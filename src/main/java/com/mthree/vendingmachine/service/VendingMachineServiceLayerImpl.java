/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chuck
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public void createSnack(Snack snack) throws 
        VendingMachineDuplicateIdException,
        VendingMachineDataValidationException, 
        VendingMachinePersistenceException {

        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.getSnack(snack.getTitle()) != null) {
            throw new VendingMachineDuplicateIdException(
                    "ERROR: Could not create snack.  Snack "
                    + snack.getTitle()
                    + " already exists");
        }

        // Now validate all the fields on the given Student object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateSnackData(snack);

        // We passed all our business rules checks so go ahead 
        // and persist the Student object
        dao.addSnack(snack.getTitle(), snack);

        // The Snack was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Snack " + snack.getTitle() + " CREATED.");

    }

    @Override
    public List<Snack> getAllSnacks() throws
            VendingMachinePersistenceException {
        return dao.getAllSnacks();
    }

    @Override
    public Snack getSnack(String snackTitle) throws
            VendingMachinePersistenceException {
        return dao.getSnack(snackTitle);
    }

    @Override
    public Snack removeSnack(String snackTitle) throws 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException,
            VendingMachinePersistenceException {
        
        Snack snack = dao.getSnack(snackTitle);
        
        if (snack.getCount() > 0 && 
                Double.parseDouble(dao.getMoneyInMachine()) > 
                Double.parseDouble(snack.getPrice())) {
            
            Snack removedSnack = dao.removeSnack(snackTitle);
            auditDao.writeAuditEntry("Snack " + snackTitle + " REMOVED.");
            return removedSnack;
            
        } else {
            if (snack.getCount() == 0) {
                try {
                    throw new VendingMachineNoItemInventoryException("ERROR: Item is not in inventory!");   
                } catch (VendingMachineNoItemInventoryException e) {
                    System.out.println(e.toString());
                    return null;
                }
            } else {
                try {
                    throw new VendingMachineInsufficientFundsException("ERROR: Insert more money!");   
                } catch (VendingMachineInsufficientFundsException e) {
                    System.out.println(e.toString());
                    return null;
                }
            }
        }
    }
    
    private void validateSnackData(Snack snack) throws 
            VendingMachineDataValidationException {

        if (snack.getTitle() == null 
                || snack.getTitle().trim().length() == 0
                || snack.getPrice() == null
                || snack.getCount() < 0) {
            throw new VendingMachineDataValidationException(
                    "ERROR: All fields are required.");
        }
    }

    @Override
    public boolean insertMoneyToMachine(String insertedMoney) throws 
            VendingMachineInvalidValueException,
            VendingMachinePersistenceException {
        BigDecimal money = new BigDecimal(insertedMoney);
        
        try {
            if (money.compareTo(BigDecimal.ZERO) == -1) {
                throw new VendingMachineInvalidValueException(
                        "ERROR: Invalid money value.");
            } else if (money.scale() != 2) {
                throw new VendingMachineInvalidValueException(
                        "ERROR: Invalid money value.");
            } else {
                money = money.setScale(2);
                dao.insertMoneyToMachine(money);
            }
        } catch (VendingMachineInvalidValueException e) {
            return false;
        }
        
        auditDao.writeAuditEntry("Money:  " + insertedMoney + " INSERTED.");
        return true;
    }
    
    @Override
    public String getMoneyInMachine() {
        return dao.getMoneyInMachine();
    }

    @Override
    public String getChange() throws VendingMachinePersistenceException {
        String change = dao.getChange();
        auditDao.writeAuditEntry("Change:  " + change + " CHANGE RECEIVED.");
        return change;
    }
}
