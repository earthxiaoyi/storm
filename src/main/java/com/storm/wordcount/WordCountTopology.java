package com.storm.wordcount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * wordcount topology
 * @author jiaming.jiang
 *
 */
public class WordCountTopology {

	private static final String SENTENCE_SPOUT_ID="sentence-spout";
	
	private static final String SPLIT_BOLT_ID="split-bolt";
	
	private static final String COUNT_BOLT_ID="count-bolt";
	
	private static final String REPORT_BOLT_ID="report-bolt";
	
	private static final String TOPOLOGY_NAME="word-count-topology";
	
	public static void main(String[] args) {
		
		SentenceSpout spout = new SentenceSpout();
		SplitSentenceBolt split = new SplitSentenceBolt();
		WordCountBolt wordCount = new WordCountBolt();
		ReportBolt report = new ReportBolt();
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout(SENTENCE_SPOUT_ID, spout);
		
		builder.setBolt(SPLIT_BOLT_ID, split).shuffleGrouping(SENTENCE_SPOUT_ID);
		
		builder.setBolt(COUNT_BOLT_ID, wordCount).fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
		
		builder.setBolt(REPORT_BOLT_ID, report).globalGrouping(COUNT_BOLT_ID);
		
		Config config = new Config();
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		cluster.killTopology(TOPOLOGY_NAME);
		cluster.shutdown();
	}

}
