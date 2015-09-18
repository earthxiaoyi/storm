package com.storm.trident.function;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class DispatchAlert extends BaseFunction{

	private static final long serialVersionUID = 1L;
	
	public void execute(TridentTuple tuple, TridentCollector collector) {
		String alert = (String)tuple.getValue(0);
		System.out.println("alert reveived ["+alert+"]");
		System.exit(0);
	}

}
