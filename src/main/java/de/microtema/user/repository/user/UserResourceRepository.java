package de.microtema.user.repository.user;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public class UserResourceRepository implements ResourceRepositoryV2<User, Long> {

    private final UserRepository repository;

    public UserResourceRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<User> getResourceClass() {
        return User.class;
    }

    @Override
    public User findOne(Long sourceId, QuerySpec querySpec) {

        Optional<User> optional = repository.findById(sourceId);

        return optional.orElse(null);
    }

    @Override
    public ResourceList<User> findAll(QuerySpec querySpec) {

        Iterable<User> users = repository.findAll();

        return querySpec.apply(users);
    }

    @Override
    public ResourceList<User> findAll(Iterable<Long> iterable, QuerySpec querySpec) {

        Iterable<User> users = repository.findAllById(iterable);

        return querySpec.apply(users);
    }

    @Override
    public <S extends User> S save(S s) {

        return repository.save(s);
    }

    @Override
    public <S extends User> S create(S s) {

        return save(s);
    }

    @Override
    public void delete(Long sourceId) {

        repository.deleteById(sourceId);
    }
}
