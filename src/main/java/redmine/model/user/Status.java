package redmine.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE(1),
    NON_ACTIVE(2),
    LOCKED(3);

    public final Integer status;

}
