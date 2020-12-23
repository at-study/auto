package sql_tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import redmine.db.requests.RoleRequests;
import redmine.managers.Manager;
import redmine.model.role.Role;
import redmine.model.role.RolePermission;
import redmine.model.role.RolePermissions;

public class DataBaseTest {

    @Test
    public void executePlainQueryTest() {

        String query = "SELECT * FROM users u " +
                "INNER JOIN tokens t ON u.id = t.user_id " +
                "INNER JOIN email_addresses e ON u.id = e.user_id";

        List<Map<String, Object>> result = Manager.dbConnection.executeQuery(query);



    }

    @Test
    public void basicSqlTest() {
        int rolesCountBefore = RoleRequests.getAllRoles().size();

        Role role = new Role();

        Assert.assertNull(role.getId());

        RoleRequests.addRole(role);

        Assert.assertNotNull(role.getId());

        int rolesCountAfter = RoleRequests.getAllRoles().size();

        Assert.assertEquals(rolesCountAfter, rolesCountBefore + 1);
    }

    @Test
    public void getRoleTest() {
        Role role = new Role();
        role.setName("Пользователь1");

        Role dataBaseRole = RoleRequests.getRole(role);
        Assert.assertEquals(dataBaseRole.getId().intValue(), 11);

        role.setId(15);
        Role dataBaseRole2 = RoleRequests.getRole(role);
        Assert.assertEquals(dataBaseRole2.getName(), "Новая автотестовая роль");
    }

    @Test
    public void updateRoleTest() {
        Role role = new Role();
        role.setName("Новая автотестовая роль");

        RoleRequests.updateRole(role);

    }

    @Test
    public void generateRoleTest() {
        Role role = new Role();
        role.setName("Генерируемая роль 3");

        role.generate();
        role.setPermissions(new RolePermissions(
                RolePermission.ADD_DOCUMENTS,
                RolePermission.CLOSE_PROJECT,
                RolePermission.ADD_SUBPROJECTS,
                RolePermission.VIEW_WIKI_PAGES,
                RolePermission.ADD_ISSUE_NOTES
        ));
        role.generate();
    }
}
