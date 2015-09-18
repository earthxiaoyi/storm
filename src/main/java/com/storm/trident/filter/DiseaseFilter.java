package com.storm.trident.filter;

import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

import com.storm.trident.model.DiagnosisEvent;

/**
 * 过滤掉不符合业务的疾病代码
 * @author jiaming.jiang
 *
 */
public class DiseaseFilter extends BaseFilter{

	private static final long serialVersionUID = 1L;

	public boolean isKeep(TridentTuple tuple) {
		DiagnosisEvent diagEvent = (DiagnosisEvent)tuple.getValue(0);
		String diag = diagEvent.getDiag();
		Integer code = Integer.parseInt(diag);
		if(code.intValue()<=322){
			System.out.println("emitting disease ["+code+"]");
			return true;
		}else{
			System.out.println("Filtering disease ["+code+"]");
			return false;
		}
	}

}
