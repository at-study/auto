package old_testng_tests.sql_tests;

import org.testng.annotations.Test;

import redmine.utils.StringGenerators;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

public class HashGeneration {

    @Test
    public void saltAndHashPasswordGeneration() {
        String salt = StringGenerators.randomString(32, "0123456789abcdef");
        String password = StringGenerators.randomEnglishString(10);


        // Алгоритм для хэширования паролей - Hash_row = SHA1( salt + SHA1( password ) )
        String hashedPassword = sha1Hex(salt + sha1Hex(password));

        System.out.println(password);
        System.out.println(salt);
        System.out.println(hashedPassword);


    }
}
