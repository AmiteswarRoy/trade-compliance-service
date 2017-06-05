package com.dowjones.tradecompliance.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.FileData;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.repository.FileDataRepository;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("FileSearchService")
public class FileSearchService implements FileSearch {
	//private Logger logger = LogManager.getLogger(FileSearchService.class);
	private static Gson gson = new GsonBuilder().create();

	@Autowired
	@Qualifier("FileDataRepositoryImpl")
	FileDataRepository repository;

	@Override
	@EnableInstrumentation
	public FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception {
		return getAuthorizedResults(searchableData);
	}
	
	private FileQueryResults getAuthorizedResults(FileSearchableData searchableData) throws Exception {
		
		System.out.println("The input searchable data is :"+gson.toJson(searchableData));
		
		FileQueryResults results = new FileQueryResults();
		List<FileData> fileData = new ArrayList<FileData>();
		
		FileData datas = new FileData();

		datas.setItemCode(searchableData.getCriteria().getGoods().get(0));
		datas.setItemDescription("First item description");
		datas.setMatchPhrase("Goods");
		datas.setGoodsCodes1("goodcodes1");
		datas.setGoodsCodes2("goodcodes2");
		datas.setGoodsCodes3("goodcodes3");
		datas.setGoods("goods");
		
		fileData.add(datas);
		
		results.setTotalHits(1);
		results.setFiles(fileData);
		
		//results = repository.getFiles(searchableData);
		
		System.out.println("The result data is :"+gson.toJson(results));
	
		return results;
	}

	

}
