#ifndef FRAMELESSHELPERPRIVATE_H
#define FRAMELESSHELPERPRIVATE_H

#include <QObject>
#include <QWidget>
#include "widgetdata.h"

class FramelessHelperPrivate : public QObject
{
    Q_OBJECT
public:
    explicit FramelessHelperPrivate(QObject *parent = nullptr);

    QHash<QWidget*, WidgetData*> m_widgetDataHash;
    bool m_bWidgetMovable        : true;
    bool m_bWidgetResizable      : true;
    bool m_bRubberBandOnResize   : true;
    bool m_bRubberBandOnMove     : true;

signals:

};

#endif // FRAMELESSHELPERPRIVATE_H
