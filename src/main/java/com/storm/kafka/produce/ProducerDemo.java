package com.storm.kafka.produce;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class ProducerDemo {
	public static void main(String[] args) {
		
		Properties p = new Properties();//配置
		p.put("metadata.broker.list", "192.168.56.2:9092");
		p.put("serializer.class", "kafka.serializer.StringEncoder");
		p.put("partitioner.class", "com.storm.kafka.produce.SimplePartitioner");
		p.put("request.required.acks", "1");
		
		ProducerConfig conf = new ProducerConfig(p);
		Producer<String, String> producer = new Producer<String, String>(conf);
		String topic = "myTopic";
		//消息发送
		for(int i=0;i<10;i++){
			String key = "id:"+i;
			String msg = "msg:"+i;
			//指定topic、key、消息
			KeyedMessage<String, String> data = 
					new KeyedMessage<String,String>(topic,key,msg);
			producer.send(data);
		}
		producer.close();
	}
}
