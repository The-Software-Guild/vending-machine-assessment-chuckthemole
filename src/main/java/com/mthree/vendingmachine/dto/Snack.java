/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dto;

/**
 *
 * @author Chuck
 */
public class Snack {
    private String title;
    private String price;
    private int count;
            
    public Snack(String title) {
        this.title = title;
    }
    
    public Snack(String title, String price, int count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
