package de.tub.service;

import com.fasterxml.jackson.databind.JsonNode;
import de.tub.model.BodyObject;
import de.tub.model.RichRequest;

import java.util.List;

public interface GeneratorService {

    List<RichRequest> generateRequests(BodyObject body);
}
