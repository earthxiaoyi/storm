package com.storm.trident;

import java.io.Serializable;

import storm.trident.spout.ITridentSpout.BatchCoordinator;

public class DefautCoordinator implements BatchCoordinator<Long>,Serializable{

	private static final long serialVersionUID = 1L;

	public Long initializeTransaction(long txid, Long prevMetadata,
			Long currMetadata) {
		return null;
	}

	public void success(long txid) {
		System.out.println("Successful transaction["+txid+"]");
	}

	public boolean isReady(long txid) {
		return true;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}
