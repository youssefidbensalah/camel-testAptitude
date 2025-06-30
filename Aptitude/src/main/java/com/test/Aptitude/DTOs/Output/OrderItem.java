package com.test.Aptitude.DTOs.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {

    private String id;
    private int idpurchaseorder;
    private String idproducts;
    private BigDecimal quantityorder;
    private int branchs_id;

}

