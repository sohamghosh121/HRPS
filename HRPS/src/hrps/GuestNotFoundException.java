/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class GuestNotFoundException extends Exception{
    public GuestNotFoundException()
    {
        super("Guest not found.");
    }

}
