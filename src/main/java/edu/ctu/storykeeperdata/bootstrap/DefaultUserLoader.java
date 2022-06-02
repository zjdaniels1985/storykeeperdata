package edu.ctu.storykeeperdata.bootstrap;

import edu.ctu.storykeeperdata.model.User;
import edu.ctu.storykeeperdata.model.UserRole;
import edu.ctu.storykeeperdata.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


@Slf4j
@AllArgsConstructor
@Component
public class DefaultUserLoader implements CommandLineRunner, Ordered {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running UserLoader");
        if (userService.getCollectionCount() == 0) {
            log.info("Saving default user in the collection");
                persist();
        } else {
            log.info("Default orders are already present in the orders collection");
        }
    }

    @Override
    public int getOrder() {
        return 4;
    }

    private void persist() {
        final User defaultUser = new User("Zach Daniels","zdaniels","zdaniels@email.com","password",UserRole.ADMIN,false,true);
        userService.save(defaultUser);
    }

}
