import 'dart:io';
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;

class E2H1 extends StatefulWidget {
  const E2H1({Key? key}) : super(key: key);

  @override
  _E2H1 createState() => _E2H1();
}

class _E2H1 extends State<E2H1> {
  int like = 0;
  int comment = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('e2h1'),
      ),
      body: Container(
        margin: const EdgeInsets.all(10),
        child: Column(
          children: [
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(10),
              decoration: BoxDecoration(
                borderRadius: const BorderRadius.all(Radius.circular(5)),
                border: Border.all(color: Colors.grey),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    margin: const EdgeInsets.only(bottom: 5),
                    child: const Text(
                      'Ура!是什么意思？？？？？？',
                      style: TextStyle(fontSize: 20),
                    ),
                  ),
                  Text(
                    '奥里给！！！',
                    style: TextStyle(color: Colors.grey.shade600),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      const Icon(CupertinoIcons.heart_solid,
                          size: 20, color: Colors.red),
                      const SizedBox(
                        width: 5,
                      ),
                      Text('$like'),
                      const SizedBox(
                        width: 20,
                      ),
                      const Icon(Icons.message_rounded),
                      const SizedBox(
                        width: 5,
                      ),
                      Text('$comment')
                    ],
                  )
                ],
              ),
            ),
            const SizedBox(
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
                  child: const Text('刷新'),
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
    super.initState();
    like = 0;
    comment = 0;
  }
}
