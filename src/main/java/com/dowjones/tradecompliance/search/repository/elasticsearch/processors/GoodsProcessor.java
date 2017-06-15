package com.dowjones.tradecompliance.search.repository.elasticsearch.processors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dowjones.tradecompliance.search.domain.SearchCriteria;
import com.dowjones.tradecompliance.search.repository.elasticsearch.CriteriaProcessor;
import com.dowjones.tradecompliance.search.repository.elasticsearch.QueryComponents;
import com.dowjones.tradecompliance.search.util.ItemConstants;

/**
 * 
 * @Deccription Constructing the query builder for goods searchcritera
 * @author swathi_c05
 * 
 * */
@Component
@Qualifier("FileSearchCriteria")
public class GoodsProcessor implements CriteriaProcessor<SearchCriteria> {
	private final String[] fieldNames = { ItemConstants.ITEM_CODE, ItemConstants.ITEM_DESCIPTION,
			ItemConstants.MATCH_PHRASE, ItemConstants.TC_GOODS_CODES };
	
	/**
	 * @Description Validate criteria against null and blank
	 * @param SearchCriteris, List<String>
	 * @return boolean
	 * */
	@Override
	public boolean hasCriteria(SearchCriteria criteria, List<String> invalidCriteria) {
		if(criteria.getGoods()!=null && criteria.getGoods().size()>0) {
			for(String good : criteria.getGoods()) {
				if(StringUtils.isEmpty(good) || StringUtils.isBlank(good)) {
					invalidCriteria.add("Cannot have an empty good code");
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * @Description Construct the multi match query for list of goods
	 * @param SearchCriteris, QueryComponents
	 * @return 
	 * */
	@Override
	public void processCriteria(SearchCriteria criteria, QueryComponents components) {
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		List<MultiMatchQueryBuilder> multiQuery = new ArrayList<MultiMatchQueryBuilder>();
		
		for(String good: criteria.getGoods()) {
			multiQuery.add(QueryBuilders.multiMatchQuery(good, fieldNames).fuzziness("AUTO"));
		}
		
		for(MultiMatchQueryBuilder multiMatchQuery : multiQuery) {
			boolQuery.should(multiMatchQuery);
		}
		
		components.setBoolQuery(boolQuery);
		
	}

}
