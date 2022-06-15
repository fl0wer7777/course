#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "calculate.h"
#include <sstream>

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

void MainWindow::on_pushButton_22_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "0");
}

void MainWindow::on_pushButton_19_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "1");
}

void MainWindow::on_pushButton_18_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "2");
}

void MainWindow::on_pushButton_17_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "3");
}

void MainWindow::on_pushButton_15_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "4");
}

void MainWindow::on_pushButton_14_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "5");
}

void MainWindow::on_pushButton_13_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "6");
}

void MainWindow::on_pushButton_11_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "7");
}

void MainWindow::on_pushButton_10_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "8");
}

void MainWindow::on_pushButton_9_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "9");
}

void MainWindow::on_pushButton_20_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "+");
}

void MainWindow::on_pushButton_16_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "-");
}

void MainWindow::on_pushButton_12_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "*");
}

void MainWindow::on_pushButton_8_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    label->setText(labelText + "/");
}

void MainWindow::on_pushButton_6_clicked()
{
    QLabel* label = ui->label;
    label->setText("");
}

void MainWindow::on_pushButton_5_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    labelText.chop(1);
    label->setText(labelText);
}

void MainWindow::on_pushButton_24_clicked()
{
    QLabel* label = ui->label;
    QString labelText = label->text();
    string s = labelText.toStdString();
    int result = calculate(s);
    stringstream sstream;
    sstream << result;
    sstream >> s;
    QString qs = QString::fromStdString(s);
    label->setText(qs);
}
