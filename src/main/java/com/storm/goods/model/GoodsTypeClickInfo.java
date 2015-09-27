package com.storm.goods.model;

import java.io.Serializable;

public class GoodsTypeClickInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long goodsId;
	private String goodsType;
	private String goodsTypeName;
	private long clickNum;
	
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public long getClickNum() {
		return clickNum;
	}
	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	
	@Override
	public String toString() {
		return "GoodsTypeClickInfo [goodsId=" + goodsId + ", goodsType="
				+ goodsType + ", goodsTypeName=" + goodsTypeName
				+ ", clickNum=" + clickNum + "]";
	}
}
