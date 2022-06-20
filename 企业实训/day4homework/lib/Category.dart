import 'package:day4homework/SPrefPage.dart';
import 'package:day4homework/SearchPage.dart';
import 'package:flutter/material.dart';

class Category extends StatefulWidget {
  Category({Key? key}) : super(key: key);

  _CategoryState createState() => _CategoryState();
}

class _CategoryState extends State<Category> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Text("分类页面"),
        RaisedButton(
          child: Text("搜索页面"),
          onPressed: () {
            //普通路由跳转
            Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => SearchPage(info: "传值",),
                )
            );
          },
        ),
        RaisedButton(
          child: Text("shared_preferences"),
          onPressed: () {
            //普通路由跳转
            Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => SPrefPage(),
                )
            );
          },
        ),
        ElevatedButton(
            onPressed: () {
              showDialog(
                  context: context,
                  builder: (BuildContext context) {
                    return AlertDialog(
                      title: Text("温馨提示"),
                      content: SingleChildScrollView(
                        child: ListBody(
                          children: <Widget>[
                            Text('这是一个文本，我是一个对话框!'),
                          ],
                        ),
                      ),
                      actions: <Widget>[
                        ElevatedButton(
                          child: Text('确定'),
                          onPressed: () {
                            Navigator.of(context).pop(); //取消对话框
                          },
                        ),
                        ElevatedButton(
                          child: Text('取消'),
                          onPressed: () {
                            Navigator.of(context).pop(); //取消对话框
                          },
                        )
                      ],
                    );
                  }
              );
            },
          child: null,
        )
      ],
    );
  }
}