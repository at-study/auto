package redmine.model.role;

import java.util.HashSet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import redmine.db.requests.RoleRequests;
import redmine.model.Generatable;
import redmine.utils.StringGenerators;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class Role implements Generatable<Role> {
    private Integer id;
    private Integer position = 1;
    private Integer builtin = 0;
    private String name = "Auto" + StringGenerators.randomEnglishString(8);
    private Boolean assignable = true;
    private IssuesVisibility issuesVisibility = IssuesVisibility.DEFAULT;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private RolePermissions permissions = new RolePermissions(new HashSet<>());
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;
    private Boolean allRolesManaged = true;
    private String settings = "--- !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "permissions_all_trackers: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: '1'\n" +
            "  add_issues: '1'\n" +
            "  edit_issues: '1'\n" +
            "  add_issue_notes: '1'\n" +
            "  delete_issues: '1'\n" +
            "permissions_tracker_ids: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: []\n" +
            "  add_issues: []\n" +
            "  edit_issues: []\n" +
            "  add_issue_notes: []\n" +
            "  delete_issues: []\n";

    @Override
    public Role read() {
        return id == null
                ? RoleRequests.getRoleByName(name)
                : RoleRequests.getRoleById(id);
    }

    @Override
    public Role update() {
        return RoleRequests.updateRole(this);
    }

    @Override
    public Role create() {
        return RoleRequests.addRole(this);
    }
}
