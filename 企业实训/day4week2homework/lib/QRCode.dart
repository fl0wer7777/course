import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';

class QRCode extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _QRCodeState();
  }
}

class _QRCodeState extends State<QRCode> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("二维码"),
      ),
      body: _createBody(),
    );
  }

  Widget _createBody() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Center(
          child: QrImage(
            data: "外包小队",
            size: 200.0,
          ),
        ),
        ElevatedButton(
          onPressed: () {
            //返回上一级页面
            Navigator.of(context).pop();
          },
          child: Text("back"),
        ),
      ],
    );
  }
}
