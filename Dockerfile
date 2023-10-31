FROM  openjdk:17
# The WORKDIR command is used to define the working directory of a Docker container at any given time. The command is specified in the Dockerfile.
WORKDIR /home/docker-example-app/

COPY    ./target/docker-example-app-0.0.1-SNAPSHOT.jar /home/docker-example-app
COPY    ./docker-config/ /home/docker-example-app/config/

EXPOSE  8085

CMD     java -jar docker-example-app-0.0.1-SNAPSHOT.jar --spring.config.location=file:/home/docker-example-app/config/application.properties


