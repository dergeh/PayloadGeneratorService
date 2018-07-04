package de.tub.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tub.model.BodyObject;
import de.tub.model.Metainfo;
import de.tub.model.RichRequest;
import de.tub.model.SimpleRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Override
    public List<RichRequest> generateRequests(BodyObject body) {
        JsonNode blueprint= body.getBlueprint();
        String base= body.getBase();
        int delay= body.getDelay();
        int threads= body.getThreads();
        int variance= body.getVariance();
        List<SimpleRequest> simpleRequests = new LinkedList<>();
        try {
            simpleRequests=generateSimpleRequests(blueprint);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<RichRequest> richRequests = new LinkedList<>();
        for (SimpleRequest sr : simpleRequests) {
            RichRequest request = new RichRequest();
            sr.setUrl(base+sr.getUrl());
            request.setRequest(sr);
            Metainfo meta= new Metainfo();
            meta.setDelay(delay);
            meta.setThreads(threads);
            meta.setVarinace(variance);
            request.setMetaInfo(meta);
            richRequests.add(request);
        }
        return richRequests;

    }

    private List<SimpleRequest> generateSimpleRequests(JsonNode blueprint) throws IOException {
        List<SimpleRequest> requests = new LinkedList<>();
        JsonNode paths = blueprint.get("paths");
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
       return requests;

    }

}
