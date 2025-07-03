package com.test.Aptitude.processors;

import com.test.Aptitude.DTOs.Input.ClientOrder;
import com.test.Aptitude.DTOs.Output.Produit;
import com.test.Aptitude.Mapper.FromInputToOutput;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        ClientOrder commande = exchange.getIn().getBody(ClientOrder.class);

        List<Produit> produits = commande.getContenu().getLignes().stream()
                .map(ligne -> new Produit(
                        ligne.getCode_produit(),
                        ligne.getLibelle_fr(),
                        ligne.getQuantite().doubleValue(),
                        commande.getContenu().getFournisseur().nom()
                ))
                .collect(Collectors.toList());

        exchange.getIn().setBody(produits);
    }
}
