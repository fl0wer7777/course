import 'package:day4week2homework/ChatRoom.dart';
import 'package:day4week2homework/QRCode.dart';
import 'package:dio/dio.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jpush_flutter/jpush_flutter.dart';
import 'package:qrscan/qrscan.dart' as scanner;
import 'dart:typed_data';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HomePageState();
  }
}

class _HomePageState extends State<HomePage> {
  JPush jPush = new JPush();

  String idLabel = "";

  Uint8List bytes = Uint8List.fromList(''.codeUnits);

  Future<void> initPlatFormState() async {
    String platform = "";

    try {
      jPush.addEventHandler(
          onReceiveNotification: (Map<String, dynamic> message) async {
        print("接收到的通知是:$message");
      }, onOpenNotification: (Map<String, dynamic> message) async {
        print("通过点击推送进入app：$message");
      }, onReceiveMessage: (Map<String, dynamic> message) async {
        print("接收到自定义消息：$message");
      }, onReceiveNotificationAuthorization:
              (Map<String, dynamic> message) async {
        print("通知权限发生变更：$message");
      });
    } on PlatformException {
      platform = "平台版本获取失败";
    }

    jPush.setup(
        appKey: "f8d8ccde2fc3cd5c8329e44f",
        channel: "theChannel",
        production: false,
        debug: true);

    jPush.applyPushAuthority(
        new NotificationSettingsIOS(sound: true, alert: true, badge: true));

    jPush.getRegistrationID().then((rid) {
      print("获得的注册id为：" + rid);
      setState(() {
        idLabel = "当前注册id为：$rid";
      });
    });
  }

  @override
  void initState() {
    super.initState();
    initPlatFormState();
  }

  void sendMessage() {
    var fireDate = DateTime.fromMillisecondsSinceEpoch(
        DateTime.now().millisecondsSinceEpoch + 3000);
    var localNotification = LocalNotification(
        id: 1,
        title: '定时推送',
        buildId: 1,
        content: '三秒后本地推送',
        fireTime: fireDate,
        subtitle: '子标题',
        badge: 5,
        extra: {"data": "附带数据"});
    jPush.sendLocalNotification(localNotification);
  }

  String result = '';

  Future _scan() async {
    try {
      await Permission.camera.request();
      String? barcode = await scanner.scan();
      result = barcode!;
    } on Exception catch (_) {
      print('error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("主页"),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            ElevatedButton(
                onPressed: () {
                  _scan();
                  if (result == '外包小队') {
                    result = '';
                    jPush.setAlias('1');
                    Navigator.of(context).push(MaterialPageRoute(
                      builder: (context) => ChatRoom(),
                    ));
                  }
                  result = '';
                },
                child: Text("进行扫码")),
            ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push(MaterialPageRoute(
                    builder: (context) => QRCode(),
                  ));
                },
                child: Text("二维码生成")),
            ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push(MaterialPageRoute(
                    builder: (context) => ChatRoom(),
                  ));
                },
                child: Text("进入外包小队聊天室")),
            Text(idLabel),
          ],
        ),
      ),
    );
  }

  Future getHttp() async {
    try {
      Response response;
      FormData formData = FormData.fromMap({"name": "张三", "message": "22"});
      response = await Dio()
          .post("http://192.168.43.46:8080/user/sendMessage", data: formData);
      return response.data;
    } catch (e) {
      return print(e);
    }
  }
}
