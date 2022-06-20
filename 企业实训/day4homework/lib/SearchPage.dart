import 'package:flutter/material.dart';

class SearchPage extends StatelessWidget {
  final String info;//用于路由传值
  const SearchPage({Key? key, this.info="默认值"}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(//在最底层采取Scaffold组件
      appBar: AppBar(
        title: Text("搜索页面"),
      ),
      body: Text("这里是Search Page！传递过来的参数值是：${this.info}"),
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