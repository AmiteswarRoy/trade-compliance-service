package com.dowjones.tradecompliance.search.repository.elasticsearch;

import java.util.List;

public interface CriteriaProcessor<T> {
	
	boolean hasCriteria(T data, List<String> invalidCriteria);
	void processCriteria(T data, QueryComponents components);

}
