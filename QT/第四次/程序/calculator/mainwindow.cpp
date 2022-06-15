#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "calculate.h"
#include <sstream>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    QMenuBar * mMbar = this->menuBar();
    this->setMenuBar(mMbar);
    QMenu * menu1 = mMbar->addMenu("计算器");
    menu1->addAction("标准");
    menu1->addSeparator();
    menu1->addAction("科学");
    menu1->addSeparator();
    menu1->addAction("绘图");
    menu1->addSeparator();
    menu1->addAction("程序员");
    menu1->addSeparator();
    menu1->addAction("日期计算");
    QMenu * menu2 = mMbar->addMenu("转换器");
    menu2->addAction("货币");
    menu2->addAction("容器");
    menu2->addAction("温度");
    menu2->addAction("重量");
    menu2->addAction("长度");
    QMenu * menu3 = mMbar->addMenu("设置");
    QAction * qaction = menu3->addAction("语言设置");
    QMenu * action_menu = new QMenu();
    qaction->setMenu(action_menu);
    action_menu->addAction("中文");
    action_menu->addAction("English");
    menu3->addAction("界面设置");
    menu3->addAction("偏好设置");
    QMenu * menu4 = mMbar->addMenu("帮助");
    menu4->addAction("使用帮助");

    QStatusBar * staBar = new QStatusBar();
    setStatusBar(staBar);
    lStatusLab = new QLabel;
    staBar->addWidget(lStatusLab);
    rStatusLab = new QLabel;
    staBar->addPermanentWidget(rStatusLab);

}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::mousePressEvent(QMouseEvent(*event)) {
    if (event->button()==Qt::LeftButton) {
        rStatusLab->setText("press the leftButton");
    } else if (event->button()==Qt::RightButton) {
        rStatusLab->setText("press the rightButton");
    }
}

void MainWindow::mouseReleaseEvent(QMouseEvent *event) {
    if (event->button()==Qt::LeftButton) {
        rStatusLab->setText("release the leftButton");
    } else if (event->button()==Qt::RightButton) {
        rStatusLab->setText("release the rightButton");
    }
}

void MainWindow::mouseMoveEvent(QMouseEvent *event) {
    double x = event->pos().x();
    double y = event->pos().y();
    QString str = "(" + QString::number(x) + "," + QString::number(y) + ")";
    lStatusLab->setText(str);
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
