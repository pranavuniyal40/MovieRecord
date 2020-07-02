FROM java:8-jdk-alpine

COPY ./target/springbootdemo-0.0.1-SNAPSHOT.war /usr/app/

WORKDIR /usr/app

RUN sh -c 'springbootdemo-0.0.1-SNAPSHOT.war'

ENTRYPOINT ["java","-jar","springbootdemo-0.0.1-SNAPSHOT.war"]