import 'package:flutter/material.dart';

import 'E2H2.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'e2h2',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: E2H2(),
    );
  }
}
