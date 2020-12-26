package rest_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import redmine.api.implementations.RestApiClient;
import redmine.api.implementations.RestRequest;
import redmine.api.interfaces.ApiClient;
import redmine.api.interfaces.HttpMethods;
import redmine.api.interfaces.Request;
import redmine.api.interfaces.Response;
import redmine.model.dto.RoleDto;
import redmine.model.role.Role;
import redmine.model.user.User;

import static org.testng.Assert.assertEquals;

public class RoleTest {

    private User user;
    private Role role;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User().generate();
        role = new Role().generate();
    }

    @Test
    public void getRoleByIdTest() {
        ApiClient apiClient = new RestApiClient(user);
        String uri = String.format("roles/%d.json", role.getId());
        Request request = new RestRequest(uri, HttpMethods.GET, null, null, null);
        Response response = apiClient.executeRequest(request);

        assertEquals(response.getStatusCode(), 200);

        RoleDto roleDto = response.getBody(RoleDto.class);

        assertEquals(roleDto.getRole().getId(), role.getId());
        assertEquals(roleDto.getRole().getName(), role.getName());
        assertEquals(roleDto.getRole().getAssignable(), role.getAssignable());
        assertEquals(roleDto.getRole().getIssues_visibility(), role.getIssuesVisibility().toString());
        assertEquals(roleDto.getRole().getUsers_visibility(), role.getUsersVisibility().toString());
        assertEquals(roleDto.getRole().getPermissions().size(), 0);
    }
}
