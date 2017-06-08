package com.dowjones.tradecompliance.search.util;

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

		fileData.setGoods(tradeItem.getGoods());
		fileData.setItem_code(tradeItem.getItem_code());
		fileData.setItem_description(tradeItem.getItem_description());
		fileData.setMatch_phrase(tradeItem.getMatch_phrase());
		
		return fileData;
	}
}
