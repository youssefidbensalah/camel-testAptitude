package com.test.Aptitude.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonFileLoaderProcessor implements Processor {


    private final String filePath;

    public JsonFileLoaderProcessor(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String json;
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new FileNotFoundException("Le fichier " + filePath + " est introuvable !");
            }
            json = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
        exchange.getIn().setBody(json);
    }
}
