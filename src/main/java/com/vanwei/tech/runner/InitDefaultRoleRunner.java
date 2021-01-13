package com.vanwei.tech.runner;

import com.vanwei.tech.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 默认角色初始化 Runner
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2019/12/30 10:52
 */
@Order(111)
@Component
@AllArgsConstructor
@Slf4j
public class InitDefaultRoleRunner implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Init Default Role.");
        roleService.initDefaultRole();
    }
}
