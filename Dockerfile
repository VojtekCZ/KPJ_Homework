FROM  openjdk:17
# The WORKDIR command is used to define the working directory of a Docker container at any given time. The command is specified in the Dockerfile.
WORKDIR /home/docker-example-app/

COPY    ./target/kpj_homework-0.0.1-snapshot.jar /home/docker-example-app/
COPY    ./ /home/docker-example-app/config/

EXPOSE  31836

CMD     java -jar kpj_homework-0.0.1-snapshot --spring.config.location=file:/home/docker-example-app/config/application.properties