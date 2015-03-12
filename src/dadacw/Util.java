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
    
    public static ArrayList<String> insert(ArrayList<String> strings, String value, int location){
        int currentElement = 0;
        //if invalid location return null
        if(location > strings.size()){
            return null;
        }
        ArrayList<String> newStrings = new ArrayList<>();
        //for all strings in list
        for (String string : strings)
        {
            //if at desired insert location then insert element
            if(location == currentElement){
                newStrings.add(value);
            }
            //add exsisting string to list, increment counter
            newStrings.add(string);
            currentElement++;
        }
        return newStrings;
    }

    public static LinkedList<Item> sortByItemNumber(LinkedList<Item> items)
    {
        //sort items by defined comparator
        Collections.sort(items, new Comparator<Item>()
        {

            @Override
            public int compare(Item i1, Item i2)
            {
                //compare items by item number
                return Integer.compare(i1.getItemNumber(), i2.getItemNumber());
            }

        });
        return items;
    }

    public static LinkedList<Item> sortByPrice(LinkedList<Item> items)
    {
        //sort items by defined comparator
        Collections.sort(items, new Comparator<Item>()
        {

            @Override
            public int compare(Item i1, Item i2)
            {
                //compare items by price
                return Double.compare(i1.getPrice(), i2.getPrice());
            }

        });
        return items;
    }
}
