package edu.ctu.storykeeperdata.bootstrap;

import edu.ctu.storykeeperdata.model.Role;
import edu.ctu.storykeeperdata.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoleLoader implements CommandLineRunner, Ordered {

    private final RoleRepository roleRepository;

    @Autowired
    public DefaultRoleLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole("ADMIN");
                roleRepository.save(newAdminRole);
            }

            Role userRole = roleRepository.findByRole("USER");
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER");
                roleRepository.save(newUserRole);
            }
    }


    @Override
    public int getOrder() {
        return 4;
    }
}