1) This project consists of three microservices Accounts,Cards and Loans registering themselves using Eurekaserver .They get their configuration from configserver which connects to github repo https://github.com/nayaksanjeet/mybank-config. 
2) To communicate among eachother they use open Feign Client.
3) These are secureed microservices by using fgateway server to communicate with Auth Server using Keycloak.  
4) For streaming messages asynchronously we have used Spring vloud stream,Spring Cloud Functions and Apache Kafka.
5) These microservices are fault tolerant ans scalable.
6) For monitoring purpose,Grafana,loki,Alloy and for tracing purpose prometheus and tempo have been used.
7) For deploying the services into cloud or in local,Kubernetes along with Helm chart have been used.


