package com.storm.trident;

import storm.trident.Stream;
import storm.trident.TridentTopology;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;

import com.storm.trident.filter.DiseaseFilter;
import com.storm.trident.function.CityAssignment;
import com.storm.trident.function.DispatchAlert;
import com.storm.trident.function.HourAssignment;
import com.storm.trident.function.OutbreakDetector;
import com.storm.trident.trident.Count;
import com.storm.trident.trident.OutbreakTrendFactory;

public class OutbreakDetectionTopology {
	public static StormTopology buildTopology(){
		
		TridentTopology topology = new TridentTopology();
		DiagnosisEventSpout spout = new DiagnosisEventSpout();
		Stream inputStream = topology.newStream("event", spout);
		
		inputStream.each(new Fields("event"), new DiseaseFilter())
		
		.each(new Fields("event"), new CityAssignment(),new Fields("city"))
		
		.each(new Fields("event","city"), new HourAssignment(),new Fields("hour","cityDiseaseHour"))
		
		.groupBy(new Fields("cityDiseaseHour"))
		
		.persistentAggregate(new OutbreakTrendFactory(), new Count(), new Fields("count"))
		
		.newValuesStream() 
		
		.each(new Fields("cityDiseaseHour","count"),new OutbreakDetector(),new Fields("alert"))
		
		.each(new Fields("alert"), new DispatchAlert(),new Fields());
		
		return topology.build();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Config conf = new Config();
		conf.setDebug(false);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("cdc", conf, buildTopology());
		Thread.sleep(20000);
		cluster.shutdown();
	}
}
