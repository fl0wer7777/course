package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//主函数
public class PipeAndFilterTest {
    public static void main(String[] args) throws IOException {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        InputFilter inputFilter = new InputFilter(pipe1);
        RorFilter rorFilter = new RorFilter(pipe1, pipe2);
        SortFilter sortFilter = new SortFilter(pipe2, pipe3);
        OutputFilter outputFilter = new OutputFilter(pipe3);

        inputFilter.handle();
        rorFilter.handle();
        sortFilter.handle();
        outputFilter.handle();
    }
}

//管道类，过滤器之间传递数据的通道，其中传输的内容为ArrayList<String>
class Pipe {
    ArrayList<String> kwicStringList;

    void input(ArrayList<String> input) {
        kwicStringList = input;
    }

    ArrayList<String> output() {
        return kwicStringList;
    }
}

//抽象过滤器类
interface Filter {
    public void handle() throws IOException;
}

//从文本文件中读取数据，并将其通过管道传入移位过滤器
class InputFilter implements Filter {
    Pipe outPipe;
    ArrayList<String> kwicStringList;

    InputFilter(Pipe outPipe) {
        this.outPipe = outPipe;
    }

    @Override
    public void handle() throws IOException {
        BufferedReader inputFile;
        inputFile = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\1\\src\\1.txt"));
        kwicStringList = new ArrayList<String>();
        String str = inputFile.readLine();
        kwicStringList.add(str);

        outPipe.input(kwicStringList);
    }
}

//从管道中得到读取文件过滤器传来的数据，进行移位处理，再将数据通过管道传入排序过滤器
class RorFilter implements Filter {
    Pipe inputPipe;
    Pipe outPipe;
    ArrayList<String> kwicStringList;

    RorFilter(Pipe inputPipe, Pipe outPipe) {
        this.inputPipe = inputPipe;
        this.outPipe = outPipe;
    }

    String ror(String str, int l){
        char[] chars = str.toCharArray();
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[(i + l) % (str.length())] = chars[i];
        }
        return String.valueOf(cs);
    }

    @Override
    public void handle() throws IOException {
        kwicStringList = new ArrayList<String>();
        ArrayList<String> AL = new ArrayList<String>();
        AL = inputPipe.output();

        String str = AL.get(0);
        for (int n = 0; n < str.length(); n++) {
            kwicStringList.add(ror(str,n));
        }

        outPipe.input(kwicStringList);
    }
}

//从管道中得到移位过滤器处理后的数据，将其处理后通过管道传入输出过滤器
class SortFilter implements Filter {
    Pipe inputPipe;
    Pipe outPipe;
    ArrayList<String> kwicStringList;

    SortFilter(Pipe inputPipe, Pipe outPipe) {
        this.inputPipe = inputPipe;
        this.outPipe = outPipe;
    }

    @Override
    public void handle() throws IOException {
        kwicStringList = inputPipe.output();

        Collections.sort(kwicStringList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        outPipe.input(kwicStringList);
    }
}

//从管道中得到排序过滤器处理后的数据，处理后直接输出在屏幕上
class OutputFilter implements Filter {
    Pipe inputPipe;
    ArrayList<String> kwicStringList;

    OutputFilter(Pipe inputPipe) {
        this.inputPipe = inputPipe;
    }

    @Override
    public void handle() throws IOException {
        kwicStringList = inputPipe.output();

        System.out.println(kwicStringList);
    }
}
