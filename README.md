# Tutorial Stream Kafka Topic ke Html dengan Server Sent Event (SSE) Springboot (Bahasa Indonesia)

Tutorial ini akan melakukan hands on mengenai cara stream Kafka ke HTML dengan menggunakan metode Server Send Event (SSE). Dibandingkan dengan menggunakan Web Socket (ws://) adalah SSE menggunakan protokol http(s) dan satu arah, dari server ke client saja.

Prerequsite tutorial ini adalah:
- Java 8 
- Maven 3.6.0
- IDE kesayangan anda (Eclipse, VSCode, Netbeans, InteliJ, dll)
- Git client

## Step 1: Install Apache Kafka dan buat topik baru
```bash
> wget https://www.apache.org/dyn/closer.cgi?path=/kafka/2.4.1/kafka_2.12-2.4.1.tgz
> tar -xzf kafka_2.12-2.4.1.tgz
> cd kafka_2.12-2.4.1
```

### Jalankan Kafka Server
```bash
> bin/zookeeper-server-start.sh config/zookeeper.properties
> bin/kafka-server-start.sh config/server.properties
```
Langkah diatas dilakukan untuk menjalankan server zookeeper di port **2181** dan server Kafka di port **9092**

### Buat Topic baru
```bash
> bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic mytopic
```
Langkah diatas dilakukan untuk membuat Topic Kafka baru dengan nama *mytopic* dengan jumlah partisi *1* dengan faktor replikasi sebanyak *1*

### 

