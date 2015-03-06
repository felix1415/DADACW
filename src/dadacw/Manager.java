/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author alexgray
 */
public class Manager
{

    private final Repository bristolStore;
    private final Repository items;
    private final LinkedList<Set> sets;
    private final StockCounter stockCounter;

    public Manager()
    {
        this.items = new Repository();
        this.bristolStore = new Repository();
        this.sets = new LinkedList<>();
        this.stockCounter = new StockCounter();
    }

    public int getSetListSize()
    {
        return sets.size();
    }

    public void removeItem(Item rmItem)
    {
        for (Item item : items.getItems())
        {
            if (item.getItemNumber() == rmItem.getItemNumber())
            {
                System.out.println(item.getItemNumber() + "|  " + rmItem.getItemNumber() + "|");
                items.remove(rmItem);
                break;
            }
        }
        
        for (Item item : bristolStore.getItems())
        {
            if (item.getItemNumber() == rmItem.getItemNumber())
            {
                bristolStore.remove(rmItem);
                break;
            }
        }

        for (Set set : sets)
        { // remove item if in a set
            for (int item : set.getItems())
            {
                if (item == rmItem.getItemNumber())
                {
                    Item newItem = this.findSimilarItem(rmItem);
                    if (newItem != null)
                    {
                        set.removeItem(item); // remove old item
                        set.addItem(newItem.getItemNumber()); // add new item
                        break;
                    } else
                    {
                        double totalPrice = this.calculateNewSetPrice(set.getItems(), rmItem, set.getPrice());
                        set.setPrice(totalPrice);
                        //setItems.remove(rmItem);
                        break;
                    }
                }
            }
        }
        stockCounter.removeNumber(rmItem.getItemNumber());
    }

    public void removeSet(Set rmSet)
    {
        //remove a set etc
        //Item newItem = findSimilarItem(new Item());
        for (int itemNumber : rmSet.getItems()) // for all the items in the set to be removed
        {
            for (Set set : sets) // for all other sets
            {
                for (int itemNum : set.getItems()) // for all the items in those sets
                {
                    //if(itemNum == )
                }
            }
            items.remove(itemNumber);
            bristolStore.remove(itemNumber);
            stockCounter.removeNumber(itemNumber);
            System.out.println(itemNumber + "| was removed  ");
        }
        for (Set set : sets)
        {
            if(set.getItemNumber() == rmSet.getItemNumber()){
                sets.remove(set);
                System.out.println(set.getItemNumber() + "|  " + rmSet.getItemNumber() + "|");
            }
        }
        stockCounter.removeNumber(rmSet.getItemNumber());
    }

    private Item findSimilarItem(Item rmItem)
    {
        for (Item item : items.getItems()) // for all items
        {
            if (item.getItemDescription().equals(rmItem.getItemDescription()))
            {
                System.out.println("Found new item");
                return item;
            }
        }
        return null;
    }

    private int calculateNewSetPrice(LinkedList<Integer> itemNums, Item rmItem, double price)
    {
        int sAP = 0; //Singular Accumalative Price
        LinkedList<Double> itemPrices = new LinkedList<>();
        for (int itemNum : itemNums)
        {
            Item item = items.getItemByID(itemNum);
            itemPrices.add(item.getPrice());
            sAP += item.getPrice();
        }
        double difference = sAP - price;
        //itemPrices = Util.doubleInsertionSort(itemPrices);
        return 0;
    }

    public LinkedList<Set> getSets()
    {
        return sets;
    }

    public void addSet(String[] iS)
    {
        if (iS != null)
        {
            Set set = new Set(Integer.valueOf(iS[0]), iS[1],
                    Double.valueOf(iS[2])); // create new set
            int noOfItems = Integer.valueOf(iS[3]) + 4;
            for (int i = 4; i < noOfItems; i++) // for number of item numbers
            {
                set.addItem(Integer.valueOf(iS[i])); // add item nubmers to set
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Item item : items.getItems())
        {
            sb.append(item.toString() + "\n");
        }
        return sb.toString();
    }

    public void addItem(String desc, double price)
    {
        this.items.addItem(desc, price, stockCounter.getNextStockNumber());
    }

    public void addItemBS(String desc, double price)
    {
        this.bristolStore.addItem(desc, price, stockCounter.getNextStockNumber());
    }

    public LinkedList<Item> getItems()
    {
        return this.items.getItems();
    }

    public LinkedList<Item> getItemsBS()
    {
        return this.bristolStore.getItems();
    }

    public void setItems(LinkedList<Item> items)
    {
        this.items.setItems(items);
    }

    public void setItemsBS(LinkedList<Item> items)
    {
        this.bristolStore.setItems(items);
    }

    public Object[] getItemsAsObjects()
    {
        return this.items.getItemsAsObjects();
    }

    public Object[] getItemsAsObjectsBS()
    {
        return this.bristolStore.getItemsAsObjects();
    }

    public int getItemListSize()
    {
        return this.items.getItemListSize();
    }

    public int getItemListSizeBS()
    {
        return this.bristolStore.getItemListSize();
    }

    public void addItemBS(String[] iS)
    {
        bristolStore.addItem(iS);
        items.addItem(iS);
        this.stockCounter.checkStockNumber(Integer.valueOf(iS[0]));
    }

    public void addItem(String[] iS)
    {
        items.addItem(iS);
        this.stockCounter.checkStockNumber(Integer.valueOf(iS[0]));
    }

}
