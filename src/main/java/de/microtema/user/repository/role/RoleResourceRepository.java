package de.microtema.user.repository.role;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * JSon Api ResourceRepository
 */
@Transactional
@Repository
public class RoleResourceRepository implements ResourceRepositoryV2<Role, Long> {

    private final RoleRepository repository;

    public RoleResourceRepository(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<Role> getResourceClass() {
        return Role.class;
    }

    @Override
    public Role findOne(Long sourceId, QuerySpec querySpec) {

        Optional<Role> optional = repository.findById(sourceId);

        return optional.orElse(null);
    }

    @Override
    public ResourceList<Role> findAll(QuerySpec querySpec) {

        Iterable<Role> users = repository.findAll();

        return querySpec.apply(users);
    }

    @Override
    public ResourceList<Role> findAll(Iterable<Long> iterable, QuerySpec querySpec) {

        Iterable<Role> users = repository.findAllById(iterable);

        return querySpec.apply(users);
    }

    @Override
    public <S extends Role> S save(S s) {

        return repository.save(s);
    }

    @Override
    public <S extends Role> S create(S s) {

        return save(s);
    }

    @Override
    public void delete(Long sourceId) {

        repository.deleteById(sourceId);
    }
}
