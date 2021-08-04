/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.controller;

import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Snack;
import com.mthree.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.mthree.vendingmachine.service.VendingMachineInvalidValueException;
import com.mthree.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.mthree.vendingmachine.service.VendingMachineServiceLayer;
import com.mthree.vendingmachine.ui.VendingMachineView;
import java.util.List;

/**
 *
 * @author Chuck
 */
public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;


    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws 
            VendingMachinePersistenceException, 
            VendingMachineInvalidValueException, 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException {
        
        boolean keepGoing = true;
        
        while (keepGoing) {
            List<Snack> snackList = service.getAllSnacks();
            int menuSelection = getMenuSelection(snackList);

            switch (menuSelection) {
                case 1 -> insertMoney();
                case 2 -> selectSnack();
                case 3 -> getChange();
                case 4 -> listSnacks();
                case 5 -> printMoneyInMachine();
                case 6 -> keepGoing = false;
                default -> unknownCommand();
            }
        }
        
        getChange();
        exitMessage();
    }

    private int getMenuSelection(List<Snack> snackList) {
        return view.printMenuAndGetSelection(snackList);
    }
    
    private void insertMoney() throws 
            VendingMachineInvalidValueException, 
            VendingMachinePersistenceException {
        String insertedMoney = view.displayDisplayInsertionAndGetMoneyInserted();
        if(!service.insertMoneyToMachine(insertedMoney)) {
            view.incorrectFormatBanner();
        }
    }
    
    private void selectSnack() throws 
            VendingMachinePersistenceException, 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException {
        view.displayDisplayGetSnackBanner();
        String snack = view.displaySnackListAndChooseSnack(service.getAllSnacks());
        service.removeSnack(snack);       
    }
    
    private void getChange() throws VendingMachinePersistenceException {
        view.displayDisplayGetChangeBanner();
        view.displayChange(service.getChange());
    }
    
    private void printMoneyInMachine() {
        view.displayDisplayMoneyInMachineBanner();
        String moneyInMachine = service.getMoneyInMachine();
        view.displayMoneyInMachine(moneyInMachine);
    }
    
    private void listSnacks() throws VendingMachinePersistenceException {
        view.displayDisplayAllBanner();
        List<Snack> snackList = service.getAllSnacks();
        view.displaySnackList(snackList);
    }
    
    private void viewSnack() throws VendingMachinePersistenceException {
        view.displayDisplaySnackBanner();
        String snackTitle = view.getSnackTitleChoice();
        Snack snack = service.getSnack(snackTitle);
        view.displaySnack(snack);
    }
    
    private void removeSnack() throws 
            VendingMachinePersistenceException, 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException {
        view.displayRemoveSnackBanner();
        String snackTitle = view.getSnackTitleChoice();
        Snack removedSnack = service.removeSnack(snackTitle);
        view.displayRemoveResult(removedSnack);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void incorrectFormatInputMessage() {
        view.incorrectFormatBanner();
    }
}
