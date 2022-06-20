import 'package:date_format/date_format.dart';
import 'package:day1_week2_homework/ButtonPage.dart';
import 'package:day1_week2_homework/ItemOnDoubleTapPage.dart';
import 'package:day1_week2_homework/ItemOnLongTapPage.dart';
import 'package:day1_week2_homework/ItemOnTapPage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class Day1Week2Homework extends StatefulWidget {
  const Day1Week2Homework({Key? key}) : super(key: key);

  @override
  _ListViewDemo createState() => _ListViewDemo();
}

class _ListViewDemo extends State<Day1Week2Homework> {
  final List<DataDemo> _data =
      List.generate(20, (i) => DataDemo(dataName[i], dataContent[i], DateTime.now()));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('待接单'),
      ),
      body: ListView.builder(
        itemCount: _data.length,
        itemBuilder: (context, index) {
          return InkWell(
            child: Dismissible(
                key: Key(UniqueKey().toString()),
              onDismissed: (direction) {
                  setState(() {
                    _data.removeAt(index);
                  });
                  Fluttertoast.showToast(msg: 'item$index被删除了');
              },
              secondaryBackground: Container(
                child: Text('左滑删除', style: TextStyle(color: Colors.white),),
                color: Colors.red,
                padding: EdgeInsets.only(right: 30),
                alignment: Alignment.centerRight,
              ),
              background: Container(
                child: Text('右滑删除', style: TextStyle(color: Colors.white),),
                color: Colors.yellow.shade800,
                padding: EdgeInsets.only(left: 30),
                alignment: Alignment.centerLeft,
              ),
              child: _itemView(context, index),
            ),
          );
        },
      ),
    );
  }

  Widget _itemView(BuildContext context, int index) {
    return Container(
      margin: EdgeInsets.only(right: 20, left: 20),
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
            SizedBox(height: 10),
            Row(
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.all(Radius.circular(5)),
                  child: SizedBox(
                    width: 50,
                    height: 50,
                    child: Image.asset('images/1.png'),
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        _data[index].name,
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      Text(
                          _data[index].content,
                        style: TextStyle(
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
                      child: Text('接单'),
                    ),
                  ],
                ),
              ],
            ),
            SizedBox(height: 10,),
            Divider(height: 1,)
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