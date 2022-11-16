package com.nginx.gateway.model.common;

import lombok.Data;

/**
 * <p>匹配条件</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Condition {

    /**
     * 匹配条件类型
     */
    private InputTypeEnum type;

    /**
     * 匹配条件名称
     */
    private String name;

    /**
     * 匹配操作符号
     */
    private OperationTypeEnum op;

    /**
     * 匹配值配置
     */
    private String value;
}
