package com.ecommerce.Ecommerce.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Integer id;
    private Integer product_id;
    private Integer user_id;
    private Integer quantity;
    private Double total;
}
