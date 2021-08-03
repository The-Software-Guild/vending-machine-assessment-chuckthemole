/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Chuck
 */
public interface VendingMachineDao {
    /**
     * Adds money to current money in machine.
     * 
     * @param money to be added to machine
     */
    void insertMoneyToMachine(BigDecimal money);
    
    String getMoneyInMachine();
    
    /**
     * Adds the given Snacks to the collection from a file. 
     *
     * @param file file where snacks are located
     * @return true if the file exists and false if the file does not exist
     */
    boolean addSnacksFromFile(String file);
    
     /**
     * Writes the Snacks to the collection file. 
     *
     * @param file file where to write the snacks
     * @return true if the file exists and false if the file does not exist
     */
    boolean writeSnacksToFile(String file);
    
    /**
     * Adds the given Snacks to the collection and associates it with the given
     * snack title. If there is already a snack associated with the given
     * title it will return that snack object, otherwise it will
     * return null.
     *
     * @param snackTitle id with which snack is to be associated
     * @param snack snack to be added to the collection
     * @return the Snack object previously associated with the given  
     * dvd title if it exists, null otherwise
     */
    Snack addSnack(String snackTitle, Snack snack);

    /**
     * Returns a List of all snacks in the collection.
     *
     * @return List containing all snacks in the collection.
     */
    List<Snack> getAllSnacks();

    /**
     * Returns the snack object associated with the given snack title.
     * Returns null if no such snack exists
     *
     * @param snackTitle title of the snack to retrieve
     * @return the Snack object associated with the given snack title,  
     * null if no such snack exists
     */
    Snack getSnack(String snackTitle);

    /**
     * Removes the snack associated with the given title.
     * Returns the snack object that is being removed or null if
     * there is no snack associated with the given title
     *
     * @param snackTitle title of snack to be removed
     * @return Snack object that was removed or null if no snack
     * was associated with the given snack title
     */
    Snack removeSnack(String snackTitle);
    
    /**
     * Edits the snack associated with the given title.
     * Returns the snack object that is being edited or null if
     * there is no snack associated with the given title
     *
     * @param snackTitle title of snack to be edited
     * @return Snack object that was edited or null if no snack
     * was associated with the given snack title
     */
    Snack editSnack(String snackTitle);
}
