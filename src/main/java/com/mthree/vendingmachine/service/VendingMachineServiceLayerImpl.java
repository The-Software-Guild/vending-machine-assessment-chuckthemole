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
                    "ERROR: Could not create student.  Student Id "
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

        // The student was successfully created, now write to the audit log
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
    public Snack removeSnack(String snackTitle) throws VendingMachinePersistenceException {
        Snack removedSnack = dao.removeSnack(snackTitle);
        // Write to audit log
        auditDao.writeAuditEntry("Snack " + snackTitle + " REMOVED.");
        return removedSnack;
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
    public void insertMoneyToMachine(String insertedMoney) throws VendingMachineInvalidValueException {
        BigDecimal money = new BigDecimal(insertedMoney);
        if (money.compareTo(BigDecimal.ZERO) == -1) {
            throw new VendingMachineInvalidValueException(
                    "ERROR: Invalid money value.");
        }
        
        dao.insertMoneyToMachine(money);
    }
    
    @Override
    public String getMoneyInMachine() {
        return dao.getMoneyInMachine();
    }
}
