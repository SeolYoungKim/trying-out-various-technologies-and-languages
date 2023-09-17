# Tomcat 9와 JDK 8 기반 이미지 사용
FROM tomcat:9.0-jdk8

LABEL maintainer="pepeykb@email.com"

# Tomcat 서버 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# Scouter Agent 다운로드 및 설정
RUN mkdir /opt/scouter

ENV SCOUTER_HOME /opt/scouter

RUN mkdir -p $SCOUTER_HOME/agent

RUN apt-get update
RUN apt-get install -y curl
RUN curl -L -o $SCOUTER_HOME/agent.tar.gz https://github.com/scouter-project/scouter/releases/download/v2.17.1/scouter-all-2.17.1.tar.gz
RUN tar -xvf $SCOUTER_HOME/agent.tar.gz -C $SCOUTER_HOME/agent --strip-components 1
RUN rm $SCOUTER_HOME/agent.tar.gz
#RUN echo "net_collector_ip=scouter-collector" >> $SCOUTER_HOME/agent/conf/scouter.conf
#RUN echo "net_collector_port=6100" >> $SCOUTER_HOME/agent/conf/scouter.conf
#RUN echo "obj_name=tomcat_instance" >> $SCOUTER_HOME/agent/conf/scouter.conf

# Scouter agent를 Tomcat에 연결
ENV JAVA_OPTS="-javaagent:$SCOUTER_HOME/agent/agent.java/scouter.agent.jar"
ENV JAVA_OPTS="${JAVA_OPTS} -Dscouter.config=${SCOUTER_HOME}/agent/agent.java/conf/scouter.conf"

EXPOSE 8080

CMD ["catalina.sh", "run"]
