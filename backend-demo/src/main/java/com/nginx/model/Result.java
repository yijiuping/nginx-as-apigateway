package com.nginx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Result</p>
 *
 * @author Jiuping Yi
 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "返回码")
    protected String code = "200";

    @ApiModelProperty(value = "返回消息")
    protected String message = "成功";

    @ApiModelProperty(value = "返回数据")
    private List<T> data;

}
