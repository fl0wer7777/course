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


void MainWindow::requestFinished(QNetworkReply *reply) {
    QByteArray bytes;
    QVariant statusCode = reply->attribute(QNetworkRequest::HttpStatusCodeAttribute);
    if (statusCode.isValid())
        qDebug() << "status code =" << statusCode.toInt();
    QVariant reason = reply->attribute(QNetworkRequest::HttpReasonPhraseAttribute).toString();
    if (reason.isValid())
        qDebug() << "reason =" << reason.toString();
    QNetworkReply::NetworkError err = reply->error();
    if (err != QNetworkReply::NoError) {
        qDebug() << "Failed: " << reply->errorString();
    }
    else {
        bytes=reply->readAll();
        ui->textBrowser_2->setText(bytes);
    }
    QJsonParseError jsonError;
    QJsonDocument doucument = QJsonDocument::fromJson(bytes,&jsonError);
    if (jsonError.error != QJsonParseError::NoError){
        qDebug() << "Json解析错误";
        return;
    }
    if (doucument.isObject()){
        QJsonObject obj = doucument.object();
        QJsonValue value;
        if (obj.contains("data")){
            value = obj.take("data");
            if (value.isString()){
                QString data = value.toString();
                ui->textBrowser->setText(data);
                qDebug() << data;
            }
        }
    }
}

void MainWindow::on_pushButton_clicked() {
    QString baseUrl="http://httpbin.org/post";
    QUrl url(baseUrl);
    QJsonObject json;
    json.insert("User", ui->lineEdit->text());
    json.insert("Password", ui->lineEdit_2->text());
    QJsonDocument document;
    document.setObject(json);
    QByteArray dataArray = document.toJson(QJsonDocument::Compact);
    QNetworkRequest request;
    request.setUrl(url);
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
    QNetworkAccessManager * manager = new QNetworkAccessManager(this);
    connect(manager, SIGNAL(finished(QNetworkReply *)), this, SLOT(requestFinished(QNetworkReply *)));
    manager->post(request,dataArray);
}

void MainWindow::on_uploadButton_clicked()
{
    QString filePath = "C:/Users/dell/Desktop/" + ui->lineEdit_3->text() + ".png";
    file = new QFile(filePath);
    file->open(QIODevice::ReadOnly);

    QNetworkAccessManager *accessManager = new QNetworkAccessManager(this);
    accessManager->setNetworkAccessible(QNetworkAccessManager::Accessible);
    QHttpMultiPart *multiPart = new QHttpMultiPart(QHttpMultiPart::FormDataType);
    QHttpPart part_file;
    part_file.setHeader(QNetworkRequest::ContentTypeHeader, QVariant("image/png"));
    QString fileName = ui->lineEdit_3->text() + ".png";
    QString disp = QString("form-data; name=\"file\"; filename=\"%1\";").arg(fileName);
    part_file.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant(disp));
    part_file.setBodyDevice(file);
    multiPart->append(part_file);
    file->setParent(multiPart);
    QUrl url("http://localhost:9090/api/img/upload");
    QNetworkRequest request(url);
    reply = accessManager->post(request, multiPart);
    multiPart->setParent(reply);

    progressBar = new QProgressBar();
    progressBar->setValue(0);
    progressBar->show();

    connect(accessManager, SIGNAL(finished(QNetworkReply*)), this, SLOT(replyFinished(QNetworkReply*)));
    connect(reply, SIGNAL(error(QNetworkReply::NetworkError)), this, SLOT(loadError(QNetworkReply::NetworkError)));
    connect(reply, SIGNAL(uploadProgress(qint64, qint64)), this, SLOT(loadProgress(qint64, qint64)));
}

void MainWindow::on_downloadButton_clicked()
{
    file = new QFile("C:/Users/dell/Desktop/" + ui->lineEdit_3->text() + ".png");
    file->open(QIODevice::WriteOnly);

    QNetworkAccessManager *accessManager = new QNetworkAccessManager(this);
    accessManager->setNetworkAccessible(QNetworkAccessManager::Accessible);
    QUrl url("http://localhost:9090/api/images/" + ui->lineEdit_3->text() + ".png");

    QNetworkRequest request(url);
    reply = accessManager->get(request);

    progressBar = new QProgressBar();
    progressBar->setValue(0);
    progressBar->show();

    connect((QObject *)reply, SIGNAL(readyRead()), this, SLOT(readContent()));
    connect(accessManager, SIGNAL(finished(QNetworkReply*)), this, SLOT(replyFinished(QNetworkReply*)));
    connect(reply, SIGNAL(error(QNetworkReply::NetworkError)),this, SLOT(loadError(QNetworkReply::NetworkError)));
    connect(reply, SIGNAL(downloadProgress(qint64, qint64)), this, SLOT(loadProgress(qint64, qint64)));
}

void MainWindow::readContent()
{
    file->write(reply->readAll());
}

void MainWindow::replyFinished(QNetworkReply*)
{
    if(reply->error() == QNetworkReply::NoError)
        {
            reply->deleteLater();
            file->flush();
            file->close();
        }
        else
        {
            QMessageBox::critical(NULL, tr("Error"), "Failed!!!");
        }
}

void MainWindow::loadProgress(qint64 bytesSent, qint64 bytesTotal)
{
       if (bytesTotal != 0) {
           progressBar->setMaximum(bytesTotal);
           progressBar->setValue(bytesSent);
       }
}

void MainWindow::loadError(QNetworkReply::NetworkError)
{
     qDebug() << "Error: " << reply->error();
}
