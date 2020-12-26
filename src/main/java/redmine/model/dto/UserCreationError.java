package redmine.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserCreationError {

    List<String> errors;

}
