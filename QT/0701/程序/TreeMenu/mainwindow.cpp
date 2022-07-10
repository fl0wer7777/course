#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    model = new QFileSystemModel;
    model->setRootPath(QDir::currentPath());
    ui->treeView->setModel(model);
    ui->treeView->setRootIndex(model->index(QDir::currentPath()));
    ui->treeView->setContextMenuPolicy(Qt::CustomContextMenu);
    connect(ui->treeView,SIGNAL(customContextMenuRequested(const QPoint&)), this,SLOT(onCustomContextMenuRequested(const QPoint&)));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_buttonOpenFile_clicked() {
    QString dir = ui->lineEdit->text();
    if (dir == nullptr) {
        return;
    }
    model->setRootPath(dir);
    QModelIndex index = model->index(dir);
    ui->treeView->setModel(model);
    ui->treeView->setRootIndex(index);
    ui->treeView->expandAll();
    ReadDir(dir);
}

void MainWindow::ReadDir(QString path) {
    QDir dir(path);
    if (!dir.exists())
        return;
     dir.setFilter(QDir::Dirs | QDir::Files);
     dir.setSorting(QDir::DirsFirst);
     QFileInfoList list = dir.entryInfoList();
     for (int i=0;i<list.size();i++) {
         QFileInfo fileInfo = list.at(i);
         if (fileInfo.fileName() == "." | fileInfo.fileName() == "..") {
             continue;
         }
         if (fileInfo.isDir()) {
             ui->treeView->expand(model->index(fileInfo.filePath()));
             ReadDir(fileInfo.filePath());
         }
     }
}

void MainWindow::ChangeIcon() {
    QString path=QFileDialog::getOpenFileName(this,"打开图片","/",tr("*.ico *.jpg *.png *.jpeg"));
}

void MainWindow::onCustomContextMenuRequested(const QPoint &pos) {
    curIndex=ui->treeView->indexAt(pos);
    QMenu *menu = new QMenu;
    menu->addAction(QString("更改图标"), this, SLOT(ChangeIcon()));
    menu->addAction(QString("重命名"), this, SLOT(Rename()));
    menu->exec(QCursor::pos());
}


void MainWindow::Rename(){
    QString path = model->filePath(curIndex);
    QString text = QInputDialog::getText(this, tr("内容修改"),tr("请输入新内容"));
    QFile file(path);
    QFileInfo i(file);
    QString parentPath = i.dir().path();
    QString newName = parentPath + "/" + text;
    file.rename(newName);
}
