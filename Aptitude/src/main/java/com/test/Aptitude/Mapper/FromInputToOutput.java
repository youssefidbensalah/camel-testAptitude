package com.test.Aptitude.Mapper;

import com.test.Aptitude.DTOs.Input.ClientOrder;
import com.test.Aptitude.DTOs.Input.Contenu;
import com.test.Aptitude.DTOs.Input.Ligne;
import com.test.Aptitude.DTOs.Output.Order;
import com.test.Aptitude.DTOs.Output.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class FromInputToOutput {

//    Mapping
//🔹 Order :
//    clientOrder.contenu.id → order.id
//    clientOrder.contenu.fournisseur.code → order.contact_id
//    clientOrder.contenu.fournisseur.nom → order.contact_name
//    clientOrder.contenu.site_reception.id → order.branchs_id
//    clientOrder.contenu.site_reception.nom_site → order.branch_name
//    clientOrder.contenu.type_message → order.user_text_5
//    clientOrder.contenu.numero_commande → order.reference
//    clientOrder.contenu.creation → order.date_order
//    clientOrder.contenu.date_reception → order.datereceive_estimated
//    clientOrder.contenu.lignes → order.items (liste d’objets OrderItem)
//
//            🔹 OrderItem :
//    clientOrder.contenu.lignes[].id → orderItem.id
//    clientOrder.contenu.id → orderItem.idpurchaseorder
//    clientOrder.contenu.lignes[].code_produit → orderItem.idproducts
//    clientOrder.contenu.lignes[].quantite → orderItem.quantityorder
//    clientOrder.contenu.site_reception.id → orderItem.branchs_id


    public static OrderItem[] mapToOrderItems(ClientOrder clientOrder) {
        return clientOrder.getContenu().getLignes().stream()
                .map(ligne -> new OrderItem(
                        String.valueOf(ligne.getId()),
                        clientOrder.getContenu().getId(),
                        ligne.getCode_produit(),
                        calculateTotalQuantity(ligne.getQuantite()),
                        clientOrder.getContenu().getSite_reception().site_id()
                ))
                .toArray(OrderItem[]::new);
    }

    public static Order mapToOrder(ClientOrder clientOrder) {
        return new Order(
                clientOrder.getContenu().getId(),
                clientOrder.getContenu().getFournisseur().code(),
                clientOrder.getContenu().getNumero_commande(),
                null, // datevalidationprovider is not provided in the input
                convertDateToIsoOffset(clientOrder.getContenu().getCreation()),
                convertDateToIsoOffset(clientOrder.getContenu().getDate_reception().atStartOfDay()),
                clientOrder.getContenu().getSite_reception().site_id(),
                clientOrder.getContenu().getType_message(),
                "0.000000", // weight is not provided in the input
                clientOrder.getContenu().getSite_reception().nom_site(),
                calculateTotalQuantity(clientOrder.getContenu()),
                calculateTotalQuantity(clientOrder.getContenu()),
                clientOrder.getContenu().getFournisseur().nom(),
                mapToOrderItems(clientOrder)
        );
    }

    // Méthode utilitaire simple pour la conversion
    private static String convertDateToIsoOffset(LocalDateTime localDateTime) {
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        return offsetDateTime.format(outputFormatter);
    }
    private static BigDecimal calculateTotalQuantity(Contenu contenu) {
        // Calculer la somme des quantités des lignes
        return contenu.getLignes().stream()
                .map(Ligne::getQuantite)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(6, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateTotalQuantity(BigDecimal num) {
        // Calculer la somme des quantités des lignes
        return num.setScale(6, RoundingMode.HALF_UP);
    }


}
