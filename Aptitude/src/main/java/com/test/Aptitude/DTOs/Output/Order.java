package com.test.Aptitude.DTOs.Output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order  {
    private int id;
    private String contact_id;
    private String reference;


    private String  datevalidationprovider;
    private String  dateorder;
    private String datereceive_estimated;
    private int branchs_id;
    private String user_text_5;
    private String weight;
    private String branch_name;
    private BigDecimal quantity;
    private BigDecimal quantityreceive;
    private String contact_name;
    private OrderItem[] items;


    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order {\n");
        sb.append("  id: ").append(id).append(",\n");
        sb.append("  contact_id: ").append(contact_id).append(",\n");
        sb.append("  reference: '").append(reference).append("',\n");
        sb.append("  datevalidationprovider: ").append(datevalidationprovider).append(",\n");
        sb.append("  dateorder: '").append(dateorder).append("',\n");
        sb.append("  datereceive_estimated: '").append(datereceive_estimated).append("',\n");
        sb.append("  branchs_id: ").append(branchs_id).append(",\n");
        sb.append("  user_text_5: '").append(user_text_5).append("',\n");
        sb.append("  weight: ").append(weight).append(",\n");
        sb.append("  branch_name: '").append(branch_name).append("',\n");
        sb.append("  quantity: ").append(quantity).append(",\n");
        sb.append("  quantityreceive: ").append(quantityreceive).append(",\n");
        sb.append("  contact_name: '").append(contact_name).append("',\n");

        sb.append("  items: [\n");
        if (items != null && items.length > 0) {
            for (OrderItem item : items) {
                sb.append("    {\n");
                sb.append("      id: '").append(item.getId()).append("',\n");
                sb.append("      idpurchaseorder: ").append(item.getIdpurchaseorder()).append(",\n");
                sb.append("      idproducts: '").append(item.getIdproducts()).append("',\n");
                sb.append("      quantityorder: ").append(item.getQuantityorder()).append(",\n");
                sb.append("      branchs_id: ").append(item.getBranchs_id()).append("\n");
                sb.append("    }\n");
            }
        } else {
            sb.append("    (aucun article)\n");
        }
        sb.append("  ]\n");

        sb.append("}");
        return sb.toString();
    }

}

