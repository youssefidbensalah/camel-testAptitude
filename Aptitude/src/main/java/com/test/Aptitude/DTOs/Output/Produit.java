package com.test.Aptitude.DTOs.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    private String code;
    private String libelle;
    private double quantite;
    private String fournisseur;
}
