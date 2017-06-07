package com.dowjones.tradecompliance.search.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {
	private String serverUri;
	private Integer connectTimeOut;
	private Integer readTimeOut;
	private String index;
	private String clusterName;
	private String fileType;
	private Integer maxResultsToFetch;
	private Boolean onInsertReplaceExisting;
	private Integer scrollSize;
	private String scrollTime;
	public String getServerUri() {
		return serverUri;
	}
	public void setServerUri(String serverUri) {
		this.serverUri = serverUri;
	}
	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}
	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}
	public Integer getReadTimeOut() {
		return readTimeOut;
	}
	public void setReadTimeOut(Integer readTimeOut) {
		this.readTimeOut = readTimeOut;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getMaxResultsToFetch() {
		return maxResultsToFetch;
	}
	public void setMaxResultsToFetch(Integer maxResultsToFetch) {
		this.maxResultsToFetch = maxResultsToFetch;
	}
	public Integer getScrollSize() {
		return scrollSize;
	}
	public void setScrollSize(Integer scrollSize) {
		this.scrollSize = scrollSize;
	}
	public String getScrollTime() {
		return scrollTime;
	}
	public void setScrollTime(String scrollTime) {
		this.scrollTime = scrollTime;
	}
	public Boolean getOnInsertReplaceExisting() {
		return onInsertReplaceExisting;
	}
	public void setOnInsertReplaceExisting(Boolean onInsertReplaceExisting) {
		this.onInsertReplaceExisting = onInsertReplaceExisting;
	}

	
	/* @Bean
	    public Client client() throws Exception {

	        Settings esSettings = Settings.settingsBuilder()
	                .put("cluster.name", getClusterName())
	                .build();

	        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
	        return TransportClient.builder()
	                .settings(esSettings)
	                .build()
	                .addTransportAddress(
					  new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9400));
	    }

	    @Bean
	    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
	        return new ElasticsearchTemplate(client());
	    }*/
}
