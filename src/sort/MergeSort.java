/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import dadacw.Item;
import dadacw.Set;
import java.util.LinkedList;

public class MergeSort
{

    public static LinkedList<Item> sort(LinkedList<Item> items, int criteria)
    {
        return mergeSort(items, 0, items.size() - 1, criteria);
    }

    private static LinkedList<Item> merge(LinkedList<Item> items, int leftIndex, int middleIndex, int rightIndex, int criteria)
    {
        LinkedList<Item> item1 = new LinkedList<>();
        LinkedList<Item> item2 = new LinkedList<>();

        for (int i = leftIndex; i <= middleIndex; i++)
        {
            item1.add(items.get(i));
        }
        for (int i = middleIndex + 1; i <= rightIndex; i++)
        {
            item2.add(items.get(i));
        }

        int i = leftIndex;
        while (!item1.isEmpty() && !item2.isEmpty())
        {
            if (criteria == 1)
            {
                if (item1.peek().getItemNumber() <= item2.peek().getItemNumber())
                {
                    items.set(i++, item1.poll());
                } else
                {
                    items.set(i++, item2.poll());
                }
            } else if (criteria == 2) {
                if (item1.peek().getPrice() <= item2.peek().getPrice())
                {
                    items.set(i++, item1.poll());
                } else
                {
                    items.set(i++, item2.poll());
                }
            }
            
        }

        while (!item1.isEmpty())
        {
            items.set(i++, item1.poll());
        }
        while (!item2.isEmpty())
        {
            items.set(i++, item2.poll());
        }
        return items;
    }

    private static LinkedList<Item> mergeSort(LinkedList<Item> items, int leftIndex, int rightIndex, int criteria)
    {
        if (leftIndex < rightIndex)
        {
            //split list
            int middleIndex = (leftIndex + rightIndex) / 2;
            mergeSort(items, leftIndex, middleIndex, criteria); // sort left list
            mergeSort(items, middleIndex + 1, rightIndex, criteria); // sort right list
            merge(items, leftIndex, middleIndex, rightIndex, criteria);
        }
        return items;
    }
    
    public static LinkedList<Set> sortSet(LinkedList<Set> sets, int criteria)
    {
        return mergeSortSet(sets, 0, sets.size() - 1, criteria);
    }

    private static LinkedList<Set> mergeSet(LinkedList<Set> sets, int leftIndex, int middleIndex, int rightIndex, int criteria)
    {
        LinkedList<Set> item1 = new LinkedList<>();
        LinkedList<Set> item2 = new LinkedList<>();

        for (int i = leftIndex; i <= middleIndex; i++)
        {
            item1.add(sets.get(i));
        }
        for (int i = middleIndex + 1; i <= rightIndex; i++)
        {
            item2.add(sets.get(i));
        }

        int i = leftIndex;
        while (!item1.isEmpty() && !item2.isEmpty())
        {
            if (criteria == 1)
            {
                if (item1.peek().getItemNumber() <= item2.peek().getItemNumber())
                {
                    sets.set(i++, item1.poll());
                } else
                {
                    sets.set(i++, item2.poll());
                }
            } else if (criteria == 2) {
                if (item1.peek().getPrice() <= item2.peek().getPrice())
                {
                    sets.set(i++, item1.poll());
                } else
                {
                    sets.set(i++, item2.poll());
                }
            }
            
        }

        while (!item1.isEmpty())
        {
            sets.set(i++, item1.poll());
        }
        while (!item2.isEmpty())
        {
            sets.set(i++, item2.poll());
        }
        return sets;
    }

    private static LinkedList<Set> mergeSortSet(LinkedList<Set> sets, int leftIndex, int rightIndex, int criteria)
    {
        if (leftIndex < rightIndex)
        {
            int middleIndex = (leftIndex + rightIndex) / 2;
            mergeSortSet(sets, leftIndex, middleIndex, criteria);
            mergeSortSet(sets, middleIndex + 1, rightIndex, criteria);
            mergeSet(sets, leftIndex, middleIndex, rightIndex, criteria);
        }
        return sets;
    }
}
