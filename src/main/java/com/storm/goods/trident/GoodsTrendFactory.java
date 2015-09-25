package com.storm.goods.trident;

import java.util.Map;

import backtype.storm.task.IMetricsContext;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

public class GoodsTrendFactory implements StateFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public State makeState(Map conf, IMetricsContext metrics,
			int partitionIndex, int numPartitions) {
		return new GoodsTrendState(new GoodsTridentBackingMap());
	}

}
