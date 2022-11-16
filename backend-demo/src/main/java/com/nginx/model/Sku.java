package com.nginx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * <p>Sku</p>
 *
 * @author Jiuping Yi
 */
@Data
@Builder
@ApiModel(value = "SKU", description = "SKU对象")
public class Sku {
    @ApiModelProperty(value = "SKU ID")
    private String id;

    @ApiModelProperty(value = "商品")
    private Item item;

    @ApiModelProperty(value = "颜色规格")
    private String color;
}
