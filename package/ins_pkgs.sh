#!/bin/sh

if `which systemctl >/dev/null 2>&1`;then
        USE_SYSTEMD=1
fi

if [ -f "/etc/redhat-release" ];then
        IS_REDHAT=1
fi

if [ ! "$1" = "-n" ];then
        echo "=== install pkgs ..."

        if [ -n "$IS_REDHAT" ];then
                sudo yum install -y epel-release
                sudo rpm -ivh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
                if [ -n "$USE_SYSTEMD" ];then
                        sudo yum install -y bzip2 psmisc mariadb mariadb-server vim redis java-11-openjdk-headless.x86_64 nginx
                else
                        sudo yum install -y mysql mysql-server httpd vim redis java-11-openjdk-headless.x86_64 nginx
                fi
        else
                sudo apt-get install -y mysql-client mysql-server vim redis java-11-openjdk-headless.x86_64 nginx
        fi

        if [ $? -ne 0 ];then
                echo "### Can't install system packages! Please check your internet!"
                echo "### 安装软件包失败！请确认外网可访问！"
                exit 1
        fi

else
        echo "### don't install pkgs!"
fi

if [ -n "$USE_SYSTEMD" ];then
        sudo systemctl stop firewalld
        sudo systemctl disable firewalld

        sudo systemctl enable mysql
        sudo systemctl start mysql
        sudo systemctl enable mariadb
        sudo systemctl start mariadb
        sudo systemctl enable redis
        sudo systemctl start redis
else
        sudo chkconfig mysqld on
        sudo chkconfig redis on
        sudo chkconfig iptables off

        sudo /etc/init.d/iptables stop
        sudo /etc/init.d/mysqld start
        sudo /etc/init.d/redis start
fi

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