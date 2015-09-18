package com.storm.trident.trident;

import storm.trident.operation.CombinerAggregator;
import storm.trident.tuple.TridentTuple;

/**
 * 统计聚合器
 * @author jiaming.jiang
 */
public class Count implements CombinerAggregator<Long>{

	private static final long serialVersionUID = 1L;

	public Long init(TridentTuple tuple) {
		return 1L;
	}

	public Long combine(Long val1, Long val2) {
		return val1+val2;
	}

	public Long zero() {
		return 0L;
	}
	
}
