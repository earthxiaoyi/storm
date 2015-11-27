package com.stormkafka.topology;

import java.util.HashMap;

import com.stormkafka.function.MessageFunction;
import com.stormkafka.schema.MessageSchema;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.tuple.Fields;
import storm.kafka.BrokerHosts;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.trident.OpaqueTridentKafkaSpout;
import storm.kafka.trident.TridentKafkaConfig;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.spout.TridentSpoutCoordinator;

public class StormKafkaTopology {
	
	public static void main(String[] args) {
		
		BrokerHosts brokerHost = new ZkHosts("master:2181,slave1:2181,slave2:2181");
		TridentKafkaConfig spoutConf = new TridentKafkaConfig(brokerHost, "myTopic");
		Config config = new Config();
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("metadata.broker.list", "master:9092");
		map.put("serializer.class", "kafka.serializer.StringEncoder");
		config.put("kafka.broker.properties", map);
		config.put("topic", "myTopic");
		spoutConf.scheme = new SchemeAsMultiScheme(new MessageSchema());
		
		TridentTopology tp = new TridentTopology();
		OpaqueTridentKafkaSpout spout = new OpaqueTridentKafkaSpout(spoutConf);
		Stream newStream = tp.newStream("kafka-strem", spout);
		newStream.each(new Fields("msg"),new MessageFunction(), new Fields());
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("kafka-storm", config, tp.build());
	}
}
