# RabbitMQ Learning 🚀

A hands-on learning project covering core RabbitMQ concepts and message-driven architecture using Java and Spring Boot.

## Topics Covered

✅ Producer & Consumer  
- Creating message producers and consumers
- Sending and receiving messages through RabbitMQ queues

✅ Exchanges  
- Direct Exchange
- Topic Exchange
- Fanout Exchange
- Understanding how exchanges route messages to queues

✅ Routing Keys  
- Message routing using routing keys
- Binding queues with specific patterns

✅ Serialization / Deserialization  
- Converting Java objects into messages
- Reading and converting RabbitMQ messages back into objects

✅ ACK / NACK  
- Manual acknowledgements
- Handling successful message processing
- Rejecting and requeueing failed messages

✅ Dead Letter Queues (DLQ)  
- Handling failed messages
- Moving rejected or expired messages to DLQ

✅ Retry Queues  
- Implementing message retry mechanisms
- Handling temporary failures

✅ Retry Count  
- Tracking retry attempts
- Preventing infinite retry loops

✅ Message Ordering  
- Understanding message order guarantees
- Managing ordered message processing

✅ Duplicate Messages  
- Understanding why duplicate messages occur
- Handling duplicate message delivery

✅ Idempotent Consumers  
- Designing consumers that safely process duplicate messages
- Preventing duplicate business operations

✅ Multiple Consumers  
- Load balancing messages between multiple consumers
- Understanding competing consumer pattern

✅ Prefetch  
- Controlling how many messages a consumer receives before acknowledgement
- Improving fair message distribution and consumer performance

## Tech Stack

- Java
- Spring Boot
- Spring AMQP
- RabbitMQ
- Maven

## Purpose

This repository is created for learning and practicing RabbitMQ concepts used in real-world distributed systems, including reliable message processing, failure handling, retries, and scalable consumer architectures.
