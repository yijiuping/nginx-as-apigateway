package com.nginx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * <p>Inventory</p>
 *
 * @author Jiuping Yi
 */
@Data
@Builder
@ApiModel(value = "库存", description = "库存对象")
public class Inventory {

    @ApiModelProperty(value = "库存ID")
    private String id;

    @ApiModelProperty(value = "SKU")
    private Sku sku;

    @ApiModelProperty(value = "库存值")
    private Integer stock;
}
