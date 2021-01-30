package redmine.cucumber;

import java.util.Map;

import org.testng.Assert;

public class ParametersValidator {

    public static void validateRoleParameters(Map<String, String> parameters) {
        parameters.forEach((key, value) -> Assert.assertTrue(
                AllowedParameters.ROLE_PARAMETERS.contains(key),
                "Список допустимых параметров при работе с ролями не содержит параметр " + key
        ));
    }

    public static void validateUserParameters(Map<String, String> parameters) {

    }

}
