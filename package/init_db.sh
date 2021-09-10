#!/bin/sh

if [ -z "$DB_ROOT_PASSWD" ]; then
   DB_ROOT_PASSWD=12306
fi

DATA_DIR=$(dirname $0)

if `which systemctl >/dev/null 2>&1`;then
	USE_SYSTEMD=1
fi

if [ "$1" = "--init" ];then
	echo "=== start database service ..."
	shift 1

	if [ -n "$USE_SYSTEMD" ];then
		sudo systemctl start mysql 2>/dev/null
		sudo systemctl start mariadb 2>/dev/null
	else
		sudo /etc/init.d/mysqld start
	fi

	sleep 2
	sudo mysqladmin password $DB_ROOT_PASSWD 2>/dev/null
fi

if [ -n "$DB_BACKUP_FILE" ];then
	$DATA_DIR/backup_db.sh -o $DB_BACKUP_FILE
fi

echo "=== init databases ..."

mysql -u root -p$DB_ROOT_PASSWD < $DATA_DIR/sql/create_table.sql

if [ -n "$DB_BACKUP_FILE" ];then
	$DATA_DIR/load_db.sh $DB_BACKUP_FILE
fi

mysql -u root -p$DB_ROOT_PASSWD < $DATA_DIR/sql/insert.sql
