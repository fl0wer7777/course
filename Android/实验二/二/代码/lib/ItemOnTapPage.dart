import 'package:flutter/material.dart';

class ItemOnTapPage extends StatelessWidget {
  final String info;//用于路由传值
  const ItemOnTapPage({Key? key, this.info="默认值"}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(//在最底层采取Scaffold组件
      appBar: AppBar(
        title: Text("item${this.info}单击页面"),
      ),
      body: Text("这里是item${this.info}单击页面！"),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          //返回上一级页面
          Navigator.of(context).pop();
        },
        child: Text("back"),
      ),
    );
  }
}
