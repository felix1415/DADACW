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

    public static double round(double value, int places)
    {
        places = Math.abs(places);
        double factor = (long) Math.pow(10, places);
        value = value * factor;
        double tmp = Math.round(value);
        return (double) tmp / factor;
    }

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
}
