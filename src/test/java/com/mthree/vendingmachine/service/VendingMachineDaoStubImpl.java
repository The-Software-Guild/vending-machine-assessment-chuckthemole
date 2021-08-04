/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dto.Change;
import com.mthree.vendingmachine.dto.Change.Coin;
import com.mthree.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Map<String, Snack> snacks;
    private BigDecimal moneyInMachine;
    
    public VendingMachineDaoStubImpl() {
        moneyInMachine = new BigDecimal("3.42");
        snacks  = new HashMap<>();
        Snack snack1 = new Snack("Twix", ".75", 3);
        Snack snack2 = new Snack("Snickers", "1.75", 1);
        Snack snack3 = new Snack("Coke", "1.00", 0);
        snacks.put("Twix", snack1);
        snacks.put("Snickers", snack2);
        snacks.put("Coke", snack3);
    }
    
    @Override
    public void insertMoneyToMachine(BigDecimal money) {
        moneyInMachine = moneyInMachine.add(money);
    }

    @Override
    public String getMoneyInMachine() {
        return moneyInMachine.toString();
    }

    @Override
    public boolean loadSnacksFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean writeSnacksToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Snack addSnack(String snackTitle, Snack snack) {
        return new Snack("", "", 0);
    }

    @Override
    public List<Snack> getAllSnacks() {
        return new ArrayList<>(snacks.values()); 
    }

    @Override
    public Snack getSnack(String snackTitle) {
        return snacks.get(snackTitle);
    }

    @Override
    public Snack removeSnack(String snackTitle) {
        Snack snack = snacks.get(snackTitle);
        snack.setCount(snack.getCount() - 1);
        moneyInMachine = moneyInMachine.subtract(new BigDecimal(snack.getPrice()));
        return snack;
    }

    @Override
    public String getChange() {
        StringBuilder sb = new StringBuilder();
        sb.append(moneyInMachine.toString()).append("\n").append(getChangeInCoins());
        moneyInMachine = BigDecimal.ZERO;
        return sb.toString();
    }

    @Override
    public String getChangeInCoins() {
        BigDecimal amountInPennies = moneyInMachine.movePointRight(2);
        Map<Coin, Integer> coins = Change.makeChangeFromPennies(amountInPennies);
        
        StringBuilder sb = new StringBuilder();
        for (Coin coin : coins.keySet()) {
            sb.append(coin).append(": ").append(coins.get(coin)).append("\n");
        }
        
        return sb.toString();
    }
    
}
