/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dto.Snack;
import java.util.LinkedList;
import java.util.List;
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
public class VendingMachineServiceLayerImplTest {
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createSnack method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testCreateDuplicateSnack() throws Exception {
        System.out.println("createDuplicateSnack");
        Snack snack = new Snack("Coke", "1.00", 0);
        try {
            service.createSnack(snack);
            fail("Expected Duplicate. Exception was not thrown.");
        } catch (VendingMachineDuplicateIdException e) {
            return;
        }
    }

    /**
     * Test of getAllSnacks method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllSnacks() throws Exception {
        System.out.println("getAllSnacks");
        
        List<Snack> expResult  = new LinkedList<>();
        Snack snack1 = new Snack("Twix", ".75", 3);
        Snack snack2 = new Snack("Snickers", "1.75", 1);
        Snack snack3 = new Snack("Coke", "1.00", 0);
        expResult.add(snack1);
        expResult.add(snack2);
        expResult.add(snack3);

        List<Snack> result = service.getAllSnacks();
        
        if (result.size() != expResult.size()) {
            fail("List are different sizes.");
        }
        
        for (Snack snack : result) {
            if (!expResult.contains(snack)) {
                fail("List does not contain all snacks.");
            }
        }
        return;
    }

    /**
     * Test of getSnack method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetSnack() throws Exception {
        System.out.println("getSnack");
        String snackTitle = "Coke";
        Snack expResult = new Snack("Coke", "1.00", 0);
        Snack result = service.getSnack(snackTitle);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSnack method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testRemoveSnack() throws Exception {
        System.out.println("removeSnack");
        String snackTitle = "Twix";
        Snack expResult = new Snack ("Twix", ".75", 2);
        Snack result = service.removeSnack(snackTitle);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertMoneyToMachine method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testInsertMoneyToMachine() throws Exception {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        
        System.out.println("insertMoneyToMachine");
        String insertedMoney = "1.00";
        
        boolean result = service.insertMoneyToMachine(insertedMoney);
        
        String expMoneyInMachine = "4.42";
        assertEquals(service.getMoneyInMachine(), expMoneyInMachine);
        
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of getMoneyInMachine method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetMoneyInMachine() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        
        System.out.println("getMoneyInMachine");
        String expResult = "3.42";
        String result = service.getMoneyInMachine();
        assertEquals(expResult, result);
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetChange() throws Exception {
        System.out.println("getChange");
        String expResult = "3.42\nQUARTER: 13\nNICKEL: 1\nDIME: 1\nPENNY: 2\n";
        String result = service.getChange();
        assertEquals(expResult, result);
    }
    
}
