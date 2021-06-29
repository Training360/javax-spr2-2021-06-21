FROM adoptopenjdk:8-jre-hotspot
WORKDIR app
RUN  apt-get update \
     && apt-get install wget \
     && apt-get install -y netcat \
     && wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
     && chmod +x ./wait-for-it.sh
COPY target/*.jar employees.jar
ENTRYPOINT ["java", "-jar", "employees.jar"]

