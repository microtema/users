package de.microtema.user.repository.user;

import de.microtema.user.repository.role.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init() {
        User user = new User();

        user.setName("Mario");
        user.setEmail("mario@microtema.de");

        repository.save(user);

        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_ADMIN")));
        repository.save(user);

        user = new User();

        user.setName("Aeneas");
        user.setEmail("aeneas@microtema.de");

        repository.save(user);
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        repository.save(user);

        user = new User();

        user.setName("Emmy");
        user.setEmail("emmy@web.de");

        repository.save(user);
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_MANAGER")));
        repository.save(user);
    }
}
