package com.dowjones.tradecompliance.search.util;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dowjones.tradecompliance.search.domain.FileData;
import com.dowjones.tradecompliance.search.domain.TradeItem;

/**
 * @Description To convert the response from ElasticSearch to required format
 * @author Infosys
 * 
 * */
public class ConversionService {

	private static Logger logger = LogManager.getLogger(ConversionService.class);
	
	public static FileData convertTradeItemToFileData(TradeItem tradeItem) {
		logger.debug("Inside Conversion Method");
		FileData fileData = new FileData();

		if(StringUtils.isNotEmpty(tradeItem.getItem_code()))
			fileData.setItem_code(tradeItem.getItem_code());
		else
			fileData.setItem_code(null);
		
		if(StringUtils.isNotEmpty(tradeItem.getItem_description()))
			fileData.setItem_description(tradeItem.getItem_description());
		else
			fileData.setItem_description(null);
		
		if(StringUtils.isNotEmpty(tradeItem.getMatch_phrase()))
			fileData.setMatch_phrase(tradeItem.getMatch_phrase());
		else
			fileData.setMatch_phrase(null);
		
		if(StringUtils.isNotEmpty(tradeItem.getTc_goods_codes()))
			fileData.setTc_goods_codes(tradeItem.getTc_goods_codes());
		else
			fileData.setTc_goods_codes(null);
		
		return fileData;
	}
}
