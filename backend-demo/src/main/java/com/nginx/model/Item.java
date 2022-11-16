package com.nginx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * <p>Item</p>
 *
 * @author Jiuping Yi
 */
@Data
@Builder
@ApiModel(value = "商品", description = "商品对象")
public class Item {
    @ApiModelProperty(value = "商品ID")
    private String id;

    @ApiModelProperty(value = "商品名称")
    private String name;
}
