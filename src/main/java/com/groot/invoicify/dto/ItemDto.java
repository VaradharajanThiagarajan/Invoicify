package com.groot.invoicify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String description;
    private Integer rateHourBilled;
    private Float ratePrice;
    private Float flatPrice;
}