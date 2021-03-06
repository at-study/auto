package redmine.api.implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import redmine.Property;
import redmine.api.interfaces.HttpMethods;
import redmine.api.interfaces.Request;

@Getter
public class RestRequest implements Request {
    private String uri;
    private HttpMethods method;
    private Map<String, String> parameters;
    private Map<String, String> headers;
    private Object body;

    public RestRequest(String uri, HttpMethods method, Map<String, String> parameters, Map<String, String> headers, Object body) {
        Objects.requireNonNull(uri, "В запросе должен быть uri");
        Objects.requireNonNull(method, "В запросе должен быть указан метод запроса");
        String baseUri = Property.getStringProperty("apiHost");
        this.uri = baseUri + uri;
        this.method = method;
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        this.parameters = parameters;
        if (headers == null) {
            headers = new HashMap<>();
        }
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(" ").append(uri);
        if (parameters.size() > 0) {
            sb.append("?");
            parameters.forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(System.lineSeparator());
        headers.forEach((key, value) -> sb.append(key).append("=").append(value).append(System.lineSeparator()));
        sb.append(System.lineSeparator());
        if (body != null) {
            sb.append(body.toString());
        }
        return sb.toString();
    }
}
