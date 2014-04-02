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
    private boolean isSmoking;
    private boolean hasWiFi;
    private String facing;

    public RoomType(boolean isSmoking, boolean hasWiFi, String facing)
    {
        this.isSmoking = isSmoking;
        this.hasWiFi = hasWiFi;
        this.facing = facing;
    }

    public boolean isSmoking() {
        return isSmoking;
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
