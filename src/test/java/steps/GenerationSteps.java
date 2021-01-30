package steps;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import redmine.cucumber.ParametersValidator;
import redmine.managers.Context;
import redmine.model.role.IssuesVisibility;
import redmine.model.role.Role;
import redmine.model.role.RolePermission;
import redmine.model.role.RolePermissions;
import redmine.model.role.TimeEntriesVisibility;
import redmine.model.role.UsersVisibility;
import redmine.model.user.User;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class GenerationSteps {

    @Пусть("Существует пользователь {string} с параметрами:")
    public void generateAndSaveUser(String stashId, Map<String, String> params) {
        User user = new User();
        if (params.containsKey("login")) {
            user.setLogin(params.get("login"));
        }
        if (params.containsKey("password")) {
            user.setPassword(params.get("password"));
        }
        if (params.containsKey("admin")) {
            user.setAdmin(Boolean.parseBoolean(params.get("admin")));
        }
        if (params.containsKey("status")) {
            user.setStatus(Integer.parseInt(params.get("status")));
        }
        user.generate();
        Context.put(stashId, user);
    }

    @И("В системе существует роль {string} с параметрами по умолчанию")
    public void generateDefaultRole(String roleStashId) {
        Role role = new Role().generate();
        Context.put(roleStashId, role);
    }

    @И("В системе существует роль {string} с параметрами:")
    public void generateRoleWithParameters(String roleStashId, Map<String, String> parameters) {
        ParametersValidator.validateRoleParameters(parameters);
        Role role = new Role();
        if (parameters.containsKey("Позиция")) {
            role.setPosition(parseInt(parameters.get("Позиция")));
        }
        if (parameters.containsKey("Встроенная")) {
            role.setBuiltin(parseInt(parameters.get("Встроенная")));
        }
        if (parameters.containsKey("Задача может быть назначена этой роли")) {
            role.setAssignable(parseBoolean(parameters.get("Задача может быть назначена этой роли")));
        }
        if (parameters.containsKey("Видимость задач")) {
            role.setIssuesVisibility(
                    IssuesVisibility.of(parameters.get("Видимость задач"))
            );
        }
        if (parameters.containsKey("Видимость пользователей")) {
            role.setUsersVisibility(
                    UsersVisibility.of(parameters.get("Видимость пользователей"))
            );
        }
        if (parameters.containsKey("Видимость трудозатрат")) {
            role.setTimeEntriesVisibility(
                    TimeEntriesVisibility.of(parameters.get("Видимость трудозатрат"))
            );
        }
        if (parameters.containsKey("Права")) {
            RolePermissions permissions = Context.get(parameters.get("Права"), RolePermissions.class);
            role.setPermissions(permissions);
        }
        role.generate();
        Context.put(roleStashId, role);
    }


    @И("Существует список прав роли {string} с правами:")
    public void putPermissionsToContext(String permissionsStashId, List<String> permissionDescriptions) {
        Set<RolePermission> permissions = permissionDescriptions.stream()
                .map(RolePermission::of)
                .collect(Collectors.toSet());
        RolePermissions rolePermissions = new RolePermissions(permissions);
        Context.put(permissionsStashId, rolePermissions);
    }
}
