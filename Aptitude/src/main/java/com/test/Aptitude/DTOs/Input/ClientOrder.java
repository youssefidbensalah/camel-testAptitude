package com.test.Aptitude.DTOs.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrder {

    private int id;
    private String message_type;
    private String creation;
    private int exported;
    private Contenu contenu;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClientOrder {\n");
        sb.append("  id: ").append(id).append(",\n");
        sb.append("  message_type: '").append(message_type).append("',\n");
        sb.append("  creation: '").append(creation).append("',\n");
        sb.append("  exported: ").append(exported).append(",\n");

        if (contenu != null) {
            sb.append("  contenu: {\n");
            sb.append("    type_message: '").append(contenu.getType_message()).append("',\n");
            sb.append("    id: ").append(contenu.getId()).append(",\n");

            if (contenu.getFournisseur() != null) {
                sb.append("    fournisseur: {\n");
                sb.append("      code: '").append(contenu.getFournisseur().code()).append("',\n");
                sb.append("      nom: '").append(contenu.getFournisseur().nom()).append("',\n");
                sb.append("      telephone: '").append(contenu.getFournisseur().telephone()).append("'\n");
                sb.append("    },\n");
            }

            if (contenu.getSite_reception() != null) {
                sb.append("    site_reception: {\n");
                sb.append("      site_id: ").append(contenu.getSite_reception().site_id()).append(",\n");
                sb.append("      nom_site: '").append(contenu.getSite_reception().nom_site()).append("',\n");
                sb.append("      telephone: '").append(contenu.getSite_reception().telephone()).append("'\n");
                sb.append("    },\n");
            }

            sb.append("    numero_commande: '").append(contenu.getNumero_commande()).append("',\n");
            sb.append("    numero_livraison: '").append(contenu.getNumero_livraison()).append("',\n");
            sb.append("    statut: '").append(contenu.getStatut()).append("',\n");
            sb.append("    creation: '").append(contenu.getCreation()).append("',\n");
            sb.append("    modification: '").append(contenu.getModification()).append("',\n");
            sb.append("    date_reception: '").append(contenu.getDate_reception()).append("',\n");

            sb.append("    lignes: [\n");
            if (contenu.getLignes() != null && contenu.getLignes().size() > 0) {
                for (Ligne ligne : contenu.getLignes()) {
                    sb.append("      {\n");
                    sb.append("        id: '").append(ligne.getId()).append("',\n");
                    sb.append("        code_produit: '").append(ligne.getCode_produit()).append("',\n");
                    sb.append("        libelle_fr: '").append(ligne.getLibelle_fr()).append("',\n");
                    sb.append("        quantite: ").append(ligne.getQuantite()).append(",\n");
                    sb.append("        unite: '").append(ligne.getUnite()).append("',\n");
                    sb.append("        lieu: ").append(ligne.getLieu()).append(",\n");
                    sb.append("        nb_jour_dlc_apres_decongelation: ").append(ligne.getNb_jour_dlc_apres_decongelation()).append(",\n");
                    sb.append("        nb_jour_dlv: ").append(ligne.getNb_jour_dlv()).append(",\n");
                    sb.append("        nb_jour_blocage: ").append(ligne.getNb_jour_blocage()).append(",\n");
                    sb.append("        fragile: ").append(ligne.isFragile()).append(",\n");
                    sb.append("        numero_lot: '").append(ligne.getNumero_lot()).append("',\n");
                    sb.append("        dlc: '").append(ligne.getDlc()).append("',\n");
                    sb.append("        categorie: '").append(ligne.getCategorie()).append("'\n");
                    sb.append("      },\n");
                }
            } else {
                sb.append("      (aucune ligne)\n");
            }
            sb.append("    ]\n");
            sb.append("  }\n");
        } else {
            sb.append("  contenu: null\n");
        }

        sb.append("}");
        return sb.toString();
    }


}

