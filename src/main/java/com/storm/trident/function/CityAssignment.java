package com.storm.trident.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import com.storm.trident.model.DiagnosisEvent;

public class CityAssignment extends BaseFunction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String,double[]> CITIES = new HashMap<String,double[]>();
	{
		double[] phl = {39.875365,-75.249524};
		CITIES.put("phl", phl);
		double[] nyc = {40.71448,-74.00598};
		CITIES.put("nyc", nyc);
		double[] sf = {-31.4250124,-62.0841809};
		CITIES.put("sf", sf);
		double[] la = {-24.05374,-118.24307};
		CITIES.put("la", la);
	}
	
	public void execute(TridentTuple tuple, TridentCollector collector) {
		DiagnosisEvent diagnosis = (DiagnosisEvent)tuple.getValue(0);
		
		double leastDistance = Double.MAX_VALUE;
		String closestCity = "NONE";
		for(Entry<String,double[]> city : CITIES.entrySet()){
			double R = 6371;//km
			double x = city.getValue()[0] - diagnosis.getLng() *
					Math.cos((city.getValue()[0]+diagnosis.getLng())/2);
			double y = (city.getValue()[1]-diagnosis.getLat());
			double d = Math.sqrt(x*x+y*y)*R;
			
			if(d<leastDistance){
				leastDistance = d;
				closestCity = city.getKey();
			}
		}
		//发射数据
		List<Object> list = new ArrayList<Object>();
		list.add(closestCity);
		collector.emit(list);
	}

}
