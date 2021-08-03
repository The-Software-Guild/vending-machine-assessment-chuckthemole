/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public class Change {
    private static final int LESS_THAN = -1;
    private static final int GREATER_THAN = 1;
    
    public enum Coin {
        QUARTER(new BigDecimal("25")), 
                DIME(new BigDecimal("10")), 
                NICKEL(new BigDecimal("5")), 
                PENNY(new BigDecimal("1"));
        
        public final BigDecimal value;
        
        private Coin(BigDecimal value) {
            this.value = value;
        }
    }
    
    public static Map makeChangeFromPennies(BigDecimal amountInPennies) {
        BigDecimal tempAmount = new BigDecimal(amountInPennies.toString());
        Map<Coin, Integer> coins = new HashMap<>();
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        int pennyCount = 0;
        BigDecimal difference;
        
        if (tempAmount.compareTo(BigDecimal.ONE) != LESS_THAN) {
            quarterCount = getCoinCount(tempAmount, Coin.QUARTER);
            difference = tempAmount.subtract(Coin.QUARTER.value.multiply(BigDecimal.valueOf(quarterCount)));
            tempAmount = new BigDecimal(difference.toString());
            
            if (tempAmount.compareTo(BigDecimal.ONE) != LESS_THAN) {
                dimeCount = getCoinCount(tempAmount, Coin.DIME);
                difference = tempAmount.subtract(Coin.DIME.value.multiply(BigDecimal.valueOf(dimeCount)));
                tempAmount = new BigDecimal(difference.toString());
                
                if (tempAmount.compareTo(BigDecimal.ONE) != LESS_THAN) {
                    nickelCount = getCoinCount(tempAmount, Coin.NICKEL);
                    difference = tempAmount.subtract(Coin.NICKEL.value.multiply(BigDecimal.valueOf(nickelCount)));
                    tempAmount = new BigDecimal(difference.toString());
                    
                    if (tempAmount.compareTo(BigDecimal.ONE) != LESS_THAN) {
                        pennyCount = getCoinCount(tempAmount, Coin.PENNY);
                    }
                }
            }
        }
        
        coins.put(Coin.QUARTER, quarterCount);
        coins.put(Coin.DIME, dimeCount);
        coins.put(Coin.NICKEL, nickelCount);
        coins.put(Coin.PENNY, pennyCount);
        
        return coins;
    }
    
    public static int getCoinCount(BigDecimal amount, Coin c) {
        int count = 0;
        
        while(amount.compareTo(BigDecimal.ONE) != LESS_THAN) {
            amount = amount.subtract(c.value);
            if (amount.compareTo(BigDecimal.ZERO) == GREATER_THAN || amount.equals(BigDecimal.ZERO)) {           
                count++;
            }
        }
        
        return count;
    }
}
