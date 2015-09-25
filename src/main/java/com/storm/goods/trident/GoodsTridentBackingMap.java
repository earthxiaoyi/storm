package com.storm.goods.trident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import storm.trident.state.map.IBackingMap;

public class GoodsTridentBackingMap implements IBackingMap<Long>{

	Map<String,Object> map = new ConcurrentHashMap<String,Object>();
	
	public List<Long> multiGet(List<List<Object>> keys) {
		ArrayList<Long> values = new ArrayList<Long>();
		values.add(18L);
		return values;
	}

	public void multiPut(List<List<Object>> keys, List<Long> vals) {
		//计算总量
	
	}

}
