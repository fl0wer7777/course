import 'package:flutter/material.dart';

class ContainerPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,//容器宽
      height: 200,//容器高
      padding: EdgeInsets.all(20),//内边距
      margin: EdgeInsets.all(20),//外边距
//        color: Colors.red,//背景色，和decoration属性互斥，只能设置一个，否则会报错
      //装饰器
//        decoration: ShapeDecoration(color: Colors.red, shape: CircleBorder()),
      decoration: BoxDecoration(
        color: Colors.blue,//背景颜色
        borderRadius: BorderRadius.circular(20), //圆角
        boxShadow: <BoxShadow>[
          BoxShadow(
            color: Colors.black26, //阴影的颜色
            blurRadius: 10, //虚化的宽度
            spreadRadius: 10, //阴影和原物大小的差值--不包括虚化的部分
            offset: Offset(40, 40), //阴影的偏移量
          ),
        ],
      ),
      //容器约束-大小限制
      constraints: BoxConstraints(
        minHeight: 100,
        minWidth: 100,
        maxWidth: 200,//这里限制了200，所以上面设置的宽度300无效
        maxHeight: 150,
      ),
      child: Text("AAAAAAAA"),
    );
  }
  
}