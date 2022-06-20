import 'package:flutter/material.dart';

class StatefulWidgetLifePage extends StatefulWidget {
  @override
  createState() {
    print('create state');
    return TestState();
  }
}

class TestState extends State<StatefulWidgetLifePage> {

  @override
  initState() {
    print('init state');
    super.initState();
  }

  @override
  didChangeDependencies() {
    print('did change dependencies');
    super.didChangeDependencies();
  }

  @override
  didUpdateWidget(StatefulWidgetLifePage oldWidget) {
    print('did update widget');
    super.didUpdateWidget(oldWidget);
  }

  @override
  deactivate() {
    print('deactivate');
    super.deactivate();
  }

  @override
  dispose() {
    print('dispose');
    super.dispose();
  }

  @override
  reassemble(){
    print('reassemble');
    super.reassemble();
  }

  void Test() {
    setState(() {
      print('set state');
    });
  }

  @override
  Widget build(BuildContext context) {
    print('build');
    return Column(
      children: <Widget>[
        FlatButton(
          child: Text('setState'),
          onPressed:()=> this.Test(),
        )
      ],
    );
  }
}