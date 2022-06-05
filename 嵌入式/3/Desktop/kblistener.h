#ifndef KBL_H
#define KBL_H

#include <termios.h>

struct termios inital_settings, new_settings;

void init_kblistener();
void close_kblistener();

int kbhit();
int getch();

#endif