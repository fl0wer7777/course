#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_buttonSetBG_clicked()
{
    ui->centralwidget->setStyleSheet("background:white");
}

void MainWindow::on_buttonSetBG2_clicked()
{
    ui->centralwidget->setStyleSheet("background:grey");
}

void MainWindow::on_buttonSkinRed_clicked()
{
    QFile file("D:/Qt/project/Application_0624/qss/Red.qss");
    file.open(QFile::ReadOnly);
    parentWidget()->setStyleSheet(file.readAll());
}

void MainWindow::on_buttonSkinBlue_clicked()
{
    QFile file("D:/Qt/project/Application_0624/qss/Blue.qss");
    file.open(QFile::ReadOnly);
    parentWidget()->setStyleSheet(file.readAll());
}
