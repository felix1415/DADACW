package sort;

import dadacw.Item;
import dadacw.Set;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alexgray
 */
public class InsertionSort
{

    public static LinkedList<Integer> integerInsertionSort(LinkedList<Integer> num)
    {
        int j;                     // the number of items sorted so far
        int key;                // the item to be inserted
        int i;

        for (j = 1; j < num.size(); j++)    // Start with 1 (not 0)
        {
            key = num.get(j);
            for (i = j - 1; (i >= 0) && (num.get(i) < key); i--)   // Smaller values are moving up
            {
                num.set(i + 1, num.get(i));
            }
            num.set(i + 1, key);
        }
        return num;
    }

    public static LinkedList<Item> itemInsertionSort(LinkedList<Item> item, int criteria)
    {
        int j;                     // the number of items sorted so far
        Item key;                // the item to be inserted
        int i = 0;

        for (j = 1; j < item.size(); j++)    // Start with 1 (not 0)
        {
            key = item.get(j);
            if (criteria == 1)
            {
                for (i = j - 1; (i >= 0) && (item.get(i).getItemNumber() > key.getItemNumber()); i--)   // Smaller values are moving up
                {
                    item.set(i + 1, item.get(i));
                }
            } else if (criteria == 2)
            {
                for (i = j - 1; (i >= 0) && (item.get(i).getPrice() > key.getPrice()); i--)   // Smaller values are moving up
                {
                    item.set(i + 1, item.get(i));
                }
            }
            item.set(i + 1, key);
        }
        return item;
    }

    public static LinkedList<Set> setInsertionSort(LinkedList<Set> sets, int criteria)
    {
        int j;                     // the number of items sorted so far
        Set key;                // the item to be inserted
        int i = 0;

        for (j = 1; j < sets.size(); j++)    // Start with 1 (not 0)
        {
            key = sets.get(j);
            if (criteria == 1)
            {
                for (i = j - 1; (i >= 0) && (sets.get(i).getItemNumber() > key.getItemNumber()); i--)   // Smaller values are moving up
                {
                    sets.set(i + 1, sets.get(i));
                }
            } else if (criteria == 2)
            {
                for (i = j - 1; (i >= 0) && (sets.get(i).getPrice() > key.getPrice()); i--)   // Smaller values are moving up
                {
                    sets.set(i + 1, sets.get(i));
                }
            }
            sets.set(i + 1, key);
        }
        return sets;
    }

    public static LinkedList<Double> doubleInsertionSort(LinkedList<Double> num)
    {
        int j;                     // the number of items sorted so far
        double key;                // the item to be inserted
        int i;

        for (j = 1; j < num.size(); j++)    // Start with 1 (not 0)
        {
            key = num.get(j);
            for (i = j - 1; (i >= 0) && (num.get(i) < key); i--)   // Smaller values are moving up
            {
                num.set(i + 1, num.get(i));
            }
            num.set(i + 1, key);
        }
        return num;
    }
}
