/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Soham G
 */
public class Bill implements Serializable{
    ArrayList charges = new ArrayList();
    public void add(Charge c)
    {
        charges.add(c);
    }

    public void printBill()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("\tBILL");
        System.out.println("-------------------------------------------------");
        Charge c;
        int numCharges = charges.size(), i;
        for (i=0; i<numCharges; i++)
        {
            c = (Charge)charges.get(i);
            c.printCharge();
        }
        System.out.println("-------------------------------------------------");
    }

}
