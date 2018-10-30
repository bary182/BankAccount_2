package bankaccount;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transfer {
    
    public static BigDecimal enterAmount() {
        Scanner in = new Scanner(System.in);
        BigDecimal amount = new BigDecimal("0");
        System.out.print("Wprowadź kwotę: ");
            try {
                amount = in.nextBigDecimal();
                System.out.println();
            }
            catch(InputMismatchException e) {
                System.out.println("Nieprawidłowa wartość!");
                in.nextLine();
            }
        return amount;
    }
    
    public static boolean isAmountAboveZero(BigDecimal amount) {
        return (amount.compareTo(BigDecimal.ZERO) > 0);
    }
}