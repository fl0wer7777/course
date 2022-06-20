import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';

class CardDemo extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return ListView(
      children: <Widget>[

        Card(
          margin: EdgeInsets.all(10),
          child:Column(
            children: <Widget>[
              AspectRatio(
                aspectRatio: 20/9,
                child: Image.network("https://www.itying.com/images/flutter/2.png", fit: BoxFit.cover),
              ),
              ListTile(
                leading: ClipOval(
                  child: Image.network("https://www.itying.com/images/flutter/2.png", fit: BoxFit.cover, height:60, width: 60),
                ),
                title: Text("xxxx"),
                subtitle: Text("xxxxxxx"),

              )

            ],
          ),

        ),
      ],
    );
  }
}