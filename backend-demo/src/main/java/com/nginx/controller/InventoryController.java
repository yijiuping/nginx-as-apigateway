package com.nginx.controller;

import com.nginx.model.Inventory;
import com.nginx.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>InventoryController</p>
 *
 * @author Jiuping Yi
 */
@Api(value = "库存中心", tags = "库存中心")
@Slf4j
@RestController
public class InventoryController {

    @RequestMapping(path = "/api/warehouse/inventory", method = RequestMethod.GET)
    @ApiOperation("库存查询")
    public Result<Inventory> inventories() {
        log.info("get inventories");
        Result<Inventory> result = new Result<>();
        result.setData(SampleData.getInventories());
        return result;
    }

}
