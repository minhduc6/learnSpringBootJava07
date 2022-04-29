package vn.techmaster.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.login.hash.Hashing;

import java.util.List;

import  static  org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestHash {
    @Autowired
    private Hashing hashing;

    @Test
    public void hashPassword(){
        var passwords = List.of("abc","qwert","ox-123","_21312ds?a");
        for (String password: passwords) {
            String hashed_pass = hashing.hashPassword(password);
            assertThat(hashed_pass).isNotNull();
        }
    }

    @Test
    public void validatePassword(){
        var passwords = List.of("abc","qwert","ox-123","_21312ds?a");
        for (String password: passwords) {
            String hashed_pass = hashing.hashPassword(password);
            System.out.println(hashed_pass);
            assertThat(hashing.validatePassword(password,hashed_pass)).isTrue();
        }
        var result = hashing.validatePassword("abc","1000:b8ad6a893e1c21363c8cdd2382e0d91d:a52bb15c7535da7ed71ab86773cc1d908f911dd3c40162c7c30e35c5f8c7776daed5981f5251ce629a5c9b035a1ec4775ac9ef285607522cb78b7b8a7e422971");
        assertThat(result).isFalse();
    }


}
