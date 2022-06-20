import 'package:day1_week2_homework/Day1Week2Homework.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Day1Week2Homework',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Day1Week2Homework(),
    );
  }
}
