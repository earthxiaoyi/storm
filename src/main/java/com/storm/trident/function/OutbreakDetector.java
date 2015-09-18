package com.storm.trident.function;

import java.util.ArrayList;
import java.util.List;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class OutbreakDetector extends BaseFunction{

	private static final long serialVersionUID = 1L;

	public static final int THRESHOLD = 1000;
	
	public void execute(TridentTuple tuple, TridentCollector collector) {
		String key = tuple.getValue(0).toString();
		Long count = (Long)tuple.getValue(1);
		if(count>THRESHOLD){
			List<Object> list = new ArrayList<Object>();
			list.add("Outbreak detected for ["+key+"]");
			collector.emit(list);
		}
	}

}
