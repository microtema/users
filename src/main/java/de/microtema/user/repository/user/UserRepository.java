package de.microtema.user.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource(rel = "name-contains", path="name-contains", exported = true)
    Page<User> findByNameContaining(@Param("query") String query, Pageable page);

    @RestResource(rel = "email-contains", path="email-contains", exported = true)
    Page<User> findByEmailContaining(@Param("query") String query, Pageable page);
}
