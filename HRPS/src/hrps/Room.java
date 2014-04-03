/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;

/**
 *
 * @author Soham G
 */
public class Room implements Serializable{
    public static final int VACANT = 1, OCCUPIED = 2, RESERVED = 3, UNDER_MAINTENANCE = 4;


    private int number;
    private RoomType type;
    private int availability;

    public Room(int num, boolean isSmoking, boolean hasWiFi, String facing, int bType, int available)
    {
        number = num;
        type = new RoomType(bType, isSmoking, hasWiFi, facing);
        availability = available;

    }


    public int getRoomNumber()
    {
        return number;
    }

    public int getAvailability()
    {
        return availability;
    }

    public void setAvailability(int a)
    {
        this.availability = a;
    }

    public void setBedType(int b)
    {
        this.type.setBedType(b);
    }

    public RoomType getRoomType()
    {
        return this.type;
    }



    public void showRoom()
    {
        System.out.print("Room#"+number+"\t");
        System.out.print(getRoomType().getBedTypeString()+"\t\t");
        if (type.isSmoking())
            System.out.print("Smoking\t");
        else
            System.out.print("Non-smoking\t");

        if (type.hasWiFi())
            System.out.print("WiFi\t");
        else
            System.out.print("Non-WiFi\t");

        System.out.print("Facing: "+type.getFacing()+"\t"+getAvailabilityString()+"\n");
    }




    public String getAvailabilityString()
    {
        switch(availability)
        {
            case VACANT:
                return "VACANT";
            case OCCUPIED:
                return "OCCUPIED";
            case RESERVED:
                return "RESERVED";
            case UNDER_MAINTENANCE:
                return "RESERVED";
            default:
                return "";
        }
    }



    void setHasWiFi(boolean hasWiFi) {
        this.type.setHasWiFi(hasWiFi);
    }

    void setSmoking(boolean smoking) {
        this.type.setIsSmoking(smoking);
    }

    void setFacing(String facing) {
        this.type.setFacing(facing);
    }
}
