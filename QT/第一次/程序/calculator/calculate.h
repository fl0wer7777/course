#ifndef CALCULATE_H
#define CALCULATE_H

#endif // CALCULATE_H

#include<iostream>
#include<stack>
#include<vector>
using namespace std;

int calculate(string s) {

        stack<int> res;     //每个符号运算后的结果，最终按元素求和即可

        //保存运算符的位置
        s='+'+s;           //方便编码，前面补充'+'号
        vector<int> pos;
        for(int i=0;i<s.length();i=i+1)
        {
           if(s[i]=='+' || s[i]=='-' || s[i]=='*' || s[i]=='/')
            {
                pos.push_back(i);
           }
        }
        pos.push_back(s.length());

        //针对每个运算符，挑选出运算数字
        int p1=0,p2=0;                     //用于指示每个数字前后运算符的位置
        for(int i=0;i<pos.size()-1;i++)
        {
            p1=pos[i];
            p2=pos[i+1];
            string tmp_s=s.substr(p1+1,p2-p1-1);  //选出该数字的字符串
            int tmp_int=stoi(tmp_s);      //将字符串转为整型
            if(s[p1]=='+')                //判断该数字前的运算符
            {
                res.push(tmp_int);
            }else if(s[p1]=='-')
            {
                res.push(-tmp_int);
            }else if(s[p1]=='*')
            {
                int tmp=res.top();
                res.pop();
                res.push(tmp*tmp_int);
            }else if(s[p1]=='/')
            {
                int tmp=res.top();
                res.pop();
                res.push(tmp/tmp_int);  //此处为前面的数字除以当前数，不要搞反了
            }
        }

        //计算最后结果
        int sum=0;
        while(!res.empty())
        {
            sum+=res.top();
            res.pop();
        }
        return sum;

    }
