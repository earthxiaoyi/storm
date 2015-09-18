package com.storm.trident.trident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import storm.trident.state.map.IBackingMap;

public class OutbreakTrendBackingMap implements IBackingMap<Long>{

	Map<String,Long> storage = new ConcurrentHashMap<String,Long>();
	
	public List<Long> multiGet(List<List<Object>> keys) {
		List<Long> values = new ArrayList<Long>();
		for(List<Object> key:keys){
			Long value = storage.get(key.get(0));
			if(value==null){
				values.add(new Long(0));
			} else {
				values.add(value);
			}
		}
		return values;
	}

	public void multiPut(List<List<Object>> keys, List<Long> vals) {
		for(int i=0;i<keys.size();i++){
			
			storage.put(keys.get(i).get(0).toString(), vals.get(i));
			
		}
	}

}
