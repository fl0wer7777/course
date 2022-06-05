import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KWIC {
    private static BufferedReader inputFile;

    //将传入的字符串str循环右移l位
    public static String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        try {
            //读入文件中的内容，将内容存储在字符串str中
            inputFile = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\1\\src\\1.txt"));
            ArrayList<String> kwicStringList = new ArrayList<String>();
            String str = inputFile.readLine();
            //将str中的内容多次循环右移，右移位数从0到str.length()，并全部存入kwicStringList（ArrayList<String>）中
            for (int n = 0; n < str.length(); n++) {
                kwicStringList.add(ror(str,n));
            }
            //将kwicStringList中的内容按照首字母排序
            Collections.sort(kwicStringList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
            //在屏幕上输出
            System.out.println(kwicStringList);
            try {
                //将kwicStringList中的内容存入文本文件
                File f = new File("C:\\Users\\dell\\Desktop\\1\\src\\2.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
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
