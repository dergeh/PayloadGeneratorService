package de.tub.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.Util.RequestDeserializer;
import de.tub.model.BodyObject;
import de.tub.model.Metainfo;
import de.tub.model.RichRequest;
import de.tub.model.SimpleRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
@Service
public class GeneratorServiceImpl implements GeneratorService {
    private ObjectMapper mapper = new ObjectMapper();

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
        String read=blueprint.toString();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SimpleRequest[].class, new RequestDeserializer(SimpleRequest[].class));
        mapper.registerModule(module);
        return Arrays.asList(mapper.readValue(read, SimpleRequest[].class));
    }

}
