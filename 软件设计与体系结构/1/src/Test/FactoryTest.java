package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//主函数
public class FactoryTest {
    public static void main(String[] args) {
        try {
            KWIC_F kwic1;
            Factory factory1;
            factory1 = new Factory1();
            kwic1 = factory1.newKWIC();
            kwic1.show();

            KWIC_F kwic2;
            Factory factory2;
            factory2 = new Factory2();
            kwic2 = factory2.newKWIC();
            kwic2.show();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}

//KWIC输出方式抽象接口，相当于工厂模式中的抽象产品接口
interface KWIC_F {
    public void show();
}

//具体KWIC输出方式1，实现抽象接口中的抽象方法，相当于工厂模式中的具体产品
//这里的输出方法为在屏幕上输出
class KWICOutput1 implements KWIC_F {
    private static BufferedReader inputFile;

    public static String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    @Override
    public void show() {
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

//具体KWIC输出方式2，实现抽象接口中的抽象方法，相当于工厂模式中的具体产品
//这里的输出方法为写入文本文件
class KWICOutput2 implements KWIC_F {
    private static BufferedReader inputFile;

    public static String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    @Override
    public void show() {
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
                File f = new File("C:\\Users\\dell\\Desktop\\1\\src\\2.txt");
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

//抽象工厂，提供了产品的生成方法
interface Factory {
    public KWIC_F newKWIC();
}

//具体工厂1，提供了产品的生成方法
class Factory1 implements Factory {
    @Override
    public KWIC_F newKWIC() {
        return new KWICOutput1();
    }
}

//具体工厂2，提供了产品的生成方法
class Factory2 implements Factory {
    @Override
    public KWIC_F newKWIC() {
        return new KWICOutput2();
    }
}
