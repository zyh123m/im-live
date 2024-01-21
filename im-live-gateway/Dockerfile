FROM 43.240.220.48:81/kubesphere/builder-maven:v3.2.1-jdk17

MAINTAINER dev-zhang

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /work

WORKDIR /work

EXPOSE 8080
EXPOSE 18080
ADD ./im-live-gateway-1.0.jar ./

CMD sleep 60;java -Djava.security.egd=file:/dev/./urandom -jar im-live-gateway-1.0.jar