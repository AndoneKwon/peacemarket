package com.hainum.chat.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConf {
    private RestClient restClient = RestClient.builder(new HttpHost("117.17.196.61",9200,"http")).build();

    public RestClient getRestClient(){
        return restClient;
    }
}


