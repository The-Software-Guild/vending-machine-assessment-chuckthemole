/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

import com.mthree.vendingmachine.dto.Change.Coin;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Chuck
 */
public class ChangeTest {
    
    public ChangeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of makeChangeFromPennies method, of class Change.
     */
    @Test
    public void testMakeChangeFromPennies100() {
        System.out.println("makeChangeFromPennies100");
        BigDecimal amountInPennies = new BigDecimal("100");
        Map<Coin, Integer> expResult = new HashMap<>();
        expResult.put(Coin.QUARTER, 4);
        expResult.put(Coin.DIME, 0);
        expResult.put(Coin.NICKEL, 0);
        expResult.put(Coin.PENNY, 0);
        Map result = Change.makeChangeFromPennies(amountInPennies);
        // System.out.println(expResult);
        // System.out.println(result);
        // System.out.println(expResult.equals(result));
        assertTrue(expResult.equals(result));
    }
    
    @Test
    public void testMakeChangeFromPennies371() {
        System.out.println("makeChangeFromPennies371");
        BigDecimal amountInPennies = new BigDecimal("371");
        Map<Coin, Integer> expResult = new HashMap<>();
        expResult.put(Coin.QUARTER, 14);
        expResult.put(Coin.DIME, 2);
        expResult.put(Coin.NICKEL, 0);
        expResult.put(Coin.PENNY, 1);
        Map result = Change.makeChangeFromPennies(amountInPennies);
        // System.out.println(expResult);
        // System.out.println(result);
        // System.out.println(expResult.equals(result));
        assertTrue(expResult.equals(result));
    }
    
    @Test
    public void testMakeChangeFromPennies0() {
        System.out.println("makeChangeFromPennies0");
        BigDecimal amountInPennies = new BigDecimal("0");
        Map<Coin, Integer> expResult = new HashMap<>();
        expResult.put(Coin.QUARTER, 0);
        expResult.put(Coin.DIME, 0);
        expResult.put(Coin.NICKEL, 0);
        expResult.put(Coin.PENNY, 0);
        Map result = Change.makeChangeFromPennies(amountInPennies);
        // System.out.println(expResult);
        // System.out.println(result);
        // System.out.println(expResult.equals(result));
        assertTrue(expResult.equals(result));
    }
    
    @Test
    public void testMakeChangeFromPennies8() {
        System.out.println("makeChangeFromPennies8");
        BigDecimal amountInPennies = new BigDecimal("8");
        Map<Coin, Integer> expResult = new HashMap<>();
        expResult.put(Coin.QUARTER, 0);
        expResult.put(Coin.DIME, 0);
        expResult.put(Coin.NICKEL, 1);
        expResult.put(Coin.PENNY, 3);
        Map result = Change.makeChangeFromPennies(amountInPennies);
        // System.out.println(expResult);
        // System.out.println(result);
        // System.out.println(expResult.equals(result));
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getCoinCount method, of class Change.
     */
    @Test
    public void testGetCoinCount() {
        System.out.println("getCoinCount");
        BigDecimal amount = new BigDecimal("112");
        Change.Coin c = Coin.QUARTER;
        int expResult = 4;
        int result = Change.getCoinCount(amount, c);
        assertEquals(expResult, result);
    }
}
