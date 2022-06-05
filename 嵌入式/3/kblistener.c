#include "kblistener.h"
#include <stdio.h>
#include <unistd.h>

static int peek_ch = -1;

/**
 * 初始化键盘监听器
 * 获取并修改标准终端的参数 
 */
void init_kblistener() {

    /* 获取标准终端参数 */
    tcgetattr(STDIN_FILENO, &inital_settings);
    new_settings = inital_settings;

    /**
     * 本地模式标志，控制终端编辑功能
     * ICANON: 使用标准输入模式
     * ECHO: 回显输入字符
     * ISIG: 
     */
    new_settings.c_lflag &= ~ICANON;
    new_settings.c_iflag &= ~ECHO;
    new_settings.c_lflag &= ~ISIG;

    /* 设定等待时间和等待最小字节数 */
    new_settings.c_cc[VTIME] = 0;
    new_settings.c_cc[VMIN] = 1;

    /* 将修改后的终端参数写入标准输入 */
    tcsetattr(STDIN_FILENO, TCSANOW, &new_settings);
}

/**
 * 关闭键盘监听器
 * 重置标准终端的参数 
 */
void close_kblistener() {
    tcsetattr(STDIN_FILENO, TCSANOW, &inital_settings);
}

/* 监听键盘输入 */
int kbhit() {
    unsigned char ch;
    int readCharNum;

    if (peek_ch != -1) {
        return 1;
    }

    new_settings.c_cc[VMIN] = 0;
    tcsetattr(STDIN_FILENO, TCSANOW, &new_settings);
    
    readCharNum = read(STDIN_FILENO, &ch, 1);

    new_settings.c_cc[VMIN] = 1;
    tcsetattr(STDIN_FILENO, TCSANOW, &new_settings);

    if (readCharNum == 1) {
        peek_ch = ch;
        return 1;
    }

    return 0;
}

/* 返回字符 */
int getch() {
    
    char ch;

    if (peek_ch != -1) {
        ch = peek_ch;
        peek_ch = -1;
        return ch;
    }

    read(STDIN_FILENO, &ch, 1);
    return ch;
}


