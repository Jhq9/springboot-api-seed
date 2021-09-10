package com.vanwei.tech.runner;

import com.vanwei.tech.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  Init Admin Account
 *
 *  @author     jin_huaquan
 *  @date      2021/9/10 13:43
 *  @version   1.0
 */
@Order(2)
@Component
@AllArgsConstructor
@Slf4j
public class InitAdminRunner implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Init Admin Account.");
        userService.initAdmin();
    }
}
