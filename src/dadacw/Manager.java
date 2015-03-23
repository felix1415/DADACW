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
    private final StockCounter stockCounter;
    private LinkedList<Set> sets;

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

    public boolean addItemToSet(int setID, int itemID)
    {
        Set set = this.getSetByID(setID);
        Item item = this.getItemByID(itemID);
        //if both are valid item and set then
        if (set != null && item != null)
        {
            //if not already in the set then
            if (!set.getItems().contains(itemID))
            {
                //add item to set
                set.addItem(itemID);
                //update set price
                updateSetPrices();
                return true;
            }
        }
        return false;
    }

    public void removeItem(Item rmItem)
    {
        LinkedList<Set> setsCopy = new LinkedList<>(sets);
        //for all sets
        for (Set set : sets)
        {
            Set tempSet = set;
            LinkedList<Integer> setItems = new LinkedList<>(set.getItems());
            //for all items in the set
            for (int item : setItems)
            {
                //if the item is equal to the remove item then
                if (item == rmItem.getItemNumber())
                {
                    //find a similar item method
                    Item newItem = this.findSimilarItem(rmItem, set);
                    //if new item is not null then
                    if (newItem != null)
                    {
                        //remove item from set
                        tempSet.removeItem(this.getItemByID(item));
                        //add new item
                        tempSet.addItem(newItem.getItemNumber());
                        break;
                    } else
                    {
                        //remove item from set
                        tempSet.removeItem(rmItem);
                        break;
                    }
                }
            }
            //if set now empty
            if (tempSet.getItems().size() == 0)
            {
                //remove set
                setsCopy.remove(set);
            }
        }
        //copy set back to original
        sets = setsCopy;
        //update set prices
        updateSetPrices();
        //remove from all repos and stock counter
        this.items.remove(rmItem);
        this.bristolStore.remove(rmItem);
        stockCounter.removeNumber(rmItem.getItemNumber());
    }

    public void updateSetPrices()
    {
        //for all sets
        for (Set set : sets)
        {
            double price = 0;
            //for all items in this set
            for (int itemNum : set.getItems())
            {
                //get the item by id
                Item item = this.getItemByID(itemNum);
                //price = price + (item price * set value fracetion)
                price += item.getPrice() * set.getValueFraction();
            }
            //set price
            set.setPrice(price);
        }
    }

    public void removeSet(Set rmSet)
    {
        //array list for changes
        ArrayList<Integer> remove;
        ArrayList<Integer> add;
        // for all the items in the set to be removed
        for (int rmItemNumber : rmSet.getItems())
        {
            LinkedList<Set> setsCopy = new LinkedList<>();
            // for all other sets
            for (Set set : sets)
            {
                //if set same as remove set then
                if (set.equals(rmSet))
                {
                    //next set
                    continue;
                }
                remove = new ArrayList<>(); // remove item from set
                add = new ArrayList<>(); // add item to set
                // for all the items in those sets
                for (int itemNum : set.getItems())
                {
                    //if the item to be removed then
                    if (itemNum == rmItemNumber)
                    {
                        //add item to remove array
                        remove.add(itemNum);
                        //find similar item
                        itemNum = findSimilarItem(items.getItemByID(rmItemNumber), set).getItemNumber();
                        //add item to add array
                        add.add(itemNum);
                    }
                }
                //remove items from set
                for (int rm : remove)
                {
                    set.removeItem(this.getItemByID(rm));
                }
                //add new items to set
                for (int ad : add)
                {
                    set.addItem(ad);
                }
                //copy set back to original set
                setsCopy.add(set);
            }
            updateSetPrices();
            sets = setsCopy;
            //remove item number from all repos
            items.remove(rmItemNumber);
            bristolStore.remove(rmItemNumber);
            stockCounter.removeNumber(rmItemNumber);
        }
        //remove set from sets
        sets.remove(rmSet);
        //remove id number from stock counter
        stockCounter.removeNumber(rmSet.getItemNumber());

    }

    private Item findSimilarItem(Item rmItem, Set set)
    {

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
        int setDescMatches = 0;
        Item matchItem = null;
        for (Item item : items.getItems()) // for all items
        {
            //if not same item
            if (!containedInSet(item.getItemNumber(), set))
            {
                //split item string
                String[] itemString = item.getItemDescription().split(" ");
                //get length difference of desc's
                lengthMatches = Math.abs(itemString.length - rmItemString.length);
                //split set string
                String[] setString = set.getItemDescription().split(" ");
                for (String rmString : rmItemString) // for each word in the item
                {
                    for (String string : itemString) //for each word in the candidate
                    {
                        if (rmString.equals(string)) // see if string matches
                        {
                            //increment contentMatches
                            contentMatches++;
                        }
                        //for all strings in set desc
                        for (String setStringSplit : setString)
                        {
                            //see if item string matches set string
                            if (string.equals(setStringSplit))
                            {
                                //increment setDescMatches
                                setDescMatches++;
                            }
                        }

                    }
                }
            }
            // if item matches set desc and remove item desc then
            if (contentMatches > highestMatches && lengthMatches < 2 && setDescMatches >= 6)
            { // if better than previous
                matchItem = item; //change 
                highestMatches = contentMatches;
            }
            //reset
            contentMatches = 0;
            lengthMatches = 0;
            setDescMatches = 0;
        }
        return matchItem; // best matched item for replacing an item
    }

    private boolean containedInSet(int itemNumber, Set set)
    {
        //for all items check if item is contained in a set
        for (int item : set.getItems())
        {
            if (item == itemNumber)
            {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Set> getSets()
    {
        return sets;
    }

    public void setSets(LinkedList<Set> sets)
    {
        this.sets = sets;
    }

    public int getNextStockNumber()
    {
        return this.stockCounter.getNextStockNumber();
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
            //calculate sets value fraction
            LinkedList<Item> setItems = new LinkedList<>();
            for (int item : set.getItems())
            {
                setItems.add(this.getItemByID(item));
            }
            set.updateValueFraction(setItems);
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
        int number = stockCounter.getNextStockNumber();
        this.bristolStore.addItem(desc, price, number);
        this.items.addItem(desc, price, number);
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

    public Item getItemByID(int id)
    {
        return this.items.getItemByID(id);
    }

    public Set getSetByID(int id)
    {
        for (Set set : sets)
        {
            if (set.getItemNumber() == id)
            {
                return set;
            }
        }
        return null;
    }

}
