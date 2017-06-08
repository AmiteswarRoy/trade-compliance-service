package com.dowjones.tradecompliance.search.repository.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;

public class QueryComponents {
	
	private BoolQueryBuilder boolQuery;
	private SortBuilder sortBuilder;
	private List<String> sources;
	
	public QueryComponents() {
		this.boolQuery = QueryBuilders.boolQuery();
		this.sources = new ArrayList<String>();
	}

	public BoolQueryBuilder getBoolQuery() {
		return boolQuery;
	}

	public void setBoolQuery(BoolQueryBuilder boolQuery) {
		this.boolQuery = boolQuery;
	}

	public SortBuilder getSortBuilder() {
		return sortBuilder;
	}

	public void setSortBuilder(SortBuilder sortBuilder) {
		this.sortBuilder = sortBuilder;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}
	
	

}
