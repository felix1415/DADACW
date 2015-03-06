/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author alexgray
 */
public class Util
{

    public static LinkedList<Item> sortByItemNumber(LinkedList<Item> items)
    {
        Collections.sort(items, new Comparator<Item>()
        {

            @Override
            public int compare(Item i1, Item i2)
            {
                return Integer.compare(i1.getItemNumber(), i2.getItemNumber());
            }

        });
        return items;
    }

    public static LinkedList<Item> sortByPrice(LinkedList<Item> items)
    {
        Collections.sort(items, new Comparator<Item>()
        {

            @Override
            public int compare(Item i1, Item i2)
            {
                return Double.compare(i1.getPrice(), i2.getPrice());
            }

        });
        return items;
    }

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
