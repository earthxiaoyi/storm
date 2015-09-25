package com.storm.goods.function;

import storm.trident.operation.CombinerAggregator;
import storm.trident.tuple.TridentTuple;

public class CountGoodsTypeClick implements CombinerAggregator<Long>{

	/**
	 * 
	 */
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
