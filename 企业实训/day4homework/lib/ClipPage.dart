import 'package:flutter/material.dart';

class ClipPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    /*return ClipOval(
      child: Image.asset(
        "images/1.png",
        fit: BoxFit.cover,
        width: 50,
        height: 50,
      ),
    );*/
    /*return ClipRect(
      child: Container(
        color: Colors.black12,
        child: Image.asset(
          "images/1.png",
          fit: BoxFit.cover,
          width: 100,
          height: 100,
        ),
      ),
    );*/
    return Container(
      margin: EdgeInsets.only(top: 10),
      child: ClipRRect(
        borderRadius: BorderRadius.all(
          Radius.circular(30),
        ),
        child: Container(
          color: Colors.black12,
          child: Image.asset(
            "images/4.png",
            fit: BoxFit.cover,
            width: 200,
            height: 200,
          ),
        ),
      ),
    );
  }

}