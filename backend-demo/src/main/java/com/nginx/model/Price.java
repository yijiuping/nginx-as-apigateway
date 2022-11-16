package com.nginx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * <p>Price</p>
 *
 * @author Jiuping Yi
 */
@Data
@Builder
@ApiModel(value = "价格", description = "价格对象")
public class Price {
    @ApiModelProperty(value = "价格ID")
    private String id;

    @ApiModelProperty(value = "SKU")
    private Sku sku;

    @ApiModelProperty(value = "价格值")
    private Double price;
}
