package com.example.kickons;

import com.example.kickons.login.PasswordSecurity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void passwordHashTest() {

        assertEquals(true,testSec() );
    }

    private boolean testSec(){
        String w = "sunset";
        PasswordSecurity ps1 = new PasswordSecurity();
        PasswordSecurity ps2 = new PasswordSecurity();

        System.out.println(ps1.generateHash(w).length());
        System.out.println(ps2.generateHash(w));
        if (ps1.generateHash(w).equals(ps2.generateHash(w)) ){
            return true;
        }
        else{
            return false;
        }

    }
}