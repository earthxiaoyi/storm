package com.storm.trident.function;

import java.util.ArrayList;
import java.util.List;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import com.storm.trident.model.DiagnosisEvent;

public class HourAssignment extends BaseFunction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(TridentTuple tuple, TridentCollector collector) {
		DiagnosisEvent diagnosis = (DiagnosisEvent)tuple.getValue(0);
		String city = (String)tuple.getValue(1);
		//把时间戳变成小时
		long time = diagnosis.getTime();
		long hour = time/1000*60*60;
		
		String key = city+":"+diagnosis.getDiag()+":"+hour;
		List<Object> list = new ArrayList<Object>();
		list.add(key);
		list.add(hour);
		collector.emit(list);
	}

}
