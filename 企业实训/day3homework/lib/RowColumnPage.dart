import 'package:flutter/material.dart';

class RowColumnPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    /*return Row(
      children: [
        BlueBox(),
        BlueBox(),
        BlueBox(),
      ],
    );*/
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        BlueBox(),
        BlueBox(),
        BlueBox(),
      ],
    );
  }
}

class BlueBox extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 50,
      height: 50,
      decoration: BoxDecoration(
        color: Colors.blue,
        border: Border.all(),
      ),
    );
  }
}
