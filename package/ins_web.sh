#!/bin/sh

if `which systemctl >/dev/null 2>&1`;then
	USE_SYSTEMD=1
fi

WEB_DIR=/var/www/$APP_NAME

sudo mkdir -p $WEB_DIR
sudo mkdir -p /etc/nginx/conf.d
sudo tar -xf ./web/web_files.tar.gz -C $WEB_DIR
sudo cp ./web/$APP_NAME.conf /etc/nginx/conf.d/

if [ -f "/etc/redhat-release" ];then
	sudo sed -i ':a;$!{N;ba};s/\<80\+/81/' /etc/nginx/nginx.conf
	sudo sed -i ':a;$!{N;ba};s/\<80\+/81/' /etc/nginx/nginx.conf
	sudo sed -i ':a;$!{N;ba};s/\n\daemonize no\+/\n\daemonize yes/' /etc/redis.conf
	sudo sed -i ':a;$!{N;ba};s/\n\# requirepass foobared\+/\nrequirepass 12306/' /etc/redis.conf
	sudo setenforce 0

	sudo chown -R nginx:nginx $WEB_DIR/*

	echo "=== restart nginx ..."

	if [ -n "$USE_SYSTEMD" ];then
		sudo systemctl restart nginx
		sudo systemctl restart redis
	else
		sudo /etc/init.d/nginx restart
		sudo /etc/init.d/redis restart
	fi
else
	sudo sed -i ':a;$!{N;ba};s/\<80\+/81/' /etc/nginx/nginx.conf
	sudo sed -i ':a;$!{N;ba};s/\<80\+/81/' /etc/nginx/nginx.conf
	sudo sed -i ':a;$!{N;ba};s/\n\daemonize no\+/\n\daemonize yes/' /etc/redis.conf
	sudo sed -i ':a;$!{N;ba};s/\n\# requirepass foobared\+/\nrequirepass 12306/' /etc/redis.conf

	sudo chown -R www-data:www-data $WEB_DIR/*

	echo "=== restart nginx ..."
	sudo systemctl restart nginx
	sudo systemctl restart redis
fi