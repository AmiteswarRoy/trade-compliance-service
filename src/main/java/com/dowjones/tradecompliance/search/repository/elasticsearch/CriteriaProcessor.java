package com.dowjones.tradecompliance.search.repository.elasticsearch;

import java.util.List;

public interface CriteriaProcessor<T> {
	
	boolean hasCriteria(T criteria, List<String> invalidCriteria);
	void processCriteria(T criteria, QueryComponents components);

}
