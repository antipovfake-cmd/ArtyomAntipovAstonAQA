import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanEchoResponse {

    private EchoContent args;
    private EchoContent data;
    private EchoContent form;
    private Map<String, String> headers;
    private String url;

    // Геттеры и сеттеры
    public EchoContent getArgs() {
        return args;
    }

    public void setArgs(EchoContent args) {
        this.args = args;
    }

    public EchoContent getData() {
        return data;
    }

    public void setData(Object rawData) {
        if (rawData == null) {
            this.data = null;
            return;
        }

        // Если пришла пустая строка "" (как в случае с Form Data), преобразуем в null
        if (rawData instanceof String && ((String) rawData).isEmpty()) {
            this.data = null;
        }
        // Если пришла Мапа (значит, там полноценный JSON-объект), конвертируем её в EchoContent
        else if (rawData instanceof Map) {
            ObjectMapper mapper = new ObjectMapper();
            this.data = mapper.convertValue(rawData, EchoContent.class);
        }
    }


    public EchoContent getForm() {
        return form;
    }

    public void setForm(EchoContent form) {
        this.form = form;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
