package com.storm.trident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.storm.trident.model.DiagnosisEvent;

import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout.Emitter;
import storm.trident.topology.TransactionAttempt;


/**
 * 发送数据emitter
 * 
 * @author jiaming.jiang
 *
 */
public class DiagnosisEventEmitter implements Emitter<Long>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AtomicInteger successfulTransactions = new AtomicInteger(0);
	
	public void emitBatch(TransactionAttempt tx, Long coordinatorMeta,
			TridentCollector collector) {
		for(int i=0;i<2;i++){
			List<Object> events = new ArrayList<Object>();
			double lat = new Double(-30+(int)(Math.random() * 75));
			double lng = new Double(-120+(int)(Math.random()*70));
			long time = System.currentTimeMillis();
			String diag = new Integer(320+(int)(Math.random()*7)).toString();
			DiagnosisEvent diagEvent = new DiagnosisEvent(lat, lng, time, diag);
			events.add(diagEvent);
			collector.emit(events);
		}
	}

	public void success(TransactionAttempt tx) {
		successfulTransactions.incrementAndGet();
	}

	public void close() {
		
	}

	

}
