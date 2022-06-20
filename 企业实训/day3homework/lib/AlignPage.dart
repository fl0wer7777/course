import 'package:flutter/material.dart';

class AlignPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 120.0,
      width: 120.0,
      color: Colors.red,
      child: Align(
        alignment: Alignment(0.2, 0.6),
        child: FlutterLogo(
          size: 60,
        ),
      ),
    );
    /*return Container(
      height: 120.0,
      width: 120.0,
      color: Colors.blue[50],
      child: Align(
        alignment: FractionalOffset(0.2, 0.6),
        child: FlutterLogo(
          size: 60,
        ),
      ),
    );*/
  }
}