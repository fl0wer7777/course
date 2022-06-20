import 'package:flutter/material.dart';

class Layout extends StatelessWidget {
  const Layout({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        SizedBox(height: 10),

        Row(
          children: <Widget>[
            Expanded(
              child: Container(
                height: 200,
                color: Colors.blue,
                child: Center(
                  child: Text(
                      'this is layout demo'
                  ),
                ),
              ),
            )
          ],
        ),

        SizedBox(height: 10),

        Row(
          children: <Widget>[
            Expanded(
                flex: 2,
                child: Container(
                  height: 180,
                  child: Image.network("https://www.itying.com/images/flutter/2.png",fit: BoxFit.cover),
                )
            ),
            SizedBox(width: 10),
            Expanded(
                flex: 1,
                child: Container(
                    height: 180,
                    child: ListView(
                      children: <Widget>[
                        Container(
                          height: 90,
                          child: Image.network("https://www.itying.com/images/flutter/3.png",fit: BoxFit.cover),

                        ),
                        SizedBox(height: 10),
                        Container(
                          height: 90,
                          child: Image.network("https://www.itying.com/images/flutter/4.png",fit: BoxFit.cover),
                        )
                      ],
                    )
                )
            ),


          ],
        ),

        SizedBox(height: 10),

        Row(
          children: <Widget>[
            Expanded(
                flex: 2,
                child: Container(
                    height: 180,
                    child: ListView(
                      children: <Widget>[
                        SizedBox(height: 20),
                        Container(
                          height: 40,
                          child: Image.network("https://www.itying.com/images/flutter/3.png",fit: BoxFit.cover),

                        ),
                        SizedBox(height: 10),
                        Container(
                          height: 40,
                          child: Image.network("https://www.itying.com/images/flutter/4.png",fit: BoxFit.cover),
                        ),
                        SizedBox(height: 10),
                        Container(
                          height: 40,
                          child: Image.network("https://www.itying.com/images/flutter/3.png",fit: BoxFit.cover),

                        ),
                      ],
                    )
                ),
            ),
            SizedBox(width: 10),
            Expanded(
                flex: 1,
                child: Container(
                  height: 180,
                  child: Image.network("https://www.itying.com/images/flutter/2.png",fit: BoxFit.cover),
                )
            ),


          ],
        )
      ],
    );
  }
}
