package com.storm.trident;

import java.io.Serializable;
import java.util.Map;

import storm.trident.spout.ITridentSpout;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;

public class DiagnosisEventSpout implements ITridentSpout<Long>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SpoutOutputCollector collector;
	BatchCoordinator<Long> coordinator = new DefautCoordinator();
	Emitter<Long> emitter = new DiagnosisEventEmitter();
	
	@SuppressWarnings("rawtypes")
	public BatchCoordinator<Long> getCoordinator(
			String txStateId,  Map conf, TopologyContext context) {
		return coordinator;
	}
	
	@SuppressWarnings("rawtypes")
	public Emitter<Long> getEmitter(
			String txStateId, Map conf, TopologyContext context) {
		return emitter;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getComponentConfiguration() {
		return null;
	}
	
	public Fields getOutputFields() {
		return new Fields("event");
	}
	
	
}
