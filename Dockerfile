FROM  openjdk:17
# The WORKDIR command is used to define the working directory of a Docker container at any given time. The command is specified in the Dockerfile.
WORKDIR /home/Desktop/Inventi/KPJ_Homework

COPY    ./target/KPJ_Homework-0.0.1-SNAPSHOT.jar /home//Desktop/Inventi/KPJ_Homework
COPY    ./ /home//Desktop/Inventi/KPJ_Homework/config/

EXPOSE  8085

CMD     java -jar /home/Desktop/Inventi/KPJ_Homework/KPJ_Homework-0.0.1-SNAPSHOT.jar --spring.config.location=file:/home/Desktop/Inventi/KPJ_Homework/application.properties


