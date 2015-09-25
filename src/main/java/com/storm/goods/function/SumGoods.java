package com.storm.goods.function;

import java.util.ArrayList;
import java.util.List;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class SumGoods extends BaseFunction{

	private static final long serialVersionUID = 1L;
	
	int i=1;
	
	public void execute(TridentTuple tuple, TridentCollector collector) {
		List<Object> values = new ArrayList<Object>();
		
		values.add(tuple.getStringByField("goodsName"));
		values.add(tuple.getStringByField("goodsType"));
		values.add(tuple.getLongByField("count"));
		
		collector.emit(values);
	}

}
