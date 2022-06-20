import 'package:flutter/material.dart';

class FittedPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Container(
      color: Colors.grey,
      width: 250,
      height: 250,
      //缩放布局
      child: FittedBox(
        fit: BoxFit.contain,
        //对亲属性
        alignment: Alignment.topLeft,

        child: Container(
          color: Colors.lightBlue,
          child: Text('测试'),
        ),
      ),
    );
  }
}