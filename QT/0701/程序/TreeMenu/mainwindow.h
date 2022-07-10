#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QFileSystemModel>
#include <QListView>
#include <QFileDialog>
#include <QDebug>
#include <QFileInfo>
#include <QIcon>
#include <QFileIconProvider>
#include <QInputDialog>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    QFileSystemModel *model;
    QModelIndex curIndex;
    void ReadDir(QString path);

public slots:
    void onCustomContextMenuRequested(const QPoint &pos);
    void on_buttonOpenFile_clicked();
    void ChangeIcon();
    void Rename();

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
