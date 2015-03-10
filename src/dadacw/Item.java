/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dadacw;

/**
 *
 * @author alexgray
 */
public class Item
{
    
    private final int itemNumber;
    private String itemDescription;
    private double price;

    public Item(int itemNumber, String itemDescription, double price)
    {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }


    public int getItemNumber()
    {
        return itemNumber;
    }
    
    public void setPrice(double newPrice){
        this.price = Util.round(newPrice, 2);
    }

    @Override
    public String toString()
    { // format for easy display
        String spaces = " ";
        int spaceNum = 45 - itemDescription.length(); 
        for (int i = 0; i < spaceNum; i++)
        {
            spaces += " ";
        }
        return itemNumber + "  " +
                itemDescription + spaces + " :-"+ price;
    }
    
    

}
