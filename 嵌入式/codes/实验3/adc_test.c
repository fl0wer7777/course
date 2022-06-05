#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<sys/ioctl.h>
#include<fcntl.h>
#include<linux/fs.h>
#include<errno.h>
#include<string.h>
int main(void)
{
fprintf(stderr,"Please press Ctrl-c to stop\n");
int fd=open("/dev/adc",0);
if(fd<0)
{
perror("open ADC device:");
return 1;
}
for(;;)
{
char buffer[30];
int len=read(fd,buffer,sizeof(buffer)-1);
if(len<0)
{
perror("read ADC device:");
return 1;
}
if(len>0)
{
int value=-1;
buffer[len]='\0';
sscanf(buffer,"%d",&value);
printf("ADC value:%d\n",value);
}
usleep(500*1000);
}
close(fd);
}

