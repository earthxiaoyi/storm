package com.storm.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerDemo2 {
	
	private String topic;  //主题
	private ConsumerConnector consumer;//消息消费者
	private ExecutorService executor;//线程池
	
	public ConsumerDemo2(String zkUrl,String topic,String groupid){
		this.topic=topic;
		this.consumer=Consumer.createJavaConsumerConnector(createConfig(zkUrl,groupid));
	}
	
	public void run(){
		Map<String,Integer> topicCountMap = new HashMap<String,Integer>();
		topicCountMap.put(topic, new Integer(1));
		
		Map<String, List<KafkaStream<byte[], byte[]>>> ms = 
				consumer.createMessageStreams(topicCountMap);
		
		List<KafkaStream<byte[], byte[]>> list = ms.get(topic);
		
		executor = Executors.newFixedThreadPool(10);//初始化线程池
		for(final KafkaStream stream:list){
			executor.execute(new MsgTask2(stream));
		}
	}
	
	/**
	 * 创建consumerconfig
	 * @return
	 */
	public static ConsumerConfig createConfig(String zkUrl,String groupid){
		Properties p = new Properties();
        p.put("zookeeper.connect", zkUrl);
        p.put("group.id", groupid);
        p.put("zookeeper.session.timeout.ms", "400");
        p.put("zookeeper.sync.time.ms", "200");
        p.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(p);
	}
	
	public static void main(String[] args) {
		String zkUrl = "192.168.56.2";
		String topic = "myTopic";
		String groupid = "group-1";
		ConsumerDemo2 demo = new ConsumerDemo2(zkUrl,topic,groupid);
		demo.run();
	}
}


class MsgTask2 implements Runnable{
	private KafkaStream stream;
	public MsgTask2(KafkaStream stream){
		this.stream=stream;
	}
	
	public void run() {
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while(it.hasNext()){
			System.out.println("recevier message:"+new String(it.next().message()));
		}
	}
	
}
