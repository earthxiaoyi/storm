package com.stormkafka.schema;

import java.util.List;
import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MessageSchema implements Scheme{

	private static final long serialVersionUID = 1L;

	
	public List<Object> deserialize(byte[] ser) {
		try {
			String msg = new String(ser, "UTF-8");
			return new Values(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Fields getOutputFields() {
		return new Fields("msg");
	}

}
