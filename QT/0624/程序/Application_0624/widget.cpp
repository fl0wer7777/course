#include "widget.h"
#include "ui_widget.h"
#include "mainwindow.h"
Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    setWindowFlags(Qt::FramelessWindowHint | windowFlags());
    TitleBar *pTitleBar = new TitleBar(this);
    installEventFilter(pTitleBar);
    FramelessHelper *pHelper = new FramelessHelper(this);
    pHelper->activateOn(this);
    pHelper->setTitleHeight(pTitleBar->height());
    pHelper->setWidgetMovable(true);
    pHelper->setWidgetResizable(true);
    pHelper->setRubberBandOnMove(true);
    pHelper->setRubberBandOnResize(true);
    resize(400, 300);
    MainWindow *mainWindow = new MainWindow();
    mainWindow->setObjectName("mainWindow");
    QVBoxLayout *pLayout = new QVBoxLayout();
    pLayout->addWidget(pTitleBar,0);
    pLayout->addWidget(mainWindow,1);
    pLayout->setSpacing(0);
    pLayout->setContentsMargins(0, 0, 0, 0);
    setLayout(pLayout);
}

Widget::~Widget()
{
    delete ui;
}
