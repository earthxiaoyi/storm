package com.storm.goods.model;

import java.io.Serializable;

public class GoodsInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String goodsName;
	private String goodsType;
	private long clickNum;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	@Override
	public String toString() {
		return "GoodsInfo [id=" + id + ", goodsName=" + goodsName
				+ ", goodsType=" + goodsType + ", clickNum=" + clickNum + "]";
	}
	public GoodsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsInfo(long id, String goodsName, String goodsType, int clickNum) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsType = goodsType;
		this.clickNum = clickNum;
	}
}
