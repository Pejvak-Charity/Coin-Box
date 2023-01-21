FROM amazoncorretto:17.0.5
RUN yum install git -y
RUN git clone https://github.com/Pejvak-Charity/Coin-Box
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
WORKDIR /Coin-Box
RUN mvn compile
ENTRYPOINT ["mvn","spring-boot:run"]
EXPOSE 8080
