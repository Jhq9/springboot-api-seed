FROM openjdk:11.0.12-slim-buster
USER root

VOLUME /tmp

RUN mkdir -p /var/log/springboot-api-seed
COPY ./application.yml /usr/local/etc/springboot-api-seed/application.yml

ADD springboot-api-seed-0.0.1-SNAPSHOT.jar /usr/local/bin/springboot-api-seed/springboot-api-seed.jar
ENTRYPOINT ["java","-Duser.timezone=GMT+8","-jar","-Dspring.config.location=/usr/local/etc/springboot-api-seed/application.yml","/usr/local/bin/springboot-api-seed/springboot-api-seed.jar"]