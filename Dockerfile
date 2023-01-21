FROM amazoncorretto:17.0.5
WORKDIR .
ADD backend-entrypoint.sh ./entrypoint.sh
RUN chmod +x /entrypoint.sh
RUN yum install git -y
RUN yum install unzip -y
RUN yum install -y nano
RUN curl -o apache-maven-3.8.7-bin.zip -fs https://dlcdn.apache.org/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.zip
RUN unzip -q apache-maven-3.8.7-bin.zip
RUN mv apache-maven-3.8.7 /opt/
ENV PATH="${PATH}:/opt/apache-maven-3.8.7/bin"
RUN echo $PATH
RUN rm apache-maven-3.8.7-bin.zip
RUN yum clean all
RUN rm -rf /var/cache/yum
ENTRYPOINT ["./entrypoint.sh"]
EXPOSE 8080
