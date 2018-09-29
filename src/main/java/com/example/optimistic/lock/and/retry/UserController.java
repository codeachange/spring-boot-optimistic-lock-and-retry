package com.example.optimistic.lock.and.retry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(path = "updateWithoutRetry", method = RequestMethod.GET)
    public void updateWithoutRetry() {
        userService.updateWithoutRetry();
    }

    @RequestMapping(path = "updateWithRetry", method = RequestMethod.GET)
    public void updateWithRetry() {
        userService.updateWithRetry();
    }

}
