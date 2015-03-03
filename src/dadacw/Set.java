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
public class Set extends Item
{
    
    private final LinkedList<Integer> items;

    public Set(int itemNumber, String itemDescription, double price)
    {
        super(itemNumber, itemDescription, price);
        this.items = new LinkedList<>();
    }

    public LinkedList<Integer> getItems()
    {
        return items;
    }
    
    public void addItem(int item)
    {
        this.items.add(item);
    }
    
    public void removeItem(int itemNumber){
        for (int item : items)
        {
            if(item == itemNumber){
                items.remove(item);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        for (int item : items)
        {
            sb.append("\n   ").append(item);
        }
        sb.append("\n");
        return sb.toString();
    }
    
    

}
