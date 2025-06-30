package com.test.Aptitude.Routes;

import com.test.Aptitude.DTOs.Output.Order;
import com.test.Aptitude.processors.CommandeProcessor;
import com.test.Aptitude.processors.JsonFileLoaderProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessCommandRoute extends RouteBuilder {

    private final JacksonDataFormat clientOrderJacksonDataFormat;

    public ProcessCommandRoute(JacksonDataFormat clientOrderJacksonDataFormat) {
        this.clientOrderJacksonDataFormat = clientOrderJacksonDataFormat;
    }

    @Override
    public void configure() throws Exception {
        from("timer:readJsonOnce?repeatCount=1")
                .process(new JsonFileLoaderProcessor("static/input.json"))
                .unmarshal(clientOrderJacksonDataFormat)
                .process(new CommandeProcessor())
                .split(body())
                .choice()
                .when(exchange -> exchange.getIn().getBody() instanceof Order)
                .to("direct:traiterCommande")
                .otherwise()
                .to("direct:traiterLignes")
                .end();

        from("direct:traiterCommande")
                .log("Commande reçue : ${body}")
                .to("log:commande");

        from("direct:traiterLignes")
                .log("Lignes de commande reçues : ${body}")
                .to("log:lignes");
    }
}


