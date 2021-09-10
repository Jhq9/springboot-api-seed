#!/bin/bash
. profile.sh

INSTALL_ROOT_DIR=/usr/local/bin
EXE_ROOT_PATH=$INSTALL_ROOT_DIR/$APP_NAME
ETC_ROOT_PATH=/usr/local/etc/$APP_NAME

systemctl stop $APP_NAME
systemctl disable $APP_NAME
systemctl daemon-reload
rm -f /lib/systemd/system/$APP_NAME.service

rm -f $EXE_ROOT_PATH/$APP_NAME-APP_VERSION.jar
rm -f $ETC_ROOT_PATH/application.yml