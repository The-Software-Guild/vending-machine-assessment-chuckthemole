/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.ui;

import com.mthree.vendingmachine.dto.Snack;
import java.util.List;

/**
 *
 * @author Chuck
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(List<Snack> snackList) {
        
        io.print("Snacks in Vending Machine:"); 
        for (Snack snack : snackList) {
            io.print("  " + snack.getTitle());
        }
        io.print(""); 
        
        io.print("Main Menu");
        io.print("1. Insert Money");
        io.print("2. Select Snack");
        io.print("3. List Snacks in Machine");
        io.print("4. Print Money in Machine");
        io.print("5. Quit");
        io.print(""); 

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All Snacks in Vending Machine ===");
    }
    
    public void displaySnackList(List<Snack> snackList) {
        for (Snack currentSnack : snackList) {
            String dvdInfo = String.format("* %s \n  Price: %s \n  Quantity: %s\n",
                  currentSnack.getTitle(),
                  currentSnack.getPrice(),
                  currentSnack.getCount());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplaySnackBanner () {
        io.print("=== Display Snack ===");
    }

    public String getSnackTitleChoice() {
        return io.readString("Please enter the Snack.");
    }
    
    public void displaySnack(Snack snack) {
        if (snack != null) {
            io.print("  Title: " + snack.getTitle());
            io.print("  Price: " + snack.getPrice());
            io.print("  Quantity: " + snack.getCount());
            io.print("");
        } else {
            io.print("No such Snack.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveSnackBanner () {
        io.print("=== Remove Snack ===");
    }

    public void displayRemoveResult(Snack snack) {
        if(snack != null){
            io.print("Snack successfully removed.");
        }else{
            io.print("No such Snack.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public String displayDisplayInsertionAndGetMoneyInserted() {
        io.print("=== Display Insert Money ===");
        return io.readString("Enter amount to insert into Vending Machine");
    }
    
    public void displayDisplayMoneyInMachineBanner () {
        io.print("=== Money in Vending Machine ===");
    }
    
    public void displayMoneyInMachine(String moneyInMachine) {
        io.print(moneyInMachine);
    }
}
