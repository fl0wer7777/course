#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QNetworkRequest>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QDebug>
#include <QJsonObject>
#include <QJsonArray>
#include <QJsonDocument>
#include <QString>
#include <QUrl>
#include <QFile>
#include <QDebug>
#include <QMessageBox>
#include <QProgressBar>
#include <QHttpMultiPart>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

public slots:
    void requestFinished(QNetworkReply *reply);
    void on_uploadButton_clicked();
    void on_downloadButton_clicked();
    void readContent();
    void replyFinished(QNetworkReply*);
    void loadError(QNetworkReply::NetworkError);
    void loadProgress(qint64 bytesSent, qint64 bytesTotal);

private slots:
    void on_pushButton_clicked();

private:
    Ui::MainWindow *ui;
    QNetworkReply *reply;
    QProgressBar *progressBar;
    QFile *file;
};
#endif // MAINWINDOW_H
