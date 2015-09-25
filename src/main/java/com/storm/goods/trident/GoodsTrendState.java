package com.storm.goods.trident;

import storm.trident.state.map.IBackingMap;
import storm.trident.state.map.NonTransactionalMap;

public class GoodsTrendState extends NonTransactionalMap<Long>{

	protected GoodsTrendState(IBackingMap<Long> backing) {
		super(backing);
	}

}
