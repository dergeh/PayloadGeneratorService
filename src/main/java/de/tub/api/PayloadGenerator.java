package de.tub.api;

import com.fasterxml.jackson.databind.JsonNode;
import de.tub.model.BodyObject;
import de.tub.model.RichRequest;
import de.tub.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PayloadGenerator {


    private GeneratorService generatorService;


    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<RichRequest> generateRequests(@RequestBody BodyObject body) {
        return generatorService.generateRequests(body);
    }

}
