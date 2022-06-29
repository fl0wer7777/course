#include "titlebar.h"
#include "ui_titlebar.h"

TitleBar::TitleBar(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::TitleBar)
{
    m_pButtonMin = new QPushButton;
    m_pButtonMax = new QPushButton;
    m_pButtonClose = new QPushButton;
    labelTitle=new QLabel("6月24日作业");

    m_pButtonMin->setObjectName("ButtonMin");
    m_pButtonMax->setObjectName("ButtonMax");
    m_pButtonClose->setObjectName("ButtonClose");
    m_pButtonMin->setText(QString::fromLocal8Bit("-"));
    m_pButtonMax->setText(QString::fromLocal8Bit("o"));
    m_pButtonClose->setText(QString::fromLocal8Bit("x"));
    m_pButtonMin->setFixedSize(QSize(25, 25));
    m_pButtonMax->setFixedSize(QSize(25, 25));
    m_pButtonClose->setFixedSize(QSize(25, 25));
    labelTitle->setFixedHeight(25);
    m_pButtonMin->setToolTip(QStringLiteral("最小化"));
    m_pButtonMax->setToolTip(QStringLiteral("最大化"));
    m_pButtonClose->setToolTip(QStringLiteral("关闭"));

    QHBoxLayout* mylayout = new QHBoxLayout(this);
    mylayout->addWidget(labelTitle);
    mylayout->addWidget(m_pButtonMin);
    mylayout->addWidget(m_pButtonMax);
    mylayout->addWidget(m_pButtonClose);
    mylayout->setContentsMargins(0, 0, 0, 0);
    mylayout->setSpacing(0);
    this->setFixedHeight(30);
    this->setWindowFlags(Qt::FramelessWindowHint);
    connect(m_pButtonMin, SIGNAL(clicked()), this, SLOT(on_buttonMin_clicked()));
    connect(m_pButtonMax, SIGNAL(clicked()), this, SLOT(on_buttonMax_clicked()));
    connect(m_pButtonClose, SIGNAL(clicked()), this, SLOT(on_buttonClose_clicked()));
}

TitleBar::~TitleBar()
{
    delete ui;
}

void TitleBar::mousePressEvent(QMouseEvent *event)
{
    if (event->button() == Qt::LeftButton)
    {
        m_bPressed = true;
        m_point = event->pos();
    }
}

void TitleBar::mouseMoveEvent(QMouseEvent *event)
{
    if (m_bPressed)
         parentWidget()->move(event->pos() - m_point + parentWidget()->pos());
}

void TitleBar::mouseReleaseEvent(QMouseEvent *event)
{
    m_bPressed = false;
}

void TitleBar::on_buttonClose_clicked()
{
    this->window()->close();
}

void TitleBar::on_buttonMin_clicked()
{
    this->window()->showMinimized();
}

void TitleBar::on_buttonMax_clicked()
{
    if(this->window()->isMaximized())
        this->window()->showNormal();
    else
        this->window()->showMaximized();
}
