package bankaccount;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Transfer {
    
    public static double enterAmount() {
        Scanner in = new Scanner(System.in);
        double amount = 0;
        System.out.print("Wprowadź kwotę: ");
            try {
                amount = in.nextDouble();
                System.out.println();
            }
            catch(InputMismatchException e) {
                System.out.println("Nieprawidłowa wartość!");
                in.nextLine();
            }
        return amount;
    }
    
    public static boolean isAmountAboveZero(double amount) {
        return (amount > 0);
    }
}