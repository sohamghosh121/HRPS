/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Soham G
 */
public class Charge implements Serializable{
    public static final int ROOM_CHARGE = 1, ROOM_SERVICE = 2, TAX = 3, FOOD_CHARGE = 4, TRANSPORT_CHARGE = 5;

    private String chargeName;
    private double amount;
    private int type;
    private boolean isWeekEnd;
    private double discount;
    private Calendar timeStamp;

    public Charge (String cName, double a, int t, boolean isWkEnd, double disc)
    {
        chargeName = cName;
        amount = a;
        type = t;
        isWeekEnd = isWkEnd;
        discount = disc;
        timeStamp = Calendar.getInstance();
    }

    public void printCharge()
    {
        double discounted_amount = (100-discount)/100*amount;
        System.out.println(chargeName+"\t"+discounted_amount+"\t"+returnChargeType(type)+"\t"+timeStamp.getTime().toString());
        if (isWeekEnd)
            System.out.print("Weekend");
        else
            System.out.print("Non-weekend");
        if (discount != 0.0)
            System.out.print("Discount: "+(discount*100));

        System.out.println(" ");
    }

    public String returnChargeType (int type)
    {
        String chargeType = "";
        switch(type)
        {
            case ROOM_CHARGE:
                chargeType = "Room charges";
                break;
            case ROOM_SERVICE:
                chargeType = "Room service";
                break;
            case TAX:
                chargeType = "Tax";
                break;
            case FOOD_CHARGE:
                chargeType = "Food";
                break;
            case TRANSPORT_CHARGE:
                chargeType = "Transport";
                break;
        }
        return chargeType;
    }
}
