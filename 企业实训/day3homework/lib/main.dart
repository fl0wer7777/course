import '3.1.dart';
import 'package:day3homework/Card.dart';
import 'package:day3homework/WrapPage.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:day3homework/AlignPage.dart';
import 'package:day3homework/FittedPage.dart';
import 'package:day3homework/FlexPage.dart';
import 'package:day3homework/ImagePage.dart';
import 'package:day3homework/PaddingPage.dart';
import 'package:day3homework/RowColumnPage.dart';
import 'package:day3homework/StackPage.dart';
import 'package:day3homework/StatefulWidgetLifePage.dart';
import 'package:day3homework/StatelessWidgetLifePage.dart';
import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';



void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  // This widget is the root of your application.

  Widget build(BuildContext context) {

    //通过body更改内容
    return MaterialApp(
        title: 'Flutter作业', // APP 名字
        theme: ThemeData(
          primarySwatch: Colors.blue, // APP 主题
        ),
        home: Scaffold(
            appBar: AppBar(
              title: Text('Flutter作业'), // 页面名字
            ),
            body: ImagePage()
        )
    );

    //第三题第一张图
/*    return MaterialApp(
        title: 'Flutter作业', // APP 名字
        theme: ThemeData(
          primarySwatch: Colors.blue, // APP 主题
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text('Flutter作业'), // 页面名字
          ),
          body: Layout()
        )
    );*/

    //瀑布流图
/*    return MaterialApp(
        title: 'Flutter作业', // APP 名字
        theme: ThemeData(
          primarySwatch: Colors.blue, // APP 主题
        ),
        home: Scaffold(
            appBar: AppBar(
              title: Text('Flutter作业'), // 页面名字
            ),
            body: Center(
              child: StaggeredGridView.countBuilder(
                // 横轴单元格数量
                crossAxisCount: 4,
                // 元素的数量
                itemCount: 20,
                // cell 单元格的样式
                itemBuilder: (context, index) {
                  return Material(
                    elevation: 8.0,
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                    child: CachedNetworkImage(
                      imageUrl:
                      'https://lupic.cdn.bcebos.com/20191203/3016244278_14.jpg',
                      imageBuilder: (context, imageProvider) => Container(
                        width: 100,
                        height: 100,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.all(Radius.circular(8)),
                          image: DecorationImage(
                            image: imageProvider,
                            fit: BoxFit.fill,
                          ),
                        ),
                      ),
                    ),
                  );
                },
                //第一个参数是横轴所占的单元数，第二个参数是主轴所占的单元数
                staggeredTileBuilder: (index) {
                  return StaggeredTile.count(2, index.isEven ? 2.5 : 3);
                },
                padding: EdgeInsets.all(12),
                mainAxisSpacing: 8.0,
                crossAxisSpacing: 8.0,
              ),
            ),
        )
    );*/

    //第三题第二张图
/*    return MaterialApp(
        title: 'Flutter作业', // APP 名字
        theme: ThemeData(
          primarySwatch: Colors.blue, // APP 主题
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text('Flutter作业'), // 页面名字
          ),
          body: Center(
            child: StaggeredGridView.countBuilder(
              // 横轴单元格数量
              crossAxisCount: 4,
              // 元素的数量
              itemCount: 20,
              // cell 单元格的样式
              itemBuilder: (context, index) {
                return Material(

                    child: CardDemo()
                );
              },
              //第一个参数是横轴所占的单元数，第二个参数是主轴所占的单元数
              staggeredTileBuilder: (index) {
                return StaggeredTile.count(2, index.isEven ? 2.5 : 3);
              },
              padding: EdgeInsets.all(12),
              mainAxisSpacing: 8.0,
              crossAxisSpacing: 8.0,
            ),
          ),
        )
    );*/
  }
}


/*import 'package:flutter/material.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Welcome to Flutter',
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text('Welcome to Flutter'),
        ),
        body: new Center(
          child: new Text('Hello World'),
        ),
      ),
    );
  }
}*/
