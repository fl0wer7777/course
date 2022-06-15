#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    dTime=QObject::startTimer(1000);
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

QString MainWindow::read_ip_address()
{
    QString ip_address;
    QList<QHostAddress> ipAddressesList = QNetworkInterface::allAddresses();
    for (int i = 0; i < ipAddressesList.size(); ++i)
    {
        if (ipAddressesList.at(i) != QHostAddress::LocalHost &&  ipAddressesList.at(i).toIPv4Address())
        {
            ip_address = ipAddressesList.at(i).toString();
        }
    }
    if (ip_address.isEmpty())
        ip_address = QHostAddress(QHostAddress::LocalHost).toString();
    return ip_address;
}

void MainWindow::timerEvent(QTimerEvent *event){

    if(event->timerId()==dTime) {
        ui->label_2->setText(read_ip_address());
    }

}



void MainWindow::on_pushButton_11_clicked()
{
    QString remoteIP = ui->lineEdit_6->text();
    QProcess cmd(this);
    cmd.start("ping " + remoteIP);
    cmd.waitForFinished(-1);
    QByteArray out = cmd.readAllStandardOutput();
    ui->textBrowser->append(QString::fromLocal8Bit(out));
}
