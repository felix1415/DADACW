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
public class Repository
{
    private LinkedList<Item> items;
    

    public Repository()
    {
        this.items = new LinkedList<>();
    }
    
    public int getItemListSize()
    {
        return items.size();
    }
    
    public void setItems(LinkedList<Item> newItems)
    {
        this.items = newItems;
    }

    public Item getItemByID(int id) // get item by id number
    {
        for (Item item : items)
        {
            if (item.getItemNumber() == id)
            {
                return item;
            }
        }
        return null;
    }
    
    public LinkedList<Item> getItems()
    {
        return items;
    }

    public void addItem(String[] iS)
    {
        if (iS != null)
        {
            items.add(new Item(Integer.valueOf(iS[0]), 
                               String.valueOf(iS[1]),
                               Double.valueOf(iS[2])));
        }
    }
    
    public void addItem(String desc, double price, int stock)
    {
        items.add(new Item(stock, desc, price));
    }
    
    public void remove(Item item){
        this.remove(item.getItemNumber());
    }
    
    public void remove(int itemNumber){
        for (Item item : items)
        {
            if(item.getItemNumber() == itemNumber){
                items.remove(item);
                break;
            }
        }
    }

    public Object[] getItemsAsObjects() // used for GUIs
    {
        Object[] obj = new Object[items.size()];
        for (int i = 0; i < items.size(); i++)
        {
            obj[i] = items.get(i);
        }
        return obj;
    }
    
}
