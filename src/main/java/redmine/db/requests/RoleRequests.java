package redmine.db.requests;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import redmine.managers.Manager;
import redmine.model.role.IssuesVisibility;
import redmine.model.role.Role;
import redmine.model.role.RolePermissions;
import redmine.model.role.TimeEntriesVisibility;

public class RoleRequests {

    public static List<Role> getAllRoles() {
        String query = "SELECT * FROM roles";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query);
        return result.stream()
                .map(map -> {
                    Role role = new Role();
                    role.setId((Integer) map.get("id"));
                    role.setPosition((Integer) map.get("position"));
                    role.setBuiltin((Integer) map.get("builtin"));
                    role.setName((String) map.get("name"));
                    role.setAssignable((Boolean) map.get("assignable"));
                    role.setIssuesVisibility(
                            IssuesVisibility.valueOf(((String) map.get("issues_visibility")).toUpperCase())
                    );
                    role.setPermissions(RolePermissions.of((String) map.get("permissions")));
                    role.setTimeEntriesVisibility(
                            TimeEntriesVisibility.valueOf(((String) map.get("time_entries_visibility")).toUpperCase())
                    );
                    role.setAllRolesManaged((Boolean) map.get("all_roles_managed"));
                    role.setSettings((String) map.get("settings"));
                    return role;
                })
                .collect(Collectors.toList());
    }

    public static Role getRoleById(Integer id) {
        String query = "SELECT * FROM roles WHERE id = ?";
        // Из результата запроса получить 1ую запись, собрать ее в Role и вернуть.
        return null;
    }

    public static Role getRoleByName(String name) {
        String query = "SELECT * FROM roles WHERE name = ?";
        // Из результата запроса получить 1ую запись, собрать ее в Role и вернуть.
        return null;
    }

    public static Role addRole(Role role) {
        Objects.requireNonNull(role.getPosition(), "У пользователя не может быть position == null");
        String query = "INSERT INTO public.roles\n" +
                "(id, \"name\", \"position\", assignable, builtin, permissions, issues_visibility, users_visibility, time_entries_visibility, all_roles_managed, settings)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                role.getPermissions().toString(),
                role.getIssuesVisibility().toString(),
                role.getUsersVisibility().toString(),
                role.getTimeEntriesVisibility().toString(),
                role.getAllRolesManaged(),
                role.getSettings()
        );
        role.setId((Integer) result.get(0).get("id"));
        return role;
    }

    public static Role updateRole(Role role) {
        String query = "UPDATE public.roles\n" +
                "SET \"position\"=?, assignable=?, builtin=?, " +
                "permissions=?, issues_visibility=?, users_visibility=?, time_entries_visibility=?, all_roles_managed=?, settings=?\n" +
                "WHERE name=? RETURNING id;\n";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                role.getPermissions().toString(),
                role.getIssuesVisibility().toString(),
                role.getUsersVisibility().toString(),
                role.getTimeEntriesVisibility().toString(),
                role.getAllRolesManaged(),
                role.getSettings(),
                role.getName()
        );
        role.setId((Integer) result.get(0).get("id"));
        return role;
    }

}
