#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<sys/ioctl.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<errno.h>
#include<unistd.h>
int main(int argc,char **argv)
{
    int fd;
    int on;
    int led_no;
    
	/**
	 * 检测异常的输入
	 * 命令格式 ./led_test1 led_no 0|1 (led_no可以取0, 1, 2, 3)
	 * 1. 参数数量不为3
	 * 2. 将第二个参数——“LED灯的编号”读入led_no，读取失败返回0
	 * 3. 将第三个参数——“LED灯开启指令”读入on，读取失败返回0 
	 * 4. 检查led_no和on的取值是否合法
	 */
    if(argc!=3||sscanf(argv[1],"%d",&led_no)!=1||sscanf(argv[2],"%d",&on)!=1||led_no<0||led_no>3||on<0||on>1)
    {
        fprintf(stderr,"Usage:leds led_no 0|1\n");
        exit(1);
    }
    
	/**
	 * open函数打开设备文件
	 * 原型定义于<fcntl.h>
	 * oflags定义于<bits/fcntl-linux.h>
	 * 0实际代表O_RDONLY——"只读"
	 * 返回文件描述符，失败返回-1
	 */
    fd=open("/dev/led0",0);
    
    if(fd<0)
    {
        fd=open("/dev/leds",0);
    }
    
    if(fd<0)
    {
        perror("open leds device:");
        exit(1);
    }
    
	/* Perform the I/O control operation specified by REQUEST on FD.
       One argument may follow; its presence and type depend on REQUEST.
  	   Return value depends on REQUEST.  Usually -1 indicates error.  */
	
	//  extern int ioctl (int __fd, unsigned long int __request, ...) __THROW;

    ioctl(fd,on,led_no);
    close(fd);
    
    return 0;
}
