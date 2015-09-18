package com.storm.wordcount;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 数据输入
 * @author jiaming.jiang
 *
 */
public class SentenceSpout extends BaseRichSpout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpoutOutputCollector collector = null;
	
	private int index;
	
	private String[] sentences = {
			"my dog has fleas i i i i i i i i",
			"i like cold beverages",
			"sdf de rf ww"};
	public void nextTuple() {
		this.collector.emit(new Values(sentences[index]));
		index++;
		if(index>=sentences.length){
			index=0;
		}
	}

	public void open(@SuppressWarnings("rawtypes") Map config, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
	}

}
