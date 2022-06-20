import 'package:flutter/material.dart';

class WrapPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
        child: MaterialApp(
            title: 'belineApp',
            home: Scaffold(
                appBar: AppBar(
                  title: Text('Wrap'),
                ),
                body: LayoutDemo()
            )
        )
    );
  }

}

class LayoutDemo  extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Wrap(
      spacing: 10, //- 横轴元素间的间距
      runSpacing: 10, //- 纵轴间距
      alignment: WrapAlignment.spaceBetween, //- 两边对齐
      children: <Widget>[
        MyButton('标签一'),
        MyButton('标签二'),
        MyButton('标签三'),
        MyButton('标签四'),
        MyButton('标签五'),
        MyButton('标签六'),
        MyButton('标签七'),
        MyButton('标签八'),
      ],
    );
  }
}
/**
 * 自定义一个按钮组件
 * 构造函数接收外部传入的文字
 */
class MyButton extends StatelessWidget{
  final String text;
  const MyButton(this.text,{Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return RaisedButton(
      child: Text(this.text),
      textColor: Theme.of(context).accentColor,
      onPressed: (){},  //- 点击事件
    );
  }
}
