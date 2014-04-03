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
public class RoomType implements Serializable{
    public static final int SINGLE = 1, DOUBLE = 2, MASTER = 3;
    private boolean isSmoking;
    private boolean hasWiFi;
    private String facing;
    private int bedType;

    public RoomType(int bedType, boolean isSmoking, boolean hasWiFi, String facing)
    {
        this.bedType = bedType;
        this.isSmoking = isSmoking;
        this.hasWiFi = hasWiFi;
        this.facing = facing;
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

    public int getBedType()
    {
        return this.bedType;
    }

    public void setBedType(int b)
    {
        this.bedType = b;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public void setIsSmoking(boolean isSmoking) {
        this.isSmoking = isSmoking;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    
    public boolean hasWiFi() {
        return hasWiFi;
    }

    public String getFacing() {
        return facing;
    }

    public String toString()
    {
        String s= "";
        if (isSmoking)
            s += "Smoking";
        else
            s+= "Non-smoking";

        s+="\t";

        if (hasWiFi)
            s+="WiFi";
        else
            s+="No Wifi";

        s+="\t";

        s+="Facing: "+facing;
        return s;
    }
}
