#!/bin/bash
. profile.sh
. scripts/log.sh
. ins_pkgs.sh
. init_db.sh
. ins_web.sh

DB_ROOT_PASSWD=12306

echo "====== install app \"$APP_NAME\" ..."

INSTALL_ROOT_DIR=/usr/local/bin
EXE_ROOT_PATH=$INSTALL_ROOT_DIR/$APP_NAME
ETC_ROOT_PATH=/usr/local/etc/$APP_NAME

if [ -f $EXE_ROOT_PATH/$APP_NAME-$APP_VERSION.jar ];then
  (
    info_msg "stop old service"
	  sudo systemctl stop $APP_NAME
  )2>&1
  ret=$?; if [[ 0 -ne ${ret} ]]; then error_msg "stop old service failed"; exit $ret; fi
  ok_msg "stop old service success"
fi

if [ -f sql/create_table.sql ];then
  (
  	info_msg "0. init database table"
	  mysql -u root -p$DB_ROOT_PASSWD < sql/create_table.sql
  )2>&1
  ret=$?; if [[ 0 -ne ${ret} ]]; then error_msg "init database table failed"; exit $ret; fi
  ok_msg "init database table success"
fi

(
  info_msg "1. create directories"
  sudo mkdir -p /var/log/$APP_NAME
  sudo mkdir -p $EXE_ROOT_PATH
  sudo mkdir -p $ETC_ROOT_PATH

  info_msg "2. copy files"
  sudo cp -f bin/$APP_NAME-$APP_VERSION.jar $EXE_ROOT_PATH
  sudo cp -f service/$APP_NAME.service /lib/systemd/system/

  if [ ! -f "${ETC_ROOT_PATH}/application.yml" ]; then
      sudo cp conf/application.yml $ETC_ROOT_PATH
  else
      warn_msg "application.yml is already exist. skip it."
  fi
)2>&1
ret=$?; if [[ 0 -ne ${ret} ]]; then error_msg "install app failed"; exit $ret; fi
ok_msg "install app success"

(
  info_msg "3. install $APP_NAME.service for systemctl"
  sudo systemctl enable $APP_NAME
  sudo systemctl daemon-reload
  sudo systemctl start $APP_NAME
) 2>&1
ret=$?; if [[ 0 -ne ${ret} ]]; then error_msg "install $APP_NAME.service for systemctl failed"; exit $ret; fi
ok_msg "install $APP_NAME.service for systemctl success"