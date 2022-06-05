package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//主函数
public class StrategyTest {
    public static void main(String[] args) {
        KWICOutput kwicOutput = new KWICOutput(new DisplayStrategy1());
        kwicOutput.executeOutputStrategy();

        kwicOutput.setOutputStrategy(new DisplayStrategy2());
        kwicOutput.executeOutputStrategy();
    }
}

//抽象策略类：输出方法
interface OutputStrategy {
    void show();
}

//具体策略1：输出到屏幕上
class DisplayStrategy1 implements OutputStrategy {
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

//输出策略2：输出到文本文件中
class DisplayStrategy2 implements OutputStrategy {
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
                File f = new File("C:\\Users\\dell\\Desktop\\1\\src\\4.txt");
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

//环境类：设置策略与执行
class KWICOutput {
    private OutputStrategy outputStrategy;

    public KWICOutput(OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
    }

    public OutputStrategy getOutputStrategy() { return outputStrategy; }

    public void setOutputStrategy(OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
    }

    public void executeOutputStrategy() {
        outputStrategy.show();
    }
}
