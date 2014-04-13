/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class RoomNotFoundException extends Exception{
    public RoomNotFoundException()
    {
        super("Room not found.");
    }

}
