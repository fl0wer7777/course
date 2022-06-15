#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QToolBar>
#include <QLabel>
#include <QTime>
#include <QTimer>
#include <QHostAddress>
#include <QNetworkInterface>
#include <QProcess>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    QString read_ip_address();
    void timerEvent(QTimerEvent * event);

    int time1 = -1;
    int time2 = -1;

    QTimer * timer;
    QLabel * timeLabel;

public slots:
    void updateLocalTime();

private slots:
    void on_pushButton_66_clicked();

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
