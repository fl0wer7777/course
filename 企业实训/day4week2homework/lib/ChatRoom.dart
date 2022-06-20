import 'package:flutter/cupertino.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jpush_flutter/jpush_flutter.dart';
import 'package:qrscan/qrscan.dart' as scanner;
import 'dart:typed_data';

class ChatRoom extends StatefulWidget {
  const ChatRoom({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _ChatRoomState();
  }
}

class _ChatRoomState extends State<ChatRoom> with TickerProviderStateMixin {
  String name = 'name';

  JPush jPush = new JPush();

  String debugLabel = "";

  Uint8List bytes = Uint8List.fromList(''.codeUnits);

  Future<void> initPlatFormState() async {
    String platform = "";

    try {
      jPush.addEventHandler(
          onReceiveNotification: (Map<String, dynamic> msg) async {
            ChatMessage message = ChatMessage(
                name: '${msg['title']}',
                text: '${msg['alert']}',
                animationController: AnimationController(
                    duration: Duration(milliseconds: 300), vsync: this));
            setState(() {
              _messages.insert(0, message);
            });
            message.animationController.forward();
          },
          onReceiveNotificationAuthorization:
              (Map<String, dynamic> message) async {});
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
        debugLabel = "当前注册id为：$rid";
      });
    });
  }

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

  Future getHttp(String name, String message) async {
    try {
      Response response;
      FormData formData = FormData.fromMap({"name": name, "message": message});
      response = await Dio()
          .post("http://192.168.1.102:8080/user/sendMessage", data: formData);
      print("打印得到的数据" + response.data.toString());
      return response.data;
    } catch (e) {
      return print(e);
    }
  }

  Future _generateBarCode(String inputCode) async {
    Uint8List result = await scanner.generateBarCode(inputCode);
    this.setState(() => this.bytes = result);
  }

  String result = '';

  void scan() async {
    String? barcode = await scanner.scan();
    setState(() {
      result = barcode!;
    });
  }

  final List<ChatMessage> _messages = <ChatMessage>[];
  final TextEditingController _textController = TextEditingController();
  bool _isComposing = false;

  Widget _buildTextComposer() {
    return IconTheme(
        data: IconThemeData(color: Theme.of(context).colorScheme.secondary),
        child: Container(
            margin: const EdgeInsets.symmetric(horizontal: 8.0),
            child: Row(children: <Widget>[
              Flexible(
                  child: TextField(
                controller: _textController,
                onChanged: (String text) {
                  setState(() {
                    _isComposing = text.length > 0;
                  });
                },
                onSubmitted: _handleSubmitted,
                decoration: InputDecoration.collapsed(hintText: '发送消息'),
              )),
              Container(
                margin: EdgeInsets.symmetric(horizontal: 4.0),
                child: Theme.of(context).platform == TargetPlatform.iOS
                    ? CupertinoButton(
                        child: Text('发送'),
                        onPressed:
                            _isComposing ? () => _handleSubmitted1() : null)
                    : IconButton(
                        icon: Icon(Icons.send),
                        onPressed:
                            _isComposing ? () => _handleSubmitted1() : null),
              )
            ])));
  }

  void _handleSubmitted(String text) {
    _textController.clear();
    setState(() {
      _isComposing = false;
    });
    try {
      jPush.addEventHandler(
          onReceiveNotification: (Map<String, dynamic> msg) async {
            ChatMessage message = ChatMessage(
                name: '${msg['title']}',
                text: '${msg['alert']}',
                animationController: AnimationController(
                    duration: Duration(milliseconds: 300), vsync: this));
            setState(() {
              _messages.insert(0, message);
            });
            message.animationController.forward();
          },
          onReceiveNotificationAuthorization:
              (Map<String, dynamic> message) async {});
    } on PlatformException {}
  }

  void _handleSubmitted1() {
    getHttp(name, _textController.text);
    _textController.clear();
    setState(() {
      _isComposing = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('外包小队聊天室'),
        ),
        body: Column(children: <Widget>[
          Flexible(
              child: ListView.builder(
            padding: EdgeInsets.all(8.0),
            reverse: true,
            itemBuilder: (_, int index) => _messages[index],
            itemCount: _messages.length,
          )),
          Divider(height: 1.0),
          ElevatedButton(
              onPressed: () {
                name = _textController.text;
                _textController.clear();
              },
              child: Text('改变名称')),
          Divider(height: 1.0),
          Container(
            decoration: BoxDecoration(
              color: Theme.of(context).cardColor,
            ),
            child: _buildTextComposer(),
          )
        ]));
  }
}

class ChatMessage extends StatelessWidget {
  ChatMessage(
      {required this.name,
      required this.text,
      required this.animationController});

  final String name;
  final String text;
  final AnimationController animationController;

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: const EdgeInsets.symmetric(vertical: 10.0),
        child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                margin: const EdgeInsets.only(right: 16.0),
                child: CircleAvatar(child: Text(name[0])),
              ),
              Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(name, style: Theme.of(context).textTheme.subtitle1),
                    Container(
                      margin: const EdgeInsets.only(top: 5.0),
                      child: Text(text),
                    )
                  ])
            ]));
  }
}
