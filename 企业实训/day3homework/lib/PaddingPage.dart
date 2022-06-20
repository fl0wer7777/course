import 'package:flutter/material.dart';

class PaddingPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 500,
      height: 500,
      color: Colors.black12,
      child: Padding(
        //padding: EdgeInsets.all(40),
        padding: EdgeInsets.fromLTRB(40, 45, 40, 45),
        child: Container(
          color: Colors.blue,
        ),
      ),
    );
  }
}
