/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

/**
 *
 * @author Soham G
 */
public class InvalidCreditCardException extends Exception {
    public InvalidCreditCardException()
    {
        super("Invalid credit card number. ");
    }

}
