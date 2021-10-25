package com.example.springkafkaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringKafkaExampleApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringKafkaExampleApplication.class, args);
		MessageProducer producer = context.getBean(MessageProducer.class);
		MessageListener listener = context.getBean(MessageListener.class);

		/*
		 * Sending a Hello World message to topic 'pattest'
		 */
		producer.sendMessage("Hello, World!");
		listener.latch.await(10, TimeUnit.SECONDS);

		System.out.println("Ending the kafka testing");
		context.close();
	}

	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();
	}

	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

	public static class MessageProducer {
		@Autowired
		private KafkaTemplate<String, String> kafkaTemplate;

		@Value(value = "${message.topic.name}")
		private String topicName;

		public void sendMessage(String message) {

			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

			future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {
					System.out.println("Sent message=[" + message + "] with partition=["+result.getRecordMetadata().partition()+"] with offset=[" + result.getRecordMetadata()
							.offset() + "]");
				}

				@Override
				public void onFailure(Throwable ex) {
					System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
				}
			});
		}
	}

	public static class MessageListener {

		private CountDownLatch latch = new CountDownLatch(3);

		@KafkaListener(topics = "${message.topic.name}", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
		public void listenGroupFoo(String message) {
			System.out.println("Received Message in group 'foo': " + message);
			latch.countDown();
		}

/*		@KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
		public void listenGroupBar(String message) {
			System.out.println("Received Message in group 'bar': " + message);
			latch.countDown();
		}*/
	}
}
