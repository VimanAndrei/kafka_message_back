# For Kafka comands:

### You can download Kafka froma ```https://kafka.apache.org/downloads```

## Open cmd in location you extract kafka apache.

### Start Zookeeper Server: 
``` 
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
### Start Kafka Server:
``` 
.\bin\windows\kafka-server-start.bat .\config\server.properties
``` 
### Create Topic:
``` 
.\bin\windows\kafka-topics.bat --create --topic kafka-chat --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
``` 
