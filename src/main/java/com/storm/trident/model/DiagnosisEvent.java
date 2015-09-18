package com.storm.trident.model;

import java.io.Serializable;

/**
 * 封装数据的model
 * @author jiaming.jiang
 *
 */
public class DiagnosisEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private double lat;//经度
	private double lng;//纬度
	private long time;//时间
	private String diag;//疾病种类代码
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getDiag() {
		return diag;
	}
	public void setDiag(String diag) {
		this.diag = diag;
	}
	public DiagnosisEvent(double lat, double lng, long time, String diag) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.time = time;
		this.diag = diag;
	}
	public DiagnosisEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DiagnosisEvent [lat=" + lat + ", lng=" + lng + ", time=" + time
				+ ", diag=" + diag + "]";
	}
}
