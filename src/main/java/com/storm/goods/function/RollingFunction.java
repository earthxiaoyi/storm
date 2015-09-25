package com.storm.goods.function;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class RollingFunction extends BaseFunction{

	private static final long serialVersionUID = 1L;

	public void execute(TridentTuple tuple, TridentCollector collector) {
		System.out.println(tuple.toString());
	}

}
