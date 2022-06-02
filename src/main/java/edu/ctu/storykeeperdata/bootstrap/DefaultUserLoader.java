package edu.ctu.storykeeperdata.bootstrap;

import edu.ctu.storykeeperdata.model.RegistrationRequest;
import edu.ctu.storykeeperdata.service.RegistrationService;
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
    private final RegistrationService regService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running UserLoader");
        if (userService.getCollectionCount() == 0) {
            log.info("Saving default user in the collection");
                persist(new RegistrationRequest("John", "Smith", "password", "jsmith@email.com"));
        } else {
            log.info("Default user already present in the users collection");
        }
    }

    @Override
    public int getOrder() {
        return 4;
    }

    private void persist(RegistrationRequest request) {
        regService.register(request);
    }

}
