package com.nginx.gateway.model.common;

/**
 * <p>操作符类型</p>
 *
 * @author Jiuping Yi
 */
public enum OperationTypeEnum {
    EQUAL("="),
    PREFIX(".*"),
    EXPR("~"),
    SUFFIX("*.");

    private final String desc;

    OperationTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
