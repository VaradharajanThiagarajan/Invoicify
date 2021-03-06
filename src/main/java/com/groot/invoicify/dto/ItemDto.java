package com.groot.invoicify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemDto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

	private Long itemId;
	private String description;
	private Integer rateHourBilled;
	private Float ratePrice;
	private Float flatPrice;
	private DtoState State;

	/**
	 *
	 * @param itemId
	 * @param description
	 * @param rateHourBilled
	 * @param ratePrice
	 * @param flatPrice
	 */
	public ItemDto(Long itemId, String description, Integer rateHourBilled, Float ratePrice, Float flatPrice) {
		this.itemId = itemId;
		this.description = description;
		this.rateHourBilled = rateHourBilled;
		this.ratePrice = ratePrice;
		this.flatPrice = flatPrice;
		this.setState(DtoState.Modified);
	}

	/**
	 *
	 * @param description
	 * @param rateHourBilled
	 * @param ratePrice
	 * @param flatPrice
	 */
	public ItemDto(String description, Integer rateHourBilled, Float ratePrice, Float flatPrice) {
		this.description = description;
		this.rateHourBilled = rateHourBilled;
		this.ratePrice = ratePrice;
		this.flatPrice = flatPrice;
		this.setState(DtoState.New);
	}
}
