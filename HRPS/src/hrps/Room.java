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
    public static final int SINGLE = 1, DOUBLE = 2, MASTER = 3;
    public static final int VACANT = 1, OCCUPIED = 2, RESERVED = 3, UNDER_MAINTENANCE = 4;


    private int number;
    private RoomType type;
    private int bedType;
    private int availability;

    public Room(int num, boolean isSmoking, boolean hasWiFi, String facing, int bType, int available)
    {
        number = num;
        type = new RoomType(isSmoking, hasWiFi, facing);
        bedType = bType;
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

    public int getBedType()
    {
        return bedType;
    }

    public void showRoom()
    {
        System.out.println("Room "+number+"\t"+getBedTypeString()+"\t"+getAvailabilityString());
    }

    public String getBedTypeString()
    {
        switch (bedType)
        {
            case SINGLE:
                return "Single";
            case DOUBLE:
                return "Double";
            case MASTER:
                return "Master";
            default:
                return "";
        }
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
}
