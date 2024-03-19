FROM  openjdk:17-jdk-alpine

MAINTAINER dev-zhang

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /work

WORKDIR /work

EXPOSE 7777
EXPOSE 17777
ADD ./target/im-live-id-1.0.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar im-live-id-1.0.jar