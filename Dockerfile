FROM amazoncorretto:17.0.5
WORKDIR /app
COPY . .
RUN yum install -y nano
RUN yum install unzip -y
RUN curl -o apache-maven-3.8.7-bin.zip -fs https://dlcdn.apache.org/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.zip
RUN unzip apache-maven-3.8.7-bin.zip
ENV PATH="${PATH}:/app/apache-maven-3.8.7/bin"
RUN echo $PATH
RUN rm apache-maven-3.8.7-bin.zip
RUN ls
ENTRYPOINT [ "mvn","spring-boot:run" ]
EXPOSE 8080
