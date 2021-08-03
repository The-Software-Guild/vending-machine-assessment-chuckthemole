/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Snack;
import java.util.List;

/**
 *
 * @author Chuck
 */
public interface VendingMachineServiceLayer {
    void createSnack(Snack snack) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;
 
    List<Snack> getAllSnacks() throws
            VendingMachinePersistenceException;
 
    Snack getSnack(String snackTitle) throws
            VendingMachinePersistenceException;
 
    Snack removeSnack(String snackTitle) throws
            VendingMachinePersistenceException;
    
    void insertMoneyToMachine(String insertedMoney) throws
            VendingMachineInvalidValueException;
    
    String getMoneyInMachine();
}
