import 'package:flutter/material.dart';

class AspectRatioPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 20),
      width: 100, //指定宽为100
      child: AspectRatio(
        aspectRatio: 0.5, //父容器宽为100，宽高比为0.5，则高度为200
        child: Container(
          color: Colors.red,
          child: Text(
              "11111111111111111111111111111111111111111111111111111111111111111111111111111"     "11111111111111111111111111111111111111111111111111111111111111111111111111"        "11111111111111111111111111111111111111111111111111111111111111111111111111"),
        ),
      ),
    );
  }

}