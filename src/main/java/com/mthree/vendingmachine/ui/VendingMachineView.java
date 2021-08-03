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
        
        io.print("\nSnacks in Vending Machine:"); 
        for (Snack snack : snackList) {
            io.print("  " + snack.getTitle() + "\n    $" + snack.getPrice() + "  Quantity: " + snack.getCount());
        }
        io.print(""); 
        
        io.print("Main Menu");
        io.print("1. Insert Money");
        io.print("2. Select Snack");
        io.print("3. Get Change");
        io.print("4. List Snacks in Machine");
        io.print("5. Print Money in Machine");
        io.print("6. Quit");
        io.print(""); 

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public void displayDisplayAllBanner() {
        io.print("\n=== Display All Snacks in Vending Machine ===");
    }
    
    public void displaySnackList(List<Snack> snackList) {
        for (Snack currentSnack : snackList) {
            String snackInfo = String.format("* %s \n  Price: %s  Quantity: %s",
                  currentSnack.getTitle(),
                  currentSnack.getPrice(),
                  currentSnack.getCount());
            io.print(snackInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplaySnackBanner () {
        io.print("\n=== Display Snack ===");
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
        io.print("\n=== Remove Snack ===");
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
        io.print("\n=== Insert Money ===");
        return io.readString("Enter amount to insert into Vending Machine.\nEnter in dollar and cents (example 3.00).");
    }
    
    public void displayDisplayMoneyInMachineBanner () {
        io.print("\n=== Money in Vending Machine ===");
    }
    
    public void displayMoneyInMachine(String moneyInMachine) {
        io.print("$" + moneyInMachine + "\n\n");
    }
    
    public void incorrectFormatBanner() {
        io.print("\n*** Enter money in dollar and cents (Examples: 1.20 or 3.21 or 4.00). ***\n");
    }
    
    public void displayDisplayGetChangeBanner() {
        io.print("\n=== Getting Change form Machine ===");
    }
    
    public void displayChange(String change) {
        io.print("\nYour change is $" + change);
    }
    
    public void displayDisplayGetSnackBanner() {
        io.print("\n=== Select A Snack from Vending Machine ===");
    }
    
    public String displaySnackListAndChooseSnack(List<Snack> snackList) {
        for (Snack currentSnack : snackList) {
            String snackInfo = String.format("* %s \n  Price: %s  Quantity: %s",
                  currentSnack.getTitle(),
                  currentSnack.getPrice(),
                  currentSnack.getCount());
            io.print(snackInfo);
        }
        
        return io.readString("Choose a snack from the above choices.");
    }
}
