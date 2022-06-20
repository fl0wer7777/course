import 'package:flutter/material.dart';

class ImagePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Image(
        image: AssetImage("images/4.png"),
        width: 100.0
    );
  }
}