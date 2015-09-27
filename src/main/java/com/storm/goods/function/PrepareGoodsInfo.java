package com.storm.goods.function;

import java.util.ArrayList;
import java.util.List;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import com.storm.goods.model.ClickInfo;

public class PrepareGoodsInfo extends BaseFunction{

	private static final long serialVersionUID = 1L;

	public void execute(TridentTuple tuple, TridentCollector collector) {
		ClickInfo goods = (ClickInfo)tuple.getValueByField("goods");
		
		long clickNum = goods.getClickNum();
		String goodsName = goods.getGoodsName();
		String goodsType = goods.getGoodsType();
		long id = goods.getId();
		List<Object> values = new ArrayList<Object>();
		
		values.add(clickNum);
		values.add(goodsName);
		values.add(goodsType);
		values.add(id);
		
		collector.emit(values);
	}
	
}
