package com.nginx.controller;

import com.nginx.model.Item;
import com.nginx.model.Result;
import com.nginx.model.Sku;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ItemController</p>
 *
 * @author Jiuping Yi
 */
@Api(value = "商品中心", tags = "商品中心")
@Slf4j
@RestController
public class ItemController {

    @RequestMapping(path = "/api/warehouse/item", method = RequestMethod.GET)
    @ApiOperation("商品查询")
    public Result<Item> items() {
        Result<Item> result = new Result<>();
        result.setData(SampleData.getItems());
        return result;
    }

    @RequestMapping(path = "/api/warehouse/sku", method = RequestMethod.GET)
    @ApiOperation("SKU查询")
    public Result<Sku> skus() {
        Result<Sku> result = new Result<>();
        result.setData(SampleData.getSkus());
        return result;
    }

}
