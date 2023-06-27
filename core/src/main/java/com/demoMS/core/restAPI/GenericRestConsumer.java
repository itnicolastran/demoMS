package com.demoMS.core.restAPI;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GenericRestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericRestConsumer.class);

    private RestTemplate restTemplate;

    public GenericRestConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> executeGetMethod(String uri, HttpEntity httpEntity , Class<T> responseType)
            throws RestClientException {
        LOGGER.debug("executing Get Method");
        validateURI(uri);
        return restTemplate.exchange(uri, HttpMethod.GET, (HttpEntity<?>) httpEntity, responseType);
    }

    public <T> ResponseEntity<T> executePostMethod(String uri, HttpEntity httpEntity , Class<T> responseType)
            throws RestClientException {
        LOGGER.debug("executing Post Method");
        validateURI(uri);
        return restTemplate.exchange(uri, HttpMethod.POST, (HttpEntity<?>) httpEntity, responseType);
    }

    public <T> ResponseEntity<T> executePutMethod(String uri, HttpEntity httpEntity , Class<T> responseType)
            throws RestClientException {
        LOGGER.debug("executing Put Method");
        validateURI(uri);
        return restTemplate.exchange(uri, HttpMethod.PUT, (HttpEntity<?>) httpEntity, responseType);
    }

    public <T> ResponseEntity<T> executeDeleteMethod(String uri, HttpEntity httpEntity , Class<T> responseType)
            throws RestClientException {
        LOGGER.debug("executing Delete Method");
        validateURI(uri);
        return restTemplate.exchange(uri, HttpMethod.POST, (HttpEntity<?>) httpEntity, responseType);
    }

    private void validateURI(String restURI) throws RestClientException {
        if(StringUtils.isBlank(restURI)) throw new RestClientException("REST URI is invalid!.");
    }
}
