/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

    public static LinkedList<Item> radixSortItemNumber(LinkedList<Item> items)
    {
        LinkedList<Item>[] counter = new LinkedList[]
        {
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>()
        };
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < 5; i++, dev *= 10, mod *= 10)
        {
            for (int j = 0; j < items.size(); j++)
            {
                int bucket = (items.get(j).getItemNumber() % mod) / dev;
                counter[bucket].add(items.get(j));
            }
            int pos = 0;
            for (int j = 0; j < counter.length; j++)
            {
                Item value = null;
                while ((value = counter[j].poll()) != null)
                {
                    items.set(pos++, value);
                }
            }
        }
        return items;
    }

    public static <E extends Comparable<? super Item>> LinkedList<Item> heapsort(LinkedList<Item> items)
    {

        // Java's PriorityQueue class functions as a min heap
        PriorityQueue<Item> heap = new PriorityQueue<>(items.size());

        // Add each array element to the heap
        for (Item item : items)
        {
            heap.add(item);
        }

        // Elements come off the heap in ascending order
        for (int i = 0; i < items.size(); i++)
        {
            items.set(i, heap.remove());
        }
        return items;
    }
}
