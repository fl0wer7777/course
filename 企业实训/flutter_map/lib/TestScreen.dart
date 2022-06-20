import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:url_launcher/url_launcher.dart';

class TestScreen extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
   return _TestScreenState();
  }

}

class _TestScreenState extends State<TestScreen>{
  @override
  Widget build(BuildContext context) {
    
    return Scaffold(
      appBar: AppBar(
        title: Text("测试通信"),
      ),
      body: Container(
        child: Column(
          children: [
            ElevatedButton(onPressed: (){
              toRouter();
            }, child: Text("调用Android函数")),
            ElevatedButton(onPressed: (){
              gotoAMap("117.295754","31.843559");

          //    gotoAMap()
            }, child: Text("跳转腾讯地图"))
          ],
        ),
      ),
    );

  }

  /// 高德地图
  static Future<bool> gotoAMap(longitude, latitude) async {
    var url = '${Platform.isAndroid ? 'android' : 'ios'}amap://navi?sourceApplication=amap&lat=$latitude&lon='
        '$longitude&dev=0&style=2';
    bool canLaunchUrl = await canLaunch(url);
    if (!canLaunchUrl) {
      print("======="+"未检测到高德地图");
     // Fluttertoast.showToast(msg: '未检测到高德地图~');
      return false;
    }
    await launch(url);

    return true;
  }

  static Future<bool> gotoTencentMap(longitude, latitude) async {
    var url = 'qqmap://map/routeplan?type=drive&fromcoord=CurrentLocation&tocoord=$latitude,'
        '$longitude&referer=Y6QBZ-M6FCD-WCK4W-HWLY2-2VKSV-H3FOA';
    bool canLaunchUrl = await canLaunch(url);

    if (!canLaunchUrl) {
      print("======="+"未检测到腾讯地图");
    //  Fluttertoast.showToast(msg: "未检测到腾讯地图");
      return false;
    }
    await launch(url);
    return canLaunchUrl;
  }



  static const platform = const MethodChannel("com.example.myapplication");
  Future<void> toRouter() async{
    var tag = await platform.invokeMethod("toRouter");
    print("=====${tag}");
  //  toRouter();

  }

}