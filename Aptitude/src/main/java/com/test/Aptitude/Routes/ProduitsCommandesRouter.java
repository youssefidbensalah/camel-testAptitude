package com.test.Aptitude.Routes;

import com.test.Aptitude.DTOs.Input.ClientOrder;
import com.test.Aptitude.processors.ProductProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ProduitsCommandesRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Réponse à la Quesion 3
        // Route pour récupérer les produits filtrés par catégorie  :  /api/produits
        from("direct:getProduits")
                .routeId("getProduits")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_QUERY, simple("categorie=${header.categorie}"))
                .toD("https://montest.com/api/produits?bridgeEndpoint=true")
                .log("Produits filtrés reçus : ${body}");


        // Route pour récupérer les commandes filtrées par statut : /api/commandes
        from("direct:getCommandes")
                .routeId("getCommandes")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_QUERY, simple("statut=${header.etat}"))
                .toD("https://montest.com/api/commandes?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, ClientOrder[].class)
                .log("Commandes filtrées reçues : ${body}");

        // Réponse à la Quesion 4
        // Route pour créer un produit : /api/products
        from("direct:createProduit")
                .marshal().json(JsonLibrary.Jackson)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("https://montest.com/api/products?bridgeEndpoint=true")
                .log("🆕 Produit envoyé à l’API : ${body}");

        // Réponse à la Quesion 5
        from("timer:runWorkflow?repeatCount=1")
                .routeId("fullCommandeToProductRoute")
                .log("Début du flux : ")
                .to("direct:getCommandes")
                .split(body())
                .process(new ProductProcessor())
                .split(body())
                .to("direct:createProduit")
                .log("Produit traité : ${body}")
                .end()
                .log("Fin du flux complet");
    }
}
