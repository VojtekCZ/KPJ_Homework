# KPJ_Homework

As a first step, you need to start the RabbitMQ manager by running the following command (for Linux):

```bash
sudo docker run --rm -it -p 5672:5672 -p 15672:15672 rabbitmq:3.13.0-rc.1-management
```
The second step generates the .jar file and the Docker image of our application:
```bash
mvn clean && install
```
Certainly! In the console output during the generation process, you may encounter the following log in RabbitMQ:

```bash
rabbitConnectionFactory#4ae1ee85:0, vhost: '/', user: 'guest')
```
When logging in to the RabbitMQ GUI at http://localhost:15672/#/exchanges, you will find our parameters configured for the fanout exchange 'kpj' and the queue 'kpj.vojtechleskovsky'.

To set up the 'kpj' fanout exchange and the 'kpj.vojtechleskovsky' queue, follow these steps in the RabbitMQ management console:

Go to the 'Exchanges' tab on the RabbitMQ management console.

Locate the fanout exchange named 'kpj'.

Check the configuration of the 'kpj' fanout exchange to ensure it has the desired settings.

Navigate to the 'Queues' tab.

Find the queue named 'kpj.vojtechleskovsky'.

Verify that the 'kpj.vojtechleskovsky' queue is correctly set up with the desired parameters.
![screen1](/home/vojtek/Pictures/Screenshots/1.png)
