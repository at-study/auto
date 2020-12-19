package redmine.model.role;

import java.util.HashSet;

public class RolePermissions extends HashSet<RolePermission> {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("---\n");
        forEach(permission -> builder.append("- :").append(permission).append("\n"));
        return builder.toString();
    }

}
