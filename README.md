# Rabbit EJB Example

This is a Java EE project demonstrating the integration of RabbitMQ with EJB. The application uses:
- **RabbitProducer**: To send messages to RabbitMQ.
- **RabbitConsumer**: To receive and process messages from RabbitMQ.
- **MessageServlet**: To trigger message sending via HTTP request.
- **WebLogic**: As the application server.

---

## 🚀 **Features**
- Send and receive messages using RabbitMQ.
- Simple EJB setup with RabbitMQ integration.
- Stateless EJB producer and Singleton EJB consumer.
- WebServlet to trigger message sending via HTTP.

---

## ⚙️ **Technologies Used**
- Java EE 8 (EJB, Servlet)
- RabbitMQ (AMQP Client)
- Maven
- WebLogic Server
- JSP (for a basic UI)

---

## 📂 **Project Structure**
src/main/java ├── org/bondar/rabbit/ejb │ ├── RabbitProducer.java // Stateless EJB for sending messages
 │ ├── RabbitConsumer.java // Singleton EJB for consuming messages │ └── MessageServlet.java
 // Servlet to trigger message sending └── webapp ├── index.jsp // Basic UI for testing └── WEB-INF └── weblogic.xml
 // WebLogic deployment description


---

## 🔧 **Prerequisites**
- Java 1.8
- RabbitMQ Server (Running on localhost)
- Maven
- WebLogic Server

---

## 🔌 **RabbitMQ Configuration**
Make sure RabbitMQ is running on `localhost` with the default port (`5672`).
You can use Docker to run RabbitMQ:
```sh
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

---

mvn clean install

---

Access the application using this URL:
http://localhost:7001/rabbit-ejb-example/index.jsp

Send a message using this following URL:
http://localhost:7001/rabbit-ejb-example/send?message=HelloWorld


---


## 📞 **Contact**
For any issues or questions, feel free to reach out:

Email: bondarali1380@gmail.com
GitHub: AliBondar
