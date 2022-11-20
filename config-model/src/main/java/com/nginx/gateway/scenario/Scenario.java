package com.nginx.gateway.scenario;

import lombok.Data;

import java.util.List;

/**
 * <p>Scenario</p>
 *
 * @author Jiuping Yi
 */
@Data
public class Scenario {
    private String name;
    private List<Case> cases;
    private Action pre_action;
    private Action post_action = Action.CLEAR_ETCD_CONFIG;
}
