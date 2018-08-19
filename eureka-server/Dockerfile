
FROM openjdk:8-jre-alpine

ADD target/ /opt/
WORKDIR /opt

EXPOSE 8761

ENTRYPOINT exec java $JAVA_OPTS -jar eureka-server.jar
