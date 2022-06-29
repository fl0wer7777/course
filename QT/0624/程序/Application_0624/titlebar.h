#ifndef TITLEBAR_H
#define TITLEBAR_H

#include <QWidget>
#include <QMouseEvent>
#include <QPushButton>
#include <QLabel>
#include <QHBoxLayout>
namespace Ui {
class TitleBar;
}

class TitleBar : public QWidget
{
    Q_OBJECT

public:
    explicit TitleBar(QWidget *parent = nullptr);
    ~TitleBar();
    bool m_bPressed;
    QPoint m_point;
    QLabel *labelTitle;
    QPushButton* m_pButtonMin;
    QPushButton* m_pButtonMax;
    QPushButton* m_pButtonClose;
    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
private slots:
    void on_buttonClose_clicked();

    void on_buttonMin_clicked();

    void on_buttonMax_clicked();

private:
    Ui::TitleBar *ui;
};

#endif // TITLEBAR_H
