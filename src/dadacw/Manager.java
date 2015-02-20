/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

import java.util.LinkedList;

/**
 *
 * @author alexgray
 */
public class Manager
{

    private LinkedList<Item> items;
    private LinkedList<Set> sets;
    private int currentStockNumber;

    public Manager()
    {
        this.items = new LinkedList<>();
        this.sets = new LinkedList<>();
    }
    
    public int getItemListSize(){
        return items.size();
    }
    
    public int getSetListSize(){
        return sets.size();
    }
    
    

    //sets getters, adders and sorts
    
    public void removeItem(Item rmItem){
        for (Item item : items)
        {
            if(item.getItemNumber() == rmItem.getItemNumber()){
                items.remove(rmItem);
                break;
            }
        }
        
        for (Set set : sets){ // remove item if in a set
            for (int item : set.getItems())
            {
                if(item == rmItem.getItemNumber()){
                    items.remove(rmItem);
                break;
            }
            }
        }
    }
    
    public void setItems(LinkedList<Item> newItems){
        this.items = newItems;
    }

    public Item getItemByIndex(int index)
    {
        return items.get(index);
    }

    public Item getItemByID(int index) // get item by id number
    {
        for (Item item : items)
        {
            if (item.getItemNumber() == index)
            {
                return item;
            }
        }
        return null;
    }

    public Item getItem(int index) // get item by index in list
    {
        return items.get(index);
    }

    public void addItem(String[] iS)
    {
        if (iS != null)
        {
            items.add(new Item(Integer.valueOf(iS[0]), iS[1],
                    Double.valueOf(iS[2])));
        }
        updatecurrentStockNumber(Integer.valueOf(iS[0]));
    }
    
    public void addItem(String desc, double price)
    {
        items.add(new Item(this.getNextStockNumber(), desc, price));
    }
    
    public Object[] getItemsAsObjects() // get item by index in list
    {
        Object[] obj = new Object[items.size()];
        for (int i = 0; i < items.size(); i++)
        {
            obj[i] = items.get(i);
        }
        return obj;
    }
    
    public LinkedList<Item> getItems()
    {
        return items;
    }
    
    //sets code and stuffs
    public LinkedList<Set> getSets()
    {
        return sets;
    }
    
    public void addSet(String[] iS)
    {
        if (iS != null)
        {
            Set set = new Set(Integer.valueOf(iS[0]), iS[1],
                    Double.valueOf(iS[2]));
            int noOfItems = Integer.valueOf(iS[3]) + 4;
            for (int i = 4; i < noOfItems; i++)
            {
                set.addItem(Integer.valueOf(iS[i]));
            }
            sets.add(set);
        }
    }
    
    public Object[] getSetsAsObjects()
    {
        Object[] obj = new Object[sets.size()];
        for (int i = 0; i < sets.size(); i++)
        {
            obj[i] = sets.get(i);
        }
        return obj;
    }
    
    private int getNextStockNumber(){
        currentStockNumber++;
        return currentStockNumber;
    }
    
    private void updatecurrentStockNumber(int stockNumber){
        if(stockNumber > currentStockNumber){
            currentStockNumber = stockNumber;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Item item : items)
        {
            sb.append(item.toString() + "\n");
        }
        return sb.toString();
    }
    
    

}
