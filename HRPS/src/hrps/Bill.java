/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;

/**
 *
 * @author Soham G
 */
public class Bill {
    ArrayList charges = new ArrayList();

    public void addCharges(String chargeName, double amount, int type, boolean isWeekEnd, double discount)
    {
        Charge c = new Charge(chargeName, amount, type, isWeekEnd, discount);
        charges.add(c);

    }

    public void addCharges(String chargeName, double amount, int type, boolean isWeekEnd)
    {
        Charge c = new Charge(chargeName, amount, type, isWeekEnd, 0.0);
        charges.add(c);
    }

    public void addCharges(String chargeName, double amount, int type)
    {
        Charge c = new Charge(chargeName, amount, type, false, 0.0);
        charges.add(c);
    }

    public void printBill()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("BILL");
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
