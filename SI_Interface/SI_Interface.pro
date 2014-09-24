TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.c \
    interface.c

include(deployment.pri)
qtcAddDeployment()

OTHER_FILES += \
    NIDAQmx.lib

HEADERS += \
    interface.h \
    NIDAQmx.h

LIBS += -lws2_32
