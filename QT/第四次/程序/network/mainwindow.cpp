#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    time1 = QObject::startTimer(1000);
    time2 = QObject::startTimer(5000);

    ui->setupUi(this);

    QPixmap bgImage(":/images/2.jpg");
    QPalette bgPalette = this->palette();
    bgPalette.setBrush(QPalette::Background,bgImage);
    setPalette(bgPalette);

    QMenuBar *mMbar = this -> menuBar();
    this->setMenuBar(mMbar);
    QMenu * fmenu = mMbar->addMenu("调试");
    QAction * fAct = fmenu->addAction("开始调试");
    fAct->setIcon(QIcon(":/images/1.PNG"));
    QMenu * fnmenu = new QMenu();
    fAct->setMenu(fnmenu);
    fnmenu->addAction("载入核心文件");
    fnmenu->addAction("关联到运行中的程序");
    QAction * fAct1 = fmenu->addAction("中断");
    fAct1->setIcon(QIcon(":/images/2.PNG"));
    QAction * fAct2 = fmenu->addAction("继续");
    fAct2->setIcon(QIcon(":/images/3.PNG"));
    QAction * fAct4 = fmenu->addAction("重新调试");
    fAct4->setIcon(QIcon(":/images/4.PNG"));
    fmenu->addSeparator();
    QAction * fAct5 = fmenu->addAction("停止调试");
    fAct5->setIcon(QIcon(":/images/5.PNG"));
    QMenu * Smenu = mMbar->addMenu("控件");
    QAction * sAct = Smenu->addAction("输出窗口");
    QMenu * snmenu = new QMenu();
    sAct->setMenu(snmenu);
    snmenu->addAction("上一页");
    snmenu->addAction("下一页");
    QMenu * Tmenu = mMbar->addMenu("工具");
    QMenu * Umenu = mMbar->addMenu("设置");
    QAction * uAct6 = Umenu->addAction("语言设置");
    uAct6->setIcon(QIcon(":/images/6.PNG"));
    QMenu * Vmenu = mMbar->addMenu("关于");



    QToolBar * tBar = new QToolBar(this);
    this->addToolBar(Qt::LeftToolBarArea,tBar);
    tBar->setAllowedAreas(Qt::LeftToolBarArea|Qt::RightToolBarArea);
    tBar->setFloatable("false");
    tBar->addAction("开始调试");
    tBar->addSeparator();
    tBar->addAction("结束调试");
    tBar->addSeparator();
    QPushButton * tBtn = new QPushButton("校准");
    tBar->addWidget(tBtn);
    tBar->addSeparator();

    QStatusBar * staBar = new QStatusBar();
    setStatusBar(staBar);
    QLabel * lStatusLab = new QLabel("广播地址为：192.168.222.255",this);
    staBar->addWidget(lStatusLab);
    //QLabel * rStatusLabaLab = new QLabel("当前速度2MB/S",this);
    //staBar->addPermanentWidget(rStatusLabaLab);

    timeLabel = new QLabel();
    timer= new QTimer(this);
    staBar->addPermanentWidget(timeLabel);
    //connect(timer, SIGNAL(timeout()), this, SLOT(updateLocalTime()));
    timer->start(1000);

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

    if (event->timerId() == time1) {
        ui->label_73->setText(read_ip_address());
        QTime time = QTime::currentTime();
        timeLabel->setText(time.toString());
    }

    if (event->timerId() == time2) {
        if (ui->checkBox_31->isChecked() == true) {
            QString remoteIP = ui->lineEdit_36->text();
            QProcess cmd(this);
            cmd.start("ping " + remoteIP);
            cmd.waitForFinished(-1);
            QByteArray out = cmd.readAllStandardOutput();
            ui->textBrowser_6->append(QString::fromLocal8Bit(out));
        }
    }

}

void MainWindow::on_pushButton_66_clicked()
{
    QString remoteIP = ui->lineEdit_36->text();
    QProcess cmd(this);
    cmd.start("ping " + remoteIP);
    cmd.waitForFinished(-1);
    QByteArray out = cmd.readAllStandardOutput();
    ui->textBrowser_6->append(QString::fromLocal8Bit(out));
}

void MainWindow::updateLocalTime()
{
    QTime qtimeObj = QTime::currentTime();
    QString strTime = qtimeObj.toString("hh:mm:ss");
    QDate qdateObj = QDate::currentDate();
    QString strDate = qdateObj.toString("yyyy-MM-dd");
    strDate.append(" ");
    strDate.append(strTime);
    timeLabel->setText(strDate);
}
