package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//主函数，客户端代码
public class AdapterTest {
    public static void main(String[] args) {
        KWIC_A kwic_a1 = new OutputAdapter1();
        kwic_a1.show();

        KWIC_A kwic_a2 = new OutputAdapter2();
        kwic_a2.show();
    }
}

//KWIC输出方式抽象接口，目标：实现输出
//目标接口
interface KWIC_A {
    public void show();
}

//输出方式1：在屏幕上输出
//适配者1
class KWICOutput3 {
    private static BufferedReader inputFile;

    public static String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    public void display1() {
        try {
            inputFile = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\1\\src\\1.txt"));
            ArrayList<String> kwicStringList = new ArrayList<String>();
            String str = inputFile.readLine();
            for (int n = 0; n < str.length(); n++) {
                kwicStringList.add(ror(str,n));
            }
            Collections.sort(kwicStringList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
            System.out.println("Method 1!");
            System.out.println(kwicStringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//输出方式2：输出到文本文件中
//适配者2
class KWICOutput4 {
    private static BufferedReader inputFile;

    public static String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    public void display2() {
        try {
            inputFile = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\1\\src\\1.txt"));
            ArrayList<String> kwicStringList = new ArrayList<String>();
            String str = inputFile.readLine();
            for (int n = 0; n < str.length(); n++) {
                kwicStringList.add(ror(str,n));
            }
            Collections.sort(kwicStringList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
            System.out.println("Method 2!");
            try {
                File f = new File("C:\\Users\\dell\\Desktop\\1\\src\\3.txt");
                BufferedWriter bw=new BufferedWriter(new FileWriter(f));
                for(int i = 0; i < kwicStringList.size(); i++){
                    bw.write(kwicStringList.get(i));
                    bw.newLine();
                }
                bw.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//适配器1：输出方法1
class OutputAdapter1 implements KWIC_A {
    private KWICOutput3 kwicOutput3;
    public OutputAdapter1() {
        kwicOutput3 = new KWICOutput3();
    }

    @Override
    public void show() {
        kwicOutput3.display1();
    }
}

//适配器2：输出方法2
class OutputAdapter2 implements KWIC_A {
    private KWICOutput4 kwicOutput4;
    public OutputAdapter2() {
        kwicOutput4 = new KWICOutput4();
    }

    @Override
    public void show() {
        kwicOutput4.display2();
    }
}
