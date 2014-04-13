/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class ReservationNotFoundException extends Exception{

    public ReservationNotFoundException() {
        super("No existing reservation found.");
    }

}
