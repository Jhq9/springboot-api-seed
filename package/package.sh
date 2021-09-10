#!/bin/bash
. profile.sh

FILE_NAME=$APP_NAME.$APP_VERSION.tar.gz

if [ -f $FILE_NAME ]; then
  rm $FILE_NAME
fi

rm -rf ./${APP_NAME}
mkdir -p ${APP_NAME}

chmod a+x bin/$APP_NAME-$APP_VERSION.jar
chmod a+x scripts/log.sh
chmod a+x profile.sh
chmod a+x install.sh
chmod a+x uninstall.sh
chmod a+x ins_pkgs.sh
chmod a+x ins_web.sh
chmod a+x init_db.sh

cp profile.sh ./${APP_NAME}/
cp uninstall.sh ./${APP_NAME}/
cp install.sh ./${APP_NAME}/
cp ins_pkgs.sh ./${APP_NAME}/
cp ins_web.sh ./${APP_NAME}/
cp init_db.sh ./${APP_NAME}/
cp -rf ./bin ./${APP_NAME}/
cp -rf ./scripts ./${APP_NAME}/
cp -rf ./service ./${APP_NAME}/
cp -rf ./conf ./${APP_NAME}/
cp -rf ./sql ./${APP_NAME}/
cp -rf ./web ./${APP_NAME}/

tar zcvf $FILE_NAME $APP_NAME

rm -rf ./${APP_NAME}