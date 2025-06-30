package com.test.Aptitude.processors;

import com.test.Aptitude.DTOs.Input.ClientOrder;
import com.test.Aptitude.Mapper.FromInputToOutput;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandeProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        // Récupération de l'objet ClientOrder depuis le corps de l'échange
        ClientOrder clientOrder = exchange.getIn().getBody(ClientOrder.class);


        List<Object> result = new ArrayList<>();
        result.add(FromInputToOutput.mapToOrder(clientOrder));
        result.addAll(Arrays.asList(FromInputToOutput.mapToOrderItems(clientOrder)));

        exchange.getIn().setBody(result);
    }
}
