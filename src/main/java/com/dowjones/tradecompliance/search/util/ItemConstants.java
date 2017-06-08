package com.dowjones.tradecompliance.search.util;

/**
 * 
 * @Description Constants file to hold reused data
 * @author Infosys
 *
 */
public class ItemConstants {
	private ItemConstants(){
		
	}
	public static final String ITEM_CREATION_SUCCESS = "Item created successfully.";
	public static final String SUCCESS = "SUCCESS";
	public static final String ITEM_CREATION_FAILURE = "Iteam creation failed.";
	public static final String FAILURE = "FAILURE";
	public static final String ITEM_CREATION_ERROR = "Error while creating Item.";
	public static final int ERRORCODE = 000;
	
	//Constants for the searchable column names
	public static final String ITEM_CODE = "item_code";
	public static final String ITEM_DESCIPTION = "item_description";
	public static final String MATCH_PHRASE = "match_phrase";
	public static final String GOODS_CODES = "goods";
	
	public static final String DELETE_ALL_QUERY = "{\"query\":{"
													+ "\"match_all\": {}"
														+ "}"
												+ "}";
	public static final Object DELETE_SUCCESS = "trade items successfully deleted from Index";
	public static final String DELETE_FAILURE = "Failed to delete items from Index";
}
