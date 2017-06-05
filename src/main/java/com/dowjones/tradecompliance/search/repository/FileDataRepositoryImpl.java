package com.dowjones.tradecompliance.search.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dowjones.tradecompliance.search.configuration.ElasticSearchConfig;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;


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
	public FileQueryResults getFiles(FileSearchableData searchableData) throws Exception {

		return null;
	}

	


}
