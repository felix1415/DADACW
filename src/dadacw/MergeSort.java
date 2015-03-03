/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

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
            int middleIndex = (leftIndex + rightIndex) / 2;
            mergeSort(items, leftIndex, middleIndex, criteria);
            mergeSort(items, middleIndex + 1, rightIndex, criteria);
            merge(items, leftIndex, middleIndex, rightIndex, criteria);
        }
        return items;
    }
}
