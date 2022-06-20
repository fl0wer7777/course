import 'package:day4homework/Category.dart';
import 'package:day4homework/SearchPage.dart';
import 'package:flutter/material.dart';

class Tabs extends StatefulWidget {
  Tabs({Key? key}) : super(key : key);

  @override
  State<StatefulWidget> createState() => _TabsState();
}

class _TabsState extends State<Tabs> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("day4Flutter作业"),
        actions: <Widget>[
          IconButton(
              onPressed: () => {},
              icon: Icon(Icons.folder_shared)
          ),
          IconButton(
              onPressed: () => {},
              icon: Icon(Icons.share)
          ),
        ],
        elevation: 0,
      ),
      body: Category(),

      drawer: Drawer(
        child: ListView(
          children: [
            UserAccountsDrawerHeader(
                accountName: Text(
                  "一",
                  style: TextStyle(
                    fontSize: 25
                  ),
                ),
                accountEmail: Text("646****151@qq.com"),
              otherAccountsPictures: [
                Icon(
                  Icons.camera,
                color: Colors.white,
                )
              ],
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: AssetImage("images/1.png"),
                  fit: BoxFit.fill
                ),

              ),
              currentAccountPicture: CircleAvatar(
                backgroundImage: AssetImage("images/4.png"),
              ),
            ),
            ListTile(
              leading: Icon(Icons.account_balance_wallet),
              title: Text("开通会员"),
            ),
            ListTile(
              leading: Icon(Icons.share),
              title: Text("分享"),
            ),
            ListTile(
              leading: Icon(Icons.star),
              title: Text("我的收藏"),
            ),
            ListTile(
              leading: Icon(Icons.error),
              title: Text("关于"),
            ),
          ],
        ),
      ),

/*      endDrawer: Drawer(
        child: MediaQuery.removePadding(
            context: context,
            child: ListView(
              children: <Widget>[
                ListTile(leading: const Icon(Icons.add), title: const Text('Add account0')),
                ListTile(leading: const Icon(Icons.add), title: const Text('Add account1')),
                ListTile(leading: const Icon(Icons.add), title: const Text('Add account2')),
                ListTile(leading: const Icon(Icons.add), title: const Text('Add account3')),
              ],
            ),
        ),
      ),*/

      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: "首页",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.category),
            label: "分类",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.settings),
            label: "设置",
          ),
        ]
      ),
    );
  }
}