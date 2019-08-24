#!/bin/bash

docker-compose exec kafka  \
    kafka-topics --create \
    --topic orderItems \
    --partitions 1 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper zookeeper:2181

docker-compose exec kafka  \
    kafka-topics --create \
    --topic orders \
    --partitions 1 \
    --replication-factor 1 \
    --if-not-exists \
    --zookeeper zookeeper:2181
