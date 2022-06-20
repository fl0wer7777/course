import 'package:flutter/material.dart';

class ButtonPage extends StatelessWidget {
  final String info;//用于路由传值
  const ButtonPage({Key? key, this.info="默认值"}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(//在最底层采取Scaffold组件
      appBar: AppBar(
        title: Text("item${this.info}接单页面"),
      ),
      body: Text("这里是item${this.info}的接单页面！"),
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
