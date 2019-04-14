package de.microtema.user.repository.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    @RestResource(rel = "name-contains", path="name-contains", exported = true)
    Page<Role> findByNameContaining(@Param("query") String query, Pageable page);

    Role findByName(String name);
}
