package com.nginx.controller;

import com.nginx.model.Price;
import com.nginx.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>PricingController</p>
 *
 * @author Jiuping Yi
 */
@Api(value = "价格中心", tags = "价格中心")
@Slf4j
@RestController
public class PricingController {

    @RequestMapping(path = "/api/warehouse/pricing", method = RequestMethod.GET)
    @ApiOperation("价格查询")
    public Result<Price> prices() {
        Result<Price> result = new Result<>();
        result.setData(SampleData.getPrices());
        return result;
    }

}
