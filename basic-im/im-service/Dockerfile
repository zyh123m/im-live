FROM  openjdk:17-jdk-alpine

MAINTAINER dev-zhang

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /work

WORKDIR /work

EXPOSE 9090
EXPOSE 16000
ADD ./target/im-live-im-1.0.jar ./

CMD sleep 60;java -Djava.security.egd=file:/dev/./urandom -jar im-live-im-1.0.jar