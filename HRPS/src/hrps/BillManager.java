/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.List;

/**
 *
 * @author Soham G
 */
public class BillManager {
    private static List<Bill> bills =  (List)DBoperations.readSerializedObject("bills.dat");





    public void createNewBill(int roomNo)
    {
        Bill b = new Bill(roomNo);
        bills.add(b);
        saveBillsDB();
    }

    public void addCharges(Charge c, int roomNo)
    {
        int index = findBill(roomNo);
        Bill b;
        if (index != -1)
        {
            b = bills.get(index);
            b.add(c);
        }
    }

    public void showBill(int roomNo)
    {
        int index = findBill(roomNo);
        Bill b;
        if (index != -1)
        {
            b = bills.get(index);
            b.printBill();
        }
        saveBillsDB();
    }

    public void removeBill(int roomNo)
    {
        int index = findBill(roomNo);
        if (index != -1)
        {
            bills.remove(index);
        }
        saveBillsDB();
    }

    public int findBill(int roomNo)
    {
        int i;
        Bill b;
        for (i = 0; i < bills.size(); i++)
        {
            b = bills.get(i);
            if (b.getRoomNo()== roomNo)
                return i;
        }
        return -1;
    }


    private void saveBillsDB()//private because we don't want other objects to affect db
    {
        DBoperations.writeSerializedObject("bills.dat", bills);
    }

}
