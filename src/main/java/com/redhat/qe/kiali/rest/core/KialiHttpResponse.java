package com.redhat.qe.kiali.rest.core;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.redhat.qe.kiali.rest.core.KialiHttpClient.STATUS_CODE;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Getter
@ToString
@Builder
@Slf4j
public class KialiHttpResponse {

    public static KialiHttpResponse get(URI uri, HttpResponse response)
            throws UnsupportedOperationException, IOException {
        _logger.debug("{}", response);
        KialiHttpResponse mcResponse = KialiHttpResponse.builder()
                .uri(uri)
                .responseCode(response.getStatusLine().getStatusCode())
                .headers(response.getAllHeaders())
                .build();
        if (response.getStatusLine().getStatusCode() != STATUS_CODE.NO_CONTENT.getCode()) {
            mcResponse.entity = IOUtils.toString(response.getEntity().getContent());
        }
        return mcResponse;
    }

    private URI uri;
    private String entity;
    private Integer responseCode;
    private Header[] headers;

    private String exception;
}