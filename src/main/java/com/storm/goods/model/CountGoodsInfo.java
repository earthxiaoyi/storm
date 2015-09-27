package com.storm.goods.model;

import java.io.Serializable;
import java.util.Map;

public class CountGoodsInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long goodsId;
	private String goodsName;
	private long clickNum;
	private Map<String,Object> map;
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public long getClickNum() {
		return clickNum;
	}
	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}
	@Override
	public String toString() {
		return "CountGoodsInfo [goodsId=" + goodsId + ", goodsName="
				+ goodsName + ", clickNum=" + clickNum + ", map=" + map + "]";
	}
}
