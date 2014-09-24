TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    wininterface.cpp \
    warehouse.cpp \
    bitmanipulation.cpp

include(deployment.pri)
qtcAddDeployment()

HEADERS += \
    wininterface.h \
    warehouse.h \
    bitmanipulation.h

LIBS += -lws2_32
