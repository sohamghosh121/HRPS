/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class CheckInFailedException extends Exception {
    CheckInFailedException(String msg)
    {
        super("Check-in failed. "+msg);
    }

}
