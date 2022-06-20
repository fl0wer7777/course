import 'package:flutter/material.dart';

class StatelessWidgetLifePage extends StatelessWidget {
  String title;
  StatelessWidgetLifePage({Key? key, this.title = "StatelessWidget生命周期页面"}) : super(key: key) {
    print("构造函数被调用！");
  }

  @override
  Widget build(BuildContext context) {
    print('build方法被调用了！');
    return Center(
      child: Text("StatelessWidget生命周期页面"),
    );
  }

}