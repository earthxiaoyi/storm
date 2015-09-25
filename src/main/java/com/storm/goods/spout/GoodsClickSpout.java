package com.storm.goods.spout;

import java.io.Serializable;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import storm.trident.spout.ITridentSpout;

/**
 * 发送商品点击信息
 * @author jiaming.jiang
 */
public class GoodsClickSpout implements ITridentSpout<Long>,Serializable{

	private static final long serialVersionUID = 1L;

	SpoutOutputCollector collector;
	BatchCoordinator<Long> coordinator = new Coordinator();
	Emitter<Long> emitter = new GoodsEventEmitter();
	
	@SuppressWarnings("rawtypes")
	public BatchCoordinator<Long> getCoordinator(
			String txStateId, Map conf, TopologyContext context) {
		// TODO Auto-generated method stub
		return coordinator;
	}

	@SuppressWarnings("rawtypes")
	public Emitter<Long> getEmitter(
			String txStateId, Map conf, TopologyContext context) {
		// TODO Auto-generated method stub
		return emitter;
	}

	@SuppressWarnings("rawtypes")
	public Map getComponentConfiguration() {
		return null;
	}

	public Fields getOutputFields() {
		return new Fields("goods");
	}

}
