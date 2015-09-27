package com.storm.goods.trident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import storm.trident.state.map.IBackingMap;

import com.storm.goods.model.CountGoodsInfo;
import com.storm.goods.model.GoodsTypeClickInfo;

public class GoodsTridentBackingMap implements IBackingMap<Long>{
	
	//存储商品数据
	Map<Long,Object> goods = new ConcurrentHashMap<Long,Object>();
	
	/**
	 * 获取数据计算总量
	 */
	public List<Long> multiGet(List<List<Object>> keys) {
		ArrayList<Long> values = new ArrayList<Long>();
		for(List<Object> key:keys){
			String goodsName = key.get(1).toString();
			String goodsType = key.get(2).toString();
			long id = Long.parseLong(key.get(3).toString());
			CountGoodsInfo info = (CountGoodsInfo)goods.get(id);
			if(info==null){
				values.add(0L);
			}else{
				Map<String, Object> goodsTypes = info.getMap();
				GoodsTypeClickInfo goodsTypeInfo = (GoodsTypeClickInfo)goodsTypes.get(goodsType);
				if(goodsTypeInfo==null){
					values.add(0L);
				}else{
					values.add(goodsTypeInfo.getClickNum());
				}
			}
		}
		return values;
	}

	/**
	 * 获取计算后的总量,更新数据
	 */
	public void multiPut(List<List<Object>> keys, List<Long> vals) {
		//计算总量
		//"clickNum","goodsName","goodsType","id"
				//解析数据
		for(List<Object> key:keys){
			String goodsName = key.get(1).toString();
			String goodsType = key.get(2).toString();
			long id = Long.parseLong(key.get(3).toString());
			CountGoodsInfo info = (CountGoodsInfo)goods.get(id);
			//添加商品点击数
			if(info==null){
				CountGoodsInfo goodsInfo = new CountGoodsInfo();
				goodsInfo.setClickNum(1L);
				goodsInfo.setGoodsId(id);
				goodsInfo.setGoodsName(goodsName);
				
				GoodsTypeClickInfo goodsTypeInfo = new GoodsTypeClickInfo();
				goodsTypeInfo.setClickNum(vals.get(0));
				goodsTypeInfo.setGoodsId(id);
				goodsTypeInfo.setGoodsType(goodsType);
				HashMap<String, Object> goodsTypeMap = new HashMap<String,Object>();
				goodsTypeMap.put(goodsType, goodsTypeMap);
				goodsInfo.setMap(goodsTypeMap);
				//设置值
				goods.put(id, goodsInfo);
			}else{
				//获取类型
				Map<String, Object> goodsTypeMap = info.getMap();
				if(goodsTypeMap.containsKey(goodsType)){
					GoodsTypeClickInfo goodsTypeInfo = (GoodsTypeClickInfo)goodsTypeMap.get(goodsType);
					goodsTypeInfo.setClickNum(vals.get(0));
					//设置值
					goodsTypeMap.put(goodsType, goodsTypeInfo);
					//设置总点击数量
					info.setClickNum(info.getClickNum()+1);
				}else{
					GoodsTypeClickInfo goodsTypeInfo = new GoodsTypeClickInfo();
					goodsTypeInfo.setClickNum(vals.get(0));
					goodsTypeInfo.setGoodsId(id);
					goodsTypeInfo.setGoodsType(goodsType);
					goodsTypeMap.put(goodsType, goodsTypeInfo);
					//设置总点击量
					info.setClickNum(info.getClickNum()+1);
				}
			}
			
		}
	}

}
