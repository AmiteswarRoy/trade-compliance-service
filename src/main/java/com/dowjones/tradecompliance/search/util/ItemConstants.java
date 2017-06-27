package com.dowjones.tradecompliance.search.util;

/**
 * 
 * @Description Constants file to hold reused data
 * @author Infosys
 *
 */
public class ItemConstants {
	
	public static final String ITEM_CREATION_SUCCESS = "Item created successfully.";
	public static final String ITEM_CREATION_FAILURE = "Item creation failed.";
	public static final String ITEM_CREATION_ERROR = "Error while creating Item.";
	
	//Constants for the searchable column names
	public static final String ITEM_CODE = "item_code";
	public static final String ITEM_DESCIPTION = "item_description";
	public static final String MATCH_PHRASE = "match_phrase";
	public static final String GOODS_CODES_1 = "goods_codes_1";
	public static final String GOODS_CODES_2 = "goods_codes_2";
	public static final String GOODS_CODES_3 = "goods_codes_3";
	
	public static final String TYPE_GOODS = "Goods";
	
	public static final String[] SEARCHABLE_FIELDS = { ITEM_CODE, ITEM_DESCIPTION, MATCH_PHRASE, GOODS_CODES_1, GOODS_CODES_2, GOODS_CODES_3 };
	
	public static final String DELETE_ALL_QUERY = "{\"query\":{"
													+ "\"match_all\": {}"
														+ "}"
												+ "}";
	public static final Object DELETE_SUCCESS = "trade items successfully deleted from Index";
	public static final String DELETE_FAILURE = "Failed to delete items from Index";
	
	public static final String INVALID_INPUT = "Please provide valid input. Search parameters cannot be empty!";
	public static final String TYPE_EMPTY = "Type cannot be empty.";
	public static final String SERVER_DOWN = "The application is down. Please try after sometime";
	public static final String NO_ITEMS = "There are no items in input request. Please provide items.";
}
