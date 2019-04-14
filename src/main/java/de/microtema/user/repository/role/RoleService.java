package de.microtema.user.repository.role;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        Role role = new Role();

        role.setName("ROLE_ADMIN");

        repository.save(role);

        role = new Role();

        role.setName("ROLE_USER");

        repository.save(role);

        role = new Role();

        role.setName("ROLE_MANAGER");

        repository.save(role);
    }
}
