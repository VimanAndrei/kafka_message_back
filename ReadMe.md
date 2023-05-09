For Kafka comands:

Start Zookeeper Server: 
./zookeeper-server-start.sh ../config/zookeeper.properties

Start Kafka Server:
.\bin\windows\kafka-server-start.bat .\config\server.properties

Create Topic:
.\bin\windows\kafka-topics.bat --create --topic kafka-chat --bootstrap-server localhost:2181 --replication-factor 1 --partitions 1

