package de.microtema.user.repository.role;

import de.microtema.user.repository.user.User;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiRelation;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Table(name = "ROLES")
@JsonApiResource(type = "roles")
public class Role {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR", length = 100)
    private String name;

    @JsonApiRelation
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Role() {
    }
}
