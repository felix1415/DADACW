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
                    Item newItem = this.findSimilarItem(rmItem, set);
                    if (newItem != null)
                    {
                        set.removeItem(item); // remove old item
                        set.addItem(newItem.getItemNumber()); // add new item
                        break;
                    } else
                    {
                        double totalPrice = this.calculateNewSetPrice(set.getItems(), rmItem, set.getPrice());
                        set.setPrice(totalPrice);
                        set.removeItem(rmItem.getItemNumber());
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
        ArrayList<Integer> remove;
        ArrayList<Integer> add;
        for (int rmItemNumber : rmSet.getItems()) // for all the items in the set to be removed
        {
            for (Set set : sets) // for all other sets
            {
                remove = new ArrayList<>();
                add  = new ArrayList<>();
                for (int itemNum : set.getItems()) // for all the items in those sets
                {
                    if (itemNum == rmItemNumber)
                    {
                        remove.add(itemNum);
                        itemNum = findSimilarItem(
                                    items.getItemByID(rmItemNumber),
                                    set).getItemNumber();
                        add.add(itemNum);
                    }
                }
                for (int rm : remove)
                {
                    set.removeItem(rm);
                }
                for (int ad : add)
                {
                    set.addItem(ad);
                }
            }
            items.remove(rmItemNumber);
            bristolStore.remove(rmItemNumber);
            stockCounter.removeNumber(rmItemNumber);
            System.out.println(rmItemNumber + "| was removed  ");
        }
        sets.remove(rmSet);
        stockCounter.removeNumber(rmSet.getItemNumber());
    }

    private Item findSimilarItem(Item rmItem, Set set)
    {
        //System.out.println(rmItem.toString());
        for (Item item : items.getItems()) // for all items
        {
            //if the same description
            if (item.getItemDescription().equals(rmItem.getItemDescription()))
            {
                // and not the same item
                if (item.getItemNumber() != rmItem.getItemNumber())
                {
                    //and not already in the set
                    if (!containedInSet(item.getItemNumber(), set))
                    {
                        return item;
                    }
                }
            }
        }
        //split rmItem up into string array
        String[] rmItemString = rmItem.getItemDescription().split(" ");
        int contentMatches = 0;
        int lengthMatches = 0;
        int highestMatches = 0;
        Item matchItem = null;
        for (Item item : items.getItems()) // for all item
        {
            //if not same item
            if (!containedInSet(item.getItemNumber(), set))
            {
                String[] itemString = item.getItemDescription().split(" ");
                lengthMatches = Math.abs(itemString.length - rmItemString.length);
                for (String rmString : rmItemString) // for each word in the item
                {
                    for (String string : itemString) //for each word in the candidate
                    {
                        if (rmString.equals(string)) // see if string matches
                        {
                            contentMatches++;
                        }
                    }
                }
            }
            if (contentMatches > highestMatches && lengthMatches < 2)
            { // if better than previous
                System.out.println(item.getItemDescription() + "  " + contentMatches + "  " + lengthMatches);
                matchItem = item; //change 
                highestMatches = contentMatches;
            }
            contentMatches = 0;
            lengthMatches = 0;
        }
        return matchItem;
    }

    private boolean containedInSet(int itemNumber, Set set)
    {
        for (int item : set.getItems())
        {
            if (item == itemNumber)
            {
                return true;
            }
        }
        return false;
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
    
    public Item getItemByID(int id){
        return this.items.getItemByID(id);
    }

}
