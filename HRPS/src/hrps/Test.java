/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.Calendar;

/**
 *
 * @author Soham G
 */
public class Test {

    public static void main (String args[])
    {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DATE, 7);
        System.out.println(now.getTime().toString());

    }
}
