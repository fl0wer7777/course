import 'dart:io';
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;

class Day5Homework extends StatefulWidget {
  @override
  _Day5Homework createState() => _Day5Homework();
}

class _Day5Homework extends State<Day5Homework> {
  int like = 0;
  int comment = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('day5Homework'),
      ),
      body: Container(
        margin: EdgeInsets.all(10),
        child: Column(
          children: [
            Container(
              width: double.infinity,
              padding: EdgeInsets.all(10),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                border: Border.all(color: Colors.grey),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    margin: EdgeInsets.only(bottom: 5),
                    child: Text(
                      'Ура!是什么意思？？？？？？',
                      style: TextStyle(fontSize: 20),
                    ),
                  ),
                  Text(
                    '奥里给！！！',
                    style: TextStyle(color: Colors.grey.shade600),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(CupertinoIcons.heart_solid,
                          size: 20, color: Colors.red),
                      SizedBox(
                        width: 5,
                      ),
                      Text('$like'),
                      SizedBox(
                        width: 20,
                      ),
                      Icon(Icons.message_rounded),
                      SizedBox(
                        width: 5,
                      ),
                      Text('$comment')
                    ],
                  )
                ],
              ),
            ),
            SizedBox(
              height: 20,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    var url = 'http://192.168.129.1:9090/Flutter/flutterHomework';
                    http.get(Uri.parse(url)).then((response) {
                      if (response.statusCode == HttpStatus.ok) {
                        print('响应正文：${response.body}');
                        Map<String, dynamic> map = json.decode(response.body);
                        like = map['like'];
                        comment = map['comment'];
                        setState(() {});
                        Fluttertoast.showToast(
                            msg: "点赞数量：$like，评论数量：$comment",
                            toastLength: Toast.LENGTH_SHORT,
                            gravity: ToastGravity.BOTTOM,
                            timeInSecForIosWeb: 1,
                            backgroundColor: Colors.black45,
                            textColor: Colors.white,
                            fontSize: 16.0);
                      } else {
                        print('请求失败，Code码为${response.statusCode}');
                      }
                    });
                  },
                  child: Text('刷新'),
                )
              ],
            )
          ],
        ),
      ),
    );
  }

  @override
  void initState() {
    like = 0;
    comment = 0;
  }
}
