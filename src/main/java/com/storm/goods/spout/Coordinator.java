package com.storm.goods.spout;

import java.io.Serializable;

import storm.trident.spout.ITridentSpout.BatchCoordinator;

public class Coordinator implements BatchCoordinator<Long>,Serializable{

	private static final long serialVersionUID = 1L;

	
	public Long initializeTransaction(long txid, Long prevMetadata,
			Long currMetadata) {
		// TODO Auto-generated method stub
		return null;
	}

	public void success(long txid) {
		// TODO Auto-generated method stub
	}

	public boolean isReady(long txid) {
		// TODO Auto-generated method stub
		return true;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
