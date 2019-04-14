package de.microtema.user.repository.role;

import de.microtema.user.UserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleRepositoryTest {

    static final ParameterizedTypeReference<Map> ROLE_TYPE = new ParameterizedTypeReference<Map>() {
    };

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void getRoles() {

        ResponseEntity<Map> entity = restTemplate.getForEntity("/roles", Map.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Map body = entity.getBody();

        Object embedded = body.get("_embedded");

        assertNotNull(embedded);

        Object usersObject = ((Map) embedded).get("roles");

        assertNotNull(usersObject);

        List users = (List) usersObject;

        assertEquals(3, users.size());
    }

    @Test
    public void readRolesByName() {

        String url = "/roles/search/name-contains?query={query}&page={page}&size={size}";

        Map<String, Object> params = new HashMap<>();
        params.put("query", "");
        params.put("page", 0);
        params.put("size", 10);

        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, null, ROLE_TYPE, params);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());

        Map body = exchange.getBody();

        Object embedded = body.get("_embedded");

        assertNotNull(embedded);

        Object usersObject = ((Map) embedded).get("roles");

        assertNotNull(usersObject);

        List users = (List) usersObject;

        assertEquals(3, users.size());
    }

}
