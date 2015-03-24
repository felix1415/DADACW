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
    private double valueFraction;

    public Set(int itemNumber, String itemDescription, double price)
    {
        super(itemNumber, itemDescription, price);
        this.items = new LinkedList<>();
        this.valueFraction = 0;
    }
    
    public void updateValueFraction(LinkedList<Item> itemList){
        double price = 0;
        for (Item item : itemList)
        {
           price += item.getPrice();
        }
        if(price == 0 || this.getPrice() == 0){
            valueFraction = 1;
            return;
        }
        valueFraction = this.getPrice()/price;
    }
    
    public void updateValue(LinkedList<Item> itemList){
        
    }
    
    public double getValueFraction(){
        return valueFraction;
    }

    public LinkedList<Integer> getItems()
    {
        return items;
    }
    
    public void addItem(int item)
    {
        this.items.add(item);
    }
    
    public void removeItem(Item item){
        if(items.contains(item.getItemNumber())){
            this.setPrice(this.getPrice() - 
                    (item.getPrice()*this.valueFraction));
            items.remove(new Integer(item.getItemNumber()));
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" VF:" + getValueFraction());
        for (int item : items)
        {
            sb.append("\n   ").append(item);
        }
        sb.append("\n");
        return sb.toString();
    }
    
    

}
