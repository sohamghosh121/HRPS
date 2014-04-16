/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soham G
 */
public class Bill implements Serializable{
    public static final double TAX_RATE = 0.07;
    private int roomNo;
    List<Charge> charges = new ArrayList();

    public Bill(int rn)
    {
        roomNo = rn;
    }
    public void add(Charge c)
    {
        charges.add(c);
    }

    public void printBill()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("\tBILL");
        System.out.println("-------------------------------------------------");
        double tot = 0.0;
        Charge c;
        int numCharges = charges.size(), i;
        for (i=0; i<numCharges; i++)
        {
            c = charges.get(i);
            c.printCharge();
            tot += c.getAmount();
        }
        System.out.println("Total amount: \t"+tot);
        System.out.println("-------------------------------------------------");
    }

    public int getRoomNo()
    {
        return roomNo;
    }

}
