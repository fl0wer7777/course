import 'package:flutter/material.dart';

class StackPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Stack(
      // alignment: Alignment.center,
      children: <Widget>[
        Positioned(
            top: 0,
            left: 0,
            child: Icon(Icons.home, size: 40, color: Colors.black)
        ),
        Positioned(
            right: 0,
            top: 0,
            child: Icon(Icons.home, size: 40, color: Colors.black)
        ),
        Positioned(
            right: 0,
            bottom: 0,
            child: Icon(Icons.home, size: 40, color: Colors.black)
        ),
        Positioned(
            left: 0,
            bottom: 0,
            child: Icon(Icons.home, size: 40, color: Colors.black)
        ),
        // Align(
        //   alignment: Alignment.topLeft,
        //   child: Icon(Icons.home,size: 40,color: Colors.black),
        // ),
        // Align(
        //   alignment: Alignment.center,
        //   child: Icon(Icons.search,size: 30,color: Colors.black),
        // ),
        // Align(
        //   alignment: Alignment.bottomRight,
        //   child: Icon(Icons.settings_applications,size: 30,color: Colors.black),
        // )
      ],
    );
  }
}
