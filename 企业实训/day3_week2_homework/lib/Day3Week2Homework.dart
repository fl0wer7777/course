import 'dart:io';

import 'package:day3_week2_homework/watermarker.dart';
import 'package:flutter/material.dart';
import 'package:image_crop/image_crop.dart';
import 'package:image_picker/image_picker.dart';
import 'package:share_plus/share_plus.dart';
import 'package:fluttertoast/fluttertoast.dart';

class Day3Week2Homework extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _Homework();
  }
}

class _Homework extends State<Day3Week2Homework> {
  File? _image;
  final cropKey = GlobalKey<CropState>();
  List<String> imagePaths = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("图片处理"),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            _image == null
                ? Text("请选择图片")
                : Container(
              width: 400,
              height: 400,
              child: Crop.file(_image!, key: cropKey),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                ElevatedButton(
                    onPressed: () {
                      ImagePicker()
                          .pickImage(
                          source: ImageSource.camera, imageQuality: 50)
                          .then((value) {
                        setState(() {
                          _image = File(value!.path);
                        });
                      });
                    },
                    child: Text("拍照")),
                ElevatedButton(
                    onPressed: () {
                      cropImage();
                    },
                    child: Text("图片裁剪")),
                ElevatedButton(
                    onPressed: () {
                      showPicker();
                    },
                    child: Text(
                        "选择水印内容")),
                ElevatedButton(
                    onPressed: () {
                      if (_image != null) {
                        imagePaths.add(_image!.path);
                        Share.shareFiles(imagePaths);
                      } else {
                        Fluttertoast.showToast(msg: "请先选择图片!");
                      }
                    },
                    child: Text("分享")),
              ],
            )
          ],
        ),
      ),
    );
  }

  void cropImage() async {
    final sampledFile = await ImageCrop.sampleImage(
      file: _image!,
      preferredWidth: (1024 / cropKey.currentState!.scale).round(),
      preferredHeight: (4096 / cropKey.currentState!.scale).round(),
    );

    final croppedFile = await ImageCrop.cropImage(
      file: sampledFile,
      area: cropKey.currentState!.area!,
    );

    setState(() {
      _image = croppedFile;
    });
  }

  //弹出选择器
  void showPicker() {
    showModalBottomSheet(
        context: context,
        builder: (context) {
          return StatefulBuilder(builder: (context, state) {
            return Container(
              width: MediaQuery.of(context).size.width,
              padding: EdgeInsets.only(left: 16, right: 16, bottom: 16),
              height: 400,
              child: Column(
                children: [
                  Container(
                    width: MediaQuery.of(context).size.width,
                    height: 25,
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        GestureDetector(
                          onTap: () {
                            imageAddWaterMark(_image!.path, list.toString())
                                .then((value) {
                              setState(() {
                                _image = value;
                              });
                            });
                            Navigator.of(context).pop();
                          },
                          child: Text(
                            "确定",
                            style: TextStyle(fontSize: 20, color: Colors.blue),
                          ),
                        )
                      ],
                    ),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Container(
                    width: 800,
                    height: 40,
                    child: ListView.builder(
                      itemCount: list.length == 0 ? 1 : list.length,
                      scrollDirection: Axis.horizontal,
                      itemBuilder: (context, index) {
                        return selectItemBuild(context, index, state);
                      },
                    ),
                  ),
                  Expanded(
                      child: Row(
                        children: [
                          Expanded(
                              child: ListView.builder(
                                  itemCount: _data.length,
                                  itemBuilder: (context, index) {
                                    return createParentItem(context, index, state);
                                  })),
                          Expanded(
                              child: ListView.builder(
                                  itemCount: _data[s_team].name.length,
                                  itemBuilder: (context, index) {
                                    return createChildItem(context, index, state);
                                  }))
                        ],
                      ))
                ],
              ),
            );
          });
        });
  }

  List<dataModel> _data = [
    new dataModel("一组", ["陶震", "吴涵禹", "刘晓凡", "王宇川", "何润霖", "赵震", "毛瑞"]),
    new dataModel("二组", ["张振强", "顾津宇", "秦崇昀", "曾志存", "曹天红", "余俐嘉"]),
  ];
  int s_team = 0;
  List<String> list = [];

  Widget selectItemBuild(context, index, state) {
    return Container(
      margin: EdgeInsets.all(10),
      width: 100,
      height: 100,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          list.length == 0
              ? Text(
            "请选择",
            style: TextStyle(fontSize: 15),
          )
              : Text(
            "${list[index]}",
            style: TextStyle(fontSize: 15),
          ),
          GestureDetector(
            onTap: () {
              if (list.length != 0) {
                list.remove(list[index]);
                state(() {});
              }
            },
            child: Icon(
              Icons.close,
              color: Colors.blue,
              size: 15,
            ),
          ),
          SizedBox(
            height: 20,
          )
        ],
      ),
    );
  }

  createParentItem(context, index, state) {
    return Container(
        height: 50,
        padding: EdgeInsets.only(top: 10, left: 20),
        child: InkWell(
          onTap: () {
            s_team = index;
            state(() {});
          },
          child: Text(
            "${_data[index].team}",
            style: TextStyle(
              fontSize: 20,
              color: (s_team == index) ? Colors.red : Colors.black,
            ),
          ),
        ));
  }

  createChildItem(context, index, state) {
    List<String> tempList = _data[s_team].name;
    return Container(
        height: 40,
        padding: EdgeInsets.only(top: 20, left: 20),
        child: InkWell(
            onTap: () {
              if (list.indexOf(tempList[index]) == -1) {
                list.add(tempList[index]);
              } else {
                list.remove(tempList[index]);
              }
              print(list);
              state(() {});
            },
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Text(
                  "${tempList[index]}",
                  style: TextStyle(
                    fontSize: 15,
                    color: (list.indexOf(tempList[index]) != -1)
                        ? Colors.red
                        : Colors.black,
                  ),
                ),
                SizedBox(
                  width: 60,
                ),
                Container(
                  child: list.indexOf(tempList[index]) != -1
                      ? Icon(
                    Icons.check,
                    color: Colors.red,
                  )
                      : null,
                )
              ],
            )));
  }

/*var _imagePath;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("图片处理"),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              _ImageView(_imagePath),
              RaisedButton(
                onPressed: _takePhoto,
                child: Text("拍照"),
              ),
              RaisedButton(
                onPressed: _openGallery,
                child: Text("选择照片"),
              ),
            ],
          ),
        )
    );
  }

  */ /*图片控件*/ /*
  Widget _ImageView(imgPath) {
    if (imgPath == null) {
      return Center(
        child: Text("请选择图片或拍照"),
      );
    } else {
      return Image.file(
        imgPath,
      );
    }
  }


  */ /*拍照*/ /*
  _takePhoto() async {
    var image = await ImagePicker().pickImage(source: ImageSource.camera);

    setState(() {
      _imagePath = image;
    });
  }

  */ /*相册*/ /*
  _openGallery() async {
    var image = await ImagePicker().pickImage(source: ImageSource.gallery);
    setState(() {
      _imagePath = image;
    });
  }*/

}

class dataModel {
  String team;
  List<String> name;

  dataModel(this.team, this.name);
}
