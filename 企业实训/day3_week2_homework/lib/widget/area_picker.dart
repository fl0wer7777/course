import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AreaSelection extends StatefulWidget {
  final Function onSelect;
  final int initProviceIndex;
  final int initCityIndex;
  final int initCountyIndex;

  AreaSelection(
      {required Key key,
      required this.onSelect,
      required this.initProviceIndex,
      required this.initCityIndex,
      required this.initCountyIndex})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _AreaSelectionState();
  }
}

class _AreaSelectionState extends State<AreaSelection> {
  List<AreaModel> datas = [];

  ///选中的省份的index
  int selectedProvice = 0;

  ///选中的市的index
  int selectedCity = 0;

  ///选中的区的index
  int selectedCounty = 0;

  ///定义省份控制器
  late FixedExtentScrollController proviceCotroller;

  ///定义市控制器
  late FixedExtentScrollController cityController;

  ///定义区控制器
  late FixedExtentScrollController countyController;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    proviceCotroller = new FixedExtentScrollController(
        initialItem: widget.initProviceIndex ?? 0);
    cityController =
        new FixedExtentScrollController(initialItem: widget.initCityIndex ?? 0);
    countyController = new FixedExtentScrollController(
        initialItem: widget.initCountyIndex ?? 0);

    datas = initData("父-", 1);
  }

  List<AreaModel> initData(String name, int layer) {
    List<AreaModel> _datas = [];
    for (int i = 0; i < 10; i++) {
      AreaModel areaModel = new AreaModel();
      areaModel.name = name + i.toString();
      if (layer != 3) {
        areaModel.childs = initData(areaModel.name + "-", layer + 1);
      }
      _datas.add(areaModel);
    }
    return _datas;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.all(16.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                GestureDetector(
                  onTap: () {},
                  child: Text("取消"),
                ),
                GestureDetector(
                  onTap: () {},
                  child: Text(
                    "确认",
                    style: TextStyle(color: Colors.red),
                  ),
                )
              ],
            ),
          ),
          Expanded(
              child: Container(
            child: Row(
              children: [
                Expanded(
                    flex: 1,
                    child: CupertinoPicker(
                      itemExtent: 48.0,
                      scrollController: proviceCotroller,
                      onSelectedItemChanged: (position) {
                        setState(() {
                          selectedProvice = position;
                          selectedCity = 0;
                          selectedCounty = 0;
                        });
                        cityController.jumpToItem(0);
                        countyController.jumpToItem(0);
                      },
                      children: createEachItem(datas),
                    )),
                Expanded(
                    flex: 1,
                    child: CupertinoPicker(
                      scrollController: cityController,
                      itemExtent: 48.0,
                      onSelectedItemChanged: (position) {
                        setState(() {
                          selectedCity = position;
                          selectedCounty = 0;
                        });
                        countyController.jumpToItem(0);
                      },
                      children: createEachItem(datas[selectedProvice].childs),
                    )),
                Expanded(
                    flex: 1,
                    child: CupertinoPicker(
                      scrollController: countyController,
                      itemExtent: 48.0,
                      onSelectedItemChanged: (position) {
                        setState(() {
                          selectedCounty = position;
                        });
                      },
                      children: createEachItem(
                          datas[selectedProvice].childs[selectedCity].childs),
                    ))
              ],
            ),
          ))
        ],
      ),
    );
  }

  List<Widget> createEachItem(List<AreaModel> data) {
    List<Widget> target = [];
    for (AreaModel item in data) {
      target.add(Container(
        alignment: Alignment.center,
        child: Text(
          item.name ?? '',
          style: TextStyle(fontSize: 14.0),
        ),
      ));
    }
    return target;
  }
}

class AreaModel {
  late String name;
  late List<AreaModel> childs;
}
