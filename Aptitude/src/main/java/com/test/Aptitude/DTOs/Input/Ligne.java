package com.test.Aptitude.DTOs.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ligne {
    private String id;
    private String code_produit;
    private String libelle_fr;
    private BigDecimal quantite;
    private String unite;
    private int lieu;
    private int nb_jour_dlc_apres_decongelation;
    private int nb_jour_dlv;
    private int nb_jour_blocage;
    private boolean fragile;
    private String numero_lot;
    private String dlc;
    private String categorie;

}
