package de.microtema.user.repository.user;

import de.microtema.user.repository.role.Role;
import de.microtema.user.repository.role.RoleRepository;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.RelationshipRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public class User2RoleRelationshipRepository implements RelationshipRepositoryV2<User, Long, Role, Long> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User2RoleRelationshipRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Class<User> getSourceResourceClass() {

        return User.class;
    }

    @Override
    public Class<Role> getTargetResourceClass() {

        return Role.class;
    }

    @Override
    public void setRelation(User source, Long targetId, String fieldName) {

        // Not supported for many to many mapping
    }

    @Override
    public void setRelations(User user, Iterable<Long> targetIds, String fieldName) {

        Set<Role> roles = new HashSet<>();

        roleRepository.findAllById(targetIds).forEach(roles::add);

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public void addRelations(User user, Iterable<Long> targetIds, String fieldName) {

        Set<Role> roles = user.getRoles();

        roleRepository.findAllById(targetIds).forEach(roles::add);

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public void removeRelations(User user, Iterable<Long> targetIds, String fieldName) {

        Set<Role> roles = user.getRoles();

        roleRepository.findAllById(targetIds).forEach(roles::remove);

        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public Role findOneTarget(Long sourceId, String fieldName, QuerySpec querySpec) {

        return null; // Not supported for many to many mapping
    }

    @Override
    public ResourceList<Role> findManyTargets(Long sourceId, String fieldName, QuerySpec querySpec) {

        Optional<User> user = userRepository.findById(sourceId);

        return user.map(it -> querySpec.apply(it.getRoles())).orElse(null);
    }
}
