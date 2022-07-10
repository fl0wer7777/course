import 'package:date_format/date_format.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'ButtonPage.dart';
import 'ItemOnDoubleTapPage.dart';
import 'ItemOnLongTapPage.dart';
import 'ItemOnTapPage.dart';

class E2H2 extends StatefulWidget {
  const E2H2({Key? key}) : super(key: key);

  @override
  _ListViewDemo createState() => _ListViewDemo();
}

class _ListViewDemo extends State<E2H2> {
  final List<DataDemo> _list =
      List.generate(20, (i) => DataDemo(dataName[i], dataContent[i], DateTime.now()));
  late ScrollController _scrollController;
  bool isLoading = false;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
    _scrollController.addListener(() {
      if (_scrollController.position.pixels >=
      _scrollController.position.maxScrollExtent) {
        _loadMore();
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    _scrollController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('e2h2'),
      ),
      body: RefreshIndicator(
        onRefresh: _handleRefresh,
        child: ListView.builder(
          itemCount: _list.length + 1,
          controller: _scrollController,
          itemBuilder: (context, index) {
            if (index < _list.length) {
              return InkWell(
                child: Dismissible(
                  key: Key(UniqueKey().toString()),
                  onDismissed: (direction) {
                    setState(() {
                      _list.removeAt(index);
                    });
                    Fluttertoast.showToast(msg: 'item$index被删除了');
                  },
                  secondaryBackground: Container(
                    child: const Text('左滑删除', style: TextStyle(color: Colors.white),),
                    color: Colors.red,
                    padding: const EdgeInsets.only(right: 30),
                    alignment: Alignment.centerRight,
                  ),
                  background: Container(
                    child: const Text('右滑删除', style: TextStyle(color: Colors.white),),
                    color: Colors.yellow.shade800,
                    padding: const EdgeInsets.only(left: 30),
                    alignment: Alignment.centerLeft,
                  ),
                  child: _itemView(context, index),
                ),
              );
            } else {
              return _buildLoadMoreItem();
            }
          },
        ),
      )
    );
  }

  Future<void> _handleRefresh() async {
    await Future.delayed(const Duration(seconds: 2), () {
      setState(() {
        List<DataDemo> _refreshData = List.generate(5, (i) => DataDemo('下拉刷新 ' + dataName[i], dataContent[i], DateTime.now()));
        _list.insertAll(0, _refreshData);
      });
    });
  }

  Future<void> _loadMore() async {
    if (!isLoading) {
      setState(() {
        isLoading = true;
      });
      await Future.delayed(const Duration(seconds: 2), () {
        setState(() {
          isLoading = false;
          List<DataDemo> _loadMoreData = List.generate(5, (i) => DataDemo('上拉加载 ' + dataName[i], dataContent[i], DateTime.now()));
          _list.insertAll(_list.length, _loadMoreData);
        });
      });
    }
  }

  Widget _buildLoadMoreItem() {
    return const Center(
      child: Padding(
        padding: EdgeInsets.all(10.0),
        child: Text("加载中..."),
      ),
    );
  }

  Widget _itemView(BuildContext context, int index) {
    return Container(
      margin: const EdgeInsets.only(right: 20, left: 20),
      child: InkWell(
        onTap: (){
          Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => ItemOnTapPage(info: '$index',),
              )
          );
        },
        onDoubleTap: (){
          Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => ItemOnDoubleTapPage(info: '$index',),
              )
          );
        },
        onLongPress: (){
          Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => ItemOnLongTapPage(info: '$index',),
              )
          );
        },
        child: Column(
          children: [
            const SizedBox(height: 10),
            Row(
              children: [
                ClipRRect(
                  borderRadius: const BorderRadius.all(Radius.circular(5)),
                  child: SizedBox(
                    width: 50,
                    height: 50,
                    child: Image.asset('images/1.png'),
                  ),
                ),
                const SizedBox(
                  width: 10,
                ),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        _list[index].name,
                        style: const TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      Text(
                          _list[index].content,
                        style: const TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.normal,
                        ),
                      ),
                    ],
                  ),
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    Text(formatDate(DateTime.now(), [yyyy, "-", mm, "-", dd, " ", HH, ":", nn, ":", ss]).toString()),
                    ElevatedButton(
                      onPressed: (){
                        Navigator.of(context).push(
                            MaterialPageRoute(
                              builder: (context) => ButtonPage(info: '$index',),
                            )
                        );
                      },
                      child: const Text('接单'),
                    ),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 10,),
            const Divider(height: 1,)
          ],
        ),
      ),
    );
  }
}

class DataDemo {
  final String name;
  final String content;
  final DateTime time;

  DataDemo(this.name, this.content, this.time);
}

final List<String> dataName = [
  '黄璋（公司领导）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
  '龙震（业务运营支撑）',
];

final List<String> dataContent = [
  '[机构|团队]0',
  '[机构|团队]1',
  '[机构|团队]2',
  '[机构|团队]3',
  '[机构|团队]4',
  '[机构|团队]5',
  '[机构|团队]6',
  '[机构|团队]7',
  '[机构|团队]8',
  '[机构|团队]9',
  '[机构|团队]10',
  '[机构|团队]11',
  '[机构|团队]12',
  '[机构|团队]13',
  '[机构|团队]14',
  '[机构|团队]15',
  '[机构|团队]16',
  '[机构|团队]17',
  '[机构|团队]18',
  '[机构|团队]19',
];