import 'package:flutter/material.dart';

class SizedBoxPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 10),
      child: SizedBox(
        width: 100,
        height: 100,
        child: Container(
          color: Colors.red,
          width: 50,//这里设置无效
          height: 200,//这里设置无效
          child: Center(child: Text("AAAAAAA")),
        ),
      ),
    );
  }

}