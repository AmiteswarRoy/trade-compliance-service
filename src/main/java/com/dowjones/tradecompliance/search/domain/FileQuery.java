package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class FileQuery {
	private String id;
	private List<String> inIds;
	private String keywords;
	private String keywordLogicCondition;
	private String name;
	private String type;
	private List<String> types;
	private Long fromSize;
	private Long toSize;
	private String createdBy;
	private Long fromCreatedDate;
	private Long toCreatedDate;
	private String docType;
	private String docUri;
	private Long fromUpdatedDate;
	private Long toUpdatedDate;
	private String bibAuthor;
	private String bibTitle;
	private String bibDescription;
	private Integer fromBibPageCount;
	private Integer toBibPageCount;
	private String privacy;
	private String ieeePrivacy;
	private String nonIeeeCopyRight;
	private String group;
	private List<String> groups;
	private String tag;
	private String source;
	private String status;
	private List<String> statuses;
	
	private String sortKey;
	private String sortOrder;
	private Integer fromItem;
	private Integer toItem;
	private Boolean fetchAllDocuments;
	private List<String> fetchAttributes;
	
	public FileQuery() { }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getKeywordLogicCondition() {
		return keywordLogicCondition;
	}
	public void setKeywordLogicCondition(String keywordLogicCondition) {
		this.keywordLogicCondition = keywordLogicCondition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public Long getFromSize() {
		return fromSize;
	}
	public void setFromSize(Long fromSize) {
		this.fromSize = fromSize;
	}
	public Long getToSize() {
		return toSize;
	}
	public void setToSize(Long toSize) {
		this.toSize = toSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getFromCreatedDate() {
		return fromCreatedDate;
	}
	public void setFromCreatedDate(Long fromCreatedDate) {
		this.fromCreatedDate = fromCreatedDate;
	}
	public Long getToCreatedDate() {
		return toCreatedDate;
	}
	public void setToCreatedDate(Long toCreatedDate) {
		this.toCreatedDate = toCreatedDate;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocUri() {
		return docUri;
	}
	public void setDocUri(String docUri) {
		this.docUri = docUri;
	}
	public Long getFromUpdatedDate() {
		return fromUpdatedDate;
	}
	public void setFromUpdatedDate(Long fromUpdatedDate) {
		this.fromUpdatedDate = fromUpdatedDate;
	}
	public Long getToUpdatedDate() {
		return toUpdatedDate;
	}
	public void setToUpdatedDate(Long toUpdatedDate) {
		this.toUpdatedDate = toUpdatedDate;
	}
	public String getBibAuthor() {
		return bibAuthor;
	}
	public void setBibAuthor(String bibAuthor) {
		this.bibAuthor = bibAuthor;
	}
	public String getBibTitle() {
		return bibTitle;
	}
	public void setBibTitle(String bibTitle) {
		this.bibTitle = bibTitle;
	}
	public String getBibDescription() {
		return bibDescription;
	}
	public void setBibDescription(String bibDescription) {
		this.bibDescription = bibDescription;
	}
	public Integer getFromBibPageCount() {
		return fromBibPageCount;
	}
	public void setFromBibPageCount(Integer fromBibPageCount) {
		this.fromBibPageCount = fromBibPageCount;
	}
	public Integer getToBibPageCount() {
		return toBibPageCount;
	}
	public void setToBibPageCount(Integer toBibPageCount) {
		this.toBibPageCount = toBibPageCount;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getNonIeeeCopyRight() {
		return nonIeeeCopyRight;
	}
	public void setNonIeeeCopyRight(String nonIeeeCopyRight) {
		this.nonIeeeCopyRight = nonIeeeCopyRight;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}
	public String getSortKey() {
		return sortKey;
	}
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getFromItem() {
		return fromItem;
	}
	public void setFromItem(Integer fromItem) {
		this.fromItem = fromItem;
	}
	public Integer getToItem() {
		return toItem;
	}
	public void setToItem(Integer toItem) {
		this.toItem = toItem;
	}
	public String getIeeePrivacy() {
		return ieeePrivacy;
	}
	public void setIeeePrivacy(String ieeePrivacy) {
		this.ieeePrivacy = ieeePrivacy;
	}

	public List<String> getInIds() {
		return inIds;
	}
	public void setInIds(List<String> inIds) {
		this.inIds = inIds;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	public List<String> getFetchAttributes() {
		return fetchAttributes;
	}
	public void setFetchAttributes(List<String> fetchAttributes) {
		this.fetchAttributes = fetchAttributes;
	}
	public Boolean getFetchAllDocuments() {
		return fetchAllDocuments;
	}
	public void setFetchAllDocuments(Boolean fetchAllDocuments) {
		this.fetchAllDocuments = fetchAllDocuments;
	}

}
