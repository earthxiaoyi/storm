package com.storm.goods.topology;

import storm.trident.Stream;
import storm.trident.TridentTopology;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;

import com.storm.goods.function.CountGoodsTypeClick;
import com.storm.goods.function.PrepareGoodsInfo;
import com.storm.goods.function.RollingFunction;
import com.storm.goods.function.SumGoods;
import com.storm.goods.function.SumGoodsInfoClick;
import com.storm.goods.spout.GoodsClickSpout;
import com.storm.goods.trident.GoodsTrendFactory;

public class StartMain {
	
	public static void main(String[] args) throws InterruptedException {
		Config conf = new Config();
		conf.setDebug(false);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("cdc", conf, builder());
		Thread.sleep(Integer.MAX_VALUE);
		cluster.shutdown();
	}
	
	public static StormTopology builder(){
		TridentTopology topology = new TridentTopology();
		GoodsClickSpout spout = new GoodsClickSpout();
		Stream stream = topology.newStream("goods", spout);
		
		stream.each(new Fields("goods"), new PrepareGoodsInfo(),new Fields("clickNum","goodsName","goodsType","id"))
		
		.groupBy(new Fields("clickNum","goodsName","goodsType","id"))
		
		.persistentAggregate(new GoodsTrendFactory(),new CountGoodsTypeClick(), new Fields("count")).newValuesStream();
		
		return topology.build();
	}
	
}
