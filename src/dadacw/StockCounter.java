/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadacw;

import java.util.LinkedList;

/**
 *
 * @author alexgray
 */
public class StockCounter
{

    private LinkedList<Integer> UID;

    public StockCounter()
    {
        this.UID = new LinkedList<>();
        this.UID.add(0);
    }

    public int getNextStockNumber()
    {
        int number = UID.getFirst() + 1; //UID first is always largest number
        UID.add(number);
        sort();
        return UID.getFirst();
    }
    
    public void removeNumber(int number){
        UID.removeFirstOccurrence(number);
    }

    public void checkStockNumber(int number)
    {
        if (number > UID.getFirst())
        {
            UID.add(number);
            sort();
            System.out.println(UID.getFirst());
            return;
        } else
        {
            for (int num : UID)
            {
                if (num == number)
                {
                    System.out.println("ERROR: STOCK NUMBER ALREADY EXSISTS");
                }
            }
        }

    }

    private void sort()
    {
        this.UID = Util.integerInsertionSort(UID); // use insertion sort for nearly sorted list
    }

}
