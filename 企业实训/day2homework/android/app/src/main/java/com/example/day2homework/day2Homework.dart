import 'dart:io';
import 'dart:math';

void main() {

  //输入月份并正确显示季节
  var input = stdin.readLineSync();
  int month = int.parse(input!);
  if (month >= 3 && month <= 5) {
    print('春季');
  } else if (month >= 6 && month <= 8) {
    print('夏季');
  } else if (month >= 9 && month <= 11) {
    print('秋季');
  } else if (month == 12 || month == 1 || month == 2) {
    print('冬季');
  } else {
    print('error');
  }

  //九九乘法表
  /*for (var i = 1; i < 10; ++i) {
    String line = "";
    for (var j = 1; j <= i; ++j) {
      int res = j * i;
      line += "$j * $i = $res ";
    }
    print(line);
  }*/

  //随机生成数组并排序
  /*var list = List.filled(20, 0);
  for (var i = 0; i < list.length; ++i) {
    list[i] = Random().nextInt(1000);
  }
  print(list);
  list.sort();
  print(list);*/

}