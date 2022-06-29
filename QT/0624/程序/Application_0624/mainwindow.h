#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QMouseEvent>
#include "titlebar.h"
#include "framelesshelper.h"
#include <QFrame>
QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_buttonSetBG_clicked();

    void on_buttonSetBG2_clicked();

    void on_buttonSkinRed_clicked();

    void on_buttonSkinBlue_clicked();

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
