package com.dowjones.tradecompliance.search.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.configuration.ElasticSearchConfig;
import com.dowjones.tradecompliance.search.domain.DataEntity;
import com.dowjones.tradecompliance.search.domain.ErrorEntity;
import com.dowjones.tradecompliance.search.domain.FileData;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.MetaData;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.repository.elasticsearch.EsFileQueryBuilder;
import com.dowjones.tradecompliance.search.util.ConversionService;
import com.dowjones.tradecompliance.search.util.ItemConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * 
 * @Description Repository implementation to perform crud operations on elastic search
 * @author Infosys
 *
 */
@Repository
@Qualifier("FileDataRepositoryImpl")
public class FileDataRepositoryImpl implements FileDataRepository{

	private static Logger logger = LogManager.getLogger(FileDataRepositoryImpl.class);
	private ElasticSearchConfig config;
	private Gson gson;
	private JestClient client;
	
	@Autowired
	private EsFileQueryBuilder fileQueryBuilder;
	
	public FileDataRepositoryImpl(@Autowired
			ElasticSearchConfig cfg) {
		config=cfg;
		gson = new GsonBuilder().create();
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(config.getServerUri())
				.readTimeout(config.getReadTimeOut())
				.connTimeout(config.getConnectTimeOut())
				.multiThreaded(true)
				.gson(gson)
				.build());
		client = factory.getObject();
		
	}

	/**
	 * 
	 * @Description Search files from elastic search based on criteria 
	 * @param FileSearchableData
	 * @return FileQueryResults
	 * @exception java.lang.Exception
	 */
	@Override
	@EnableInstrumentation
	public ResponseResult searchFiles(FileSearchableData searchableData) throws Exception {
		logger.debug("Inside search file method to connect to elastic search");
		ResponseResult results = new ResponseResult();
		String queryString = fileQueryBuilder.buildFileSearchQuery(searchableData).toString();
		
		Search search = new Search.Builder(queryString).addIndex(config.getIndex()).addType(config.getFileType()).build();
		SearchResult searchResult = client.execute(search);
		
		if(!searchResult.isSucceeded()) {
			logger.error("Search Failed from elastic search");
			ErrorEntity errorEntity = new ErrorEntity();
			
			errorEntity.setStatus(Integer.toString(searchResult.getResponseCode()));
			errorEntity.setDetail(searchResult.getErrorMessage());
			
			throw new IllegalStateException("The search failed : "+searchResult.getJsonString());
		}
		
		if(StringUtils.isEmpty(searchResult.getErrorMessage()) && StringUtils.isBlank(searchResult.getErrorMessage())) {
			results = populateSearchResults(searchResult);
			
		} else { 
			logger.error("File Search failed : "+searchResult.getErrorMessage());
		}
		return results;
	}

	/**
	 * 
	 * @Description Connect to elastic search and create trade item
	 * @param Trade Item
	 * @return ItemResponse
	 * @exception java.lang.Exception
	 */
	@Override
	@EnableInstrumentation
	public ItemResponse createFile(TradeItem item) throws Exception {
		ItemResponse response = new ItemResponse();
		if (item != null) {
			try{
				String fileJson = gson.toJson(item);
				Index index = new Index.Builder(fileJson)
						.index(config.getIndex())
						.type(config.getFileType())
						.refresh(true)
						.build();
				logger.debug("Index "+index.getURI());
				JestResult result = client.execute(index);
				if (result.isSucceeded()) {
					response.setMessage(ItemConstants.ITEM_CREATION_SUCCESS);
					return response;
				}else{
					logger.error("Error while creating item");
					return null;
				}
			}catch(Exception e){
				logger.error("Error while creating Trade Item in Elastic - "+e);
				response.setMessage(ItemConstants.ITEM_CREATION_ERROR +" "+e.getMessage());
				throw e;
			}
			
		} else {
			logger.error("Item cannot be null.");
			return null;
		}
	}
	
	/**
	 * 
	 * @Description Connect to elastic search and create bulk trade items
	 * @param List of trade Items
	 * @return ItemResponse
	 * @exception java.lang.Exception
	 * 
	 */
	@Override
	@EnableInstrumentation
	public ItemResponse createBulkFiles(List<TradeItem> files) throws Exception {
		ItemResponse response = new ItemResponse();
		if (files != null) {
			List<Index> indexToInsert = new ArrayList<Index>();
			try{
				for(TradeItem file:files){
					String fileJson = gson.toJson(file);
					logger.debug("input json -"+fileJson);
					Index index = new Index.Builder(fileJson)
							.index(config.getIndex())
							.type(config.getFileType())
							.refresh(true)
							.build();
					indexToInsert.add(index);
				}
				
				logger.info(" No.of Docs - "+indexToInsert.size());
				Bulk bulk = new Bulk.Builder().defaultIndex(config.getIndex())
							.defaultType(config.getFileType())
							.addAction(indexToInsert).build();
				JestResult result = client.execute(bulk);
				if (result.isSucceeded()) {
					response.setMessage(ItemConstants.ITEM_CREATION_SUCCESS);
					return response;
				}else{
					logger.error("Error while creating Bulk Trade Items");
					return null;
				}
			}catch(Exception e){
				logger.error("Error while creating Bulk Trade Items in Elastic - "+e);
				//response.setMessage(ItemConstants.ITEM_CREATION_ERROR+" " +e.getMessage());
				throw e;
			}
			
		} else {
			logger.error("Item cannot be null.");
			return null;
		}
	}
	
	/**
	 * @Description Delete all items from index
	 * @param 
	 * @return return status of delete operation
	 * @exception java.lang.Exception
	 */
	@Override
	@EnableInstrumentation
	public ItemResponse deleteAllItems() throws Exception{
		ItemResponse response = new ItemResponse();
		String query = gson.toJson(ItemConstants.DELETE_ALL_QUERY);
		
		try{
			DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(query)
											.addIndex(config.getIndex())
											.addType(config.getFileType())
											.build();
			JestResult result = client.execute(deleteByQuery);
			logger.debug("Status - "+result.isSucceeded());
			logger.info("Deleted count - "+result.getValue("deleted"));
			if(result.isSucceeded()){
				response.setMessage(result.getValue("deleted") + " "+ ItemConstants.DELETE_SUCCESS);
				return response;
			}else{
				logger.error("Error while deleting items");
				return null;
			}
			
		}catch(Exception e){
			logger.error("Error while deleting items"+e);
			response.setMessage(ItemConstants.DELETE_FAILURE +" " +e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 
	 * @Description Convert the response from Elastic Search to required format
	 * @param SearchResult
	 * @return FileQueryResults
	 * @exception java.lang.Exception
	 */
	private ResponseResult populateSearchResults(SearchResult searchResult) {
		ResponseResult results = new ResponseResult();
		MetaData meta = new MetaData();
		List<DataEntity> data = new ArrayList<DataEntity>();
		
		meta.setHits(searchResult.getTotal());
		List<SearchResult.Hit<TradeItem, Void>> hitsFromSearch = searchResult.getHits(TradeItem.class);
		
		for(SearchResult.Hit<TradeItem, Void> singleHitValue : hitsFromSearch) {
			DataEntity dataEntity = new DataEntity();
			
			dataEntity.setId(singleHitValue.id);
			dataEntity.setType(ItemConstants.TYPE_GOODS);
			dataEntity.setAttributes(ConversionService.convertTradeItemToFileData(singleHitValue.source));
			dataEntity.setRelationships(null);
			
			data.add(dataEntity);
			
		}
		
		results.setData(data);
		results.setMeta(meta);
		
		return results;
		
	}

}
