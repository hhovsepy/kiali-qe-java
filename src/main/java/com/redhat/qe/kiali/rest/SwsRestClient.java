package com.redhat.qe.kiali.rest;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redhat.qe.kiali.model.Namespace;
import com.redhat.qe.kiali.model.Services;
import com.redhat.qe.kiali.rest.core.SwsHeader;
import com.redhat.qe.kiali.rest.core.SwsHttpClient;
import com.redhat.qe.kiali.rest.core.SwsHttpResponse;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

public class SwsRestClient extends SwsHttpClient {

    private String baseUrl;
    private String username;
    private String password;

    private SwsHeader header;

    public SwsRestClient(String baseUrl, String username, String password) {
        this(baseUrl, username, password, TRUST_HOST_TYPE.DEFAULT);
    }

    public SwsRestClient(String baseUrl, String username, String password, TRUST_HOST_TYPE trustHostType) {
        super(trustHostType == null ? TRUST_HOST_TYPE.DEFAULT : trustHostType);
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        } else {
            this.baseUrl = baseUrl;
        }
        this.username = username;
        this.password = password;
        initClient();
    }

    private void initClient() {
        header = SwsHeader.getDefault();
        header.addJsonContentType();
        header.addAuthorization(username, password);
    }

    @SuppressWarnings("unchecked")
    public List<Namespace> namespaces() {
        SwsHttpResponse response = doGet(baseUrl + "/api/namespaces", header, STATUS_CODE.OK.getCode());
        return (List<Namespace>) readValue(response.getEntity(),
                collectionResolver().get(List.class, Namespace.class));
    }

    public String queryRaw(String path) {
        return doGet(path, null).getEntity();
    }

    public String rawPost(String path, HashMap<String, Object> entity) {
        SwsHttpResponse response = doPost(baseUrl + "/api/namespaces", header, toJsonString(entity),
                STATUS_CODE.OK.getCode());
        return (String) readValue(response.getEntity(), simpleResolver().get(String.class));
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> serviceMetrics(String namespace, String service,
            Map<String, Object> queryParameters) {
        SwsHttpResponse response = doGet(
                baseUrl + MessageFormat.format("/api/namespaces/{0}/services/{1}/metrics", namespace, service),
                header, STATUS_CODE.OK.getCode());
        return (Map<String, Object>) readValue(response.getEntity(),
                mapResolver().get(Map.class, String.class, Object.class));
    }

    public Services services(String namespace) {
        SwsHttpResponse response = doGet(baseUrl + MessageFormat.format("/api/namespaces/{0}/services", namespace),
                header, STATUS_CODE.OK.getCode());
        return (Services) readValue(response.getEntity(), simpleResolver().get(Services.class));
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> status() {
        SwsHttpResponse response = doGet(baseUrl + "/api/status", header, STATUS_CODE.OK.getCode());
        return (Map<String, String>) readValue(response.getEntity(),
                mapResolver().get(Map.class, String.class, String.class));
    }

}
