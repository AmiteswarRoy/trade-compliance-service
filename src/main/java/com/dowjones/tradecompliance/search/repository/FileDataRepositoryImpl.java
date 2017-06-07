package com.dowjones.tradecompliance.search.repository;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dowjones.tradecompliance.search.configuration.ElasticSearchConfig;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemCreationResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.util.ItemConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;

/**
 * 
 * @Description Repository implementation to perform crud operations on elastic search
 * @author Infosys
 *
 */
@Repository
@Qualifier("FileDataRepositoryImpl")
public class FileDataRepositoryImpl implements FileDataRepository{
	//@Autowired
	ElasticSearchConfig config;
	private Gson gson;
	private JestClient client;
	
	public FileDataRepositoryImpl(@Autowired
			ElasticSearchConfig cfg) {
		config=cfg;
		gson = new GsonBuilder().create();
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(config.getServerUri())
				.multiThreaded(true)
				.gson(gson)
				.build());
		client = factory.getObject();
		
		
	}

	@Override
	public FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception {

		return null;
	}

	/**
	 * 
	 * @Description Connect to elastic search and create trade item
	 * @param Trade Item
	 * @author Infosys
	 * 
	 */
	@Override
	public ItemCreationResponse createFile(TradeItem item) throws Exception {
		ItemCreationResponse response = new ItemCreationResponse();
		if (item != null) {
			try{
				String fileJson = gson.toJson(item);
				Index index = new Index.Builder(fileJson)
						.index(config.getIndex())
						.type(config.getFileType())
						.refresh(true)
						.build();
				System.out.println("Index "+index.getURI());
				JestResult result = client.execute(index);
				if (result.isSucceeded()) {
					response.setResponseCode(result.getResponseCode());
					response.setMessage(ItemConstants.ITEM_CREATION_SUCCESS);
					response.setStatus(ItemConstants.SUCCESS);
					return response;
				}else{
					response.setResponseCode(result.getResponseCode());
					response.setMessage(ItemConstants.ITEM_CREATION_FAILURE);
					response.setStatus(ItemConstants.FAILURE);
					return null;
				}
			}catch(Exception e){
				System.out.println("Error while creating Trade Item in Elastic - "+e);
				response.setMessage(ItemConstants.ITEM_CREATION_ERROR);
				response.setStatus(ItemConstants.FAILURE);
				response.setResponseCode(ItemConstants.ERRORCODE);
				return response;
			}
			
		} else {
			System.out.println("Item cannot be null.");
			return null;
		}
	}
	
	/**
	 * 
	 * @Description Connect to elastic search and create bulk trade items
	 * @param List of trade Items
	 * @author Infosys
	 * 
	 */
	@Override
	public ItemCreationResponse createBulkFiles(List<TradeItem> files) throws Exception {
		ItemCreationResponse response = new ItemCreationResponse();
		if (files != null) {
			List<Index> indexToInsert = new ArrayList<Index>();
			try{
				for(TradeItem file:files){
					String fileJson = gson.toJson(file);
					System.out.println("input json -"+fileJson);
					Index index = new Index.Builder(fileJson)
							.index(config.getIndex())
							.type(config.getFileType())
							.refresh(true)
							.build();
					indexToInsert.add(index);
				}
				
				System.out.println(" No.of Docs - "+indexToInsert.size());
				Bulk bulk = new Bulk.Builder().defaultIndex(config.getIndex())
							.defaultType(config.getFileType())
							.addAction(indexToInsert).build();
				JestResult result = client.execute(bulk);
				if (result.isSucceeded()) {
					response.setResponseCode(result.getResponseCode());
					response.setMessage(ItemConstants.ITEM_CREATION_SUCCESS);
					response.setStatus(ItemConstants.SUCCESS);
					return response;
				}else{
					response.setResponseCode(result.getResponseCode());
					response.setMessage(ItemConstants.ITEM_CREATION_FAILURE);
					response.setStatus(ItemConstants.FAILURE);
					return null;
				}
			}catch(Exception e){
				System.out.println("Error while creating Bulk Trade Items in Elastic - "+e);
				response.setMessage(ItemConstants.ITEM_CREATION_ERROR);
				response.setStatus(ItemConstants.FAILURE);
				response.setResponseCode(ItemConstants.ERRORCODE);
				return response;
			}
			
		} else {
			System.out.println("Item cannot be null.");
			return null;
		}
	}

}
