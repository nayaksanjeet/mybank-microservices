This project consists of three microservices Accounts,Cards and Loans registering themselves using Eurekaserver .They get their configuration from configserver which connects to github repo https://github.com/nayaksanjeet/mybank-config.
To communicate among eachother they use open Feign Client.
For streaming messages asynchronously we have used Spring vloud stream,Spring Cloud Functions and Apache Kafka.
These microservices are fault tolerant ans scalable.
For monitoring purpose,Grafana,loki,Alloy and for tracing purpose prometheus and tempo have been used.
For deploying the services into cloud or in local,Kubernetes along with Helm chart have been used.


