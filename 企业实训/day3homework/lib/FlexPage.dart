import 'package:flutter/material.dart';

class FlexPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Flex(
      direction: Axis.horizontal,
      children: [
        Expanded(
          flex: 2,
          child: Container(
            color: Colors.yellow,
          ),
        ),
        Expanded(
          flex: 1,
          child:  Container(
            color: Colors.blue,
          ),
        )
      ],
    );
    /*Row(
      children: <Widget>[
        Expanded(
            flex: 1,
          child:  Container(
            color: Colors.blue,
          ),
        ),
        Expanded(
          flex: 2,
          child:  Container(
            color: Colors.yellow,
          ),
        ),
        Expanded(
          flex: 1,
          child:  Container(
            color: Colors.red,
          ),
        ),

      ],
    );*/
  }
}
