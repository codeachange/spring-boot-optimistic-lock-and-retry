package com.example.optimistic.lock.and.retry;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("Duplicates")
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User getOrCreateUser() {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            return users.get(0);
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Tom Cat");
        user.setAge(11);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userRepository.save(user);
        return user;
    }

    public void updateWithoutRetry() {
        System.out.println("updateWithoutRetry called.");
        User user = this.getOrCreateUser();

        User sameUser = this.getOrCreateUser();
        sameUser.setName("Another Tom Cat");
        sameUser.setUpdateTime(new Date());
        userRepository.save(sameUser);

        user.setAge(12);
        userRepository.save(user);
    }


    @Retryable(OptimisticLockingFailureException.class)
    public void updateWithRetry() {
        System.out.println("updateWithRetry called.");
        User user = this.getOrCreateUser();

        User sameUser = this.getOrCreateUser();
        sameUser.setName("Another Tom Cat");
        sameUser.setUpdateTime(new Date());
        userRepository.save(sameUser);

        user.setAge(12);
        userRepository.save(user);
    }

}
