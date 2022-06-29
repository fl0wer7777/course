#include "widget.h"
#include <QFile>
#include <QApplication>
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QFile file("D:/Qt/project/Application_0624/qss/Red.qss");
    file.open(QFile::ReadOnly);
    a.setStyleSheet(file.readAll());
    Widget w;
    w.show();
    return a.exec();
}
