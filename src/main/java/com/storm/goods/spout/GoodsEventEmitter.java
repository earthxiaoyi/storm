package com.storm.goods.spout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout.Emitter;
import storm.trident.topology.TransactionAttempt;

import com.storm.goods.model.ClickInfo;

public class GoodsEventEmitter implements Emitter<Long>,Serializable{

	private static final long serialVersionUID = 1L;

	AtomicInteger atomic = new AtomicInteger(0);
	
	public void emitBatch(TransactionAttempt tx, Long coordinatorMeta,
			TridentCollector collector) {
		
		for(int i=1;i<10;i++){
			List<Object> goodsList = new ArrayList<Object>();
			
			ClickInfo goods = new ClickInfo();
			
			goods.setClickNum(1L);
			goods.setGoodsName("鞋子");
			goods.setGoodsType("A");
			goods.setId(123L);
			
			goodsList.add(goods);
			collector.emit(goodsList);
		}
		
	}

	public void success(TransactionAttempt tx) {
		atomic.incrementAndGet();
	}

	public void close() {
	}
	
}
