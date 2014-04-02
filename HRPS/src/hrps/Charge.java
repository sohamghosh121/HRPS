/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class Charge {
    public static final int ROOM_CHARGE = 1, ROOM_SERVICE = 2, TAX = 3;

    private String chargeName;
    private double amount;
    private int type;
    private boolean isWeekEnd;
    private double discount;

    public Charge (String cName, double a, int t, boolean isWkEnd, double disc)
    {
        chargeName = cName;
        amount = a;
        type = t;
        isWeekEnd = isWkEnd;
        discount = disc;
    }

    public void printCharge()
    {
        double discounted_amount = (100-discount)/100*amount;
        System.out.println(chargeName+"\t"+discounted_amount+"\t"+returnRoomType(type)+"\n");
        if (isWeekEnd)
            System.out.println("Weekend charges applied");
        if (discount != 0.0)
            System.out.println("Discount: "+(discount*100));

        System.out.println(" ");
    }

    public String returnRoomType (int type)
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
        }
        return chargeType;
    }
}
