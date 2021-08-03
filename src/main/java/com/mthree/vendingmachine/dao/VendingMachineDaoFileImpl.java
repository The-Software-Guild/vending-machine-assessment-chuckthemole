/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Snack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chuck
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map<String, Snack> snacks;
    private BigDecimal moneyInMachine;
    
    public VendingMachineDaoFileImpl() {
        moneyInMachine = BigDecimal.ZERO;
        snacks  = new HashMap<>();
    }
    
    @Override
    public void insertMoneyToMachine(BigDecimal insertedMoney) {
        moneyInMachine = moneyInMachine.add(insertedMoney);
    }
    
    @Override
    public String getMoneyInMachine() {
        return moneyInMachine.toString();
    }

    @Override
    public Snack addSnack(String snackTitle, Snack snack) {
        Snack prevSnack = snacks.put(snackTitle, snack);
        return prevSnack; 
    }

    @Override
    public List<Snack> getAllSnacks() {
        return new ArrayList<Snack>(snacks.values());
    }

    @Override
    public Snack getSnack(String snackTitle) {
        return snacks.get(snackTitle);
    }

    @Override
    public Snack removeSnack(String dvdTitle) {
        Snack removedDvd = snacks.remove(dvdTitle);
        return removedDvd;
    }

    @Override
    public boolean addSnacksFromFile(String file) {

        Scanner sc;
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(file)));
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] snackData = currentLine.split("::");
                for (int i = 0; i < snackData.length; i++) {
                    StringBuilder sb = new StringBuilder(snackData[i]);
                    sb.replace(sb.indexOf(">"), sb.indexOf(">") + 1, "");
                    sb.replace(sb.indexOf("<"), sb.indexOf("<") + 1, "");  
                    snackData[i] = sb.toString();
                }
                Snack snack = new Snack(snackData[0], snackData[1], Integer.parseInt(snackData[2]));
                addSnack(snackData[0], snack);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    @Override
    public boolean writeSnacksToFile(String file) { 

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            for (Snack snack : snacks.values()) {
                out.println("<" + snack.getTitle() + ">::" + 
                        "<" + snack.getPrice() + ">::" +
                        "<" + snack.getCount() + ">");
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public Snack editSnack(String dvdTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
