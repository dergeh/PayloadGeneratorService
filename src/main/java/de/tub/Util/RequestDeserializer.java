package de.tub.Util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.model.SimpleRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RequestDeserializer extends StdDeserializer<SimpleRequest[]> {

    public RequestDeserializer() {
        this(null);
    }

    public RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SimpleRequest[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<SimpleRequest> requests = new LinkedList<>();
        JsonNode fullDoc = jsonParser.getCodec().readTree(jsonParser);
        JsonNode paths = fullDoc.get("paths");
        Iterator<String> stringIterator = paths.fieldNames();
        while (stringIterator.hasNext()) {
            SimpleRequest request = new SimpleRequest();
            String url = stringIterator.next();
            request.setUrl(url);
            String method = paths.get(url).fieldNames().next();
            request.setMethod(method);
            JsonNode body=paths.get(url).findValue("example");
            request.setBody(body);
            requests.add(request);
        }
        SimpleRequest[] ret = new SimpleRequest[requests.size()];
        return requests.toArray(ret);

    }
}
