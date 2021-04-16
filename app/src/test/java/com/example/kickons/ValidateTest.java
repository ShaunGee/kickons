package com.example.kickons;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateTest extends Validate{


    @Test
    public void isString() {
        String input = "Carton of XXXX gold";

        assertTrue(isString(input));
    }

    @Test
    public void testIsPrice() {
        Float inputPrice = 15.50f;
        assertTrue(isPrice(inputPrice));

    }
}