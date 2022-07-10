import 'package:e2h1/E2H1.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'e2h1',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const E2H1(),
    );
  }
}
