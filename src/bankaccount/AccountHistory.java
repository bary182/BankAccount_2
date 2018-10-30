package bankaccount;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class AccountHistory {
    
    private static BigDecimal currentAccountBalanceBeforeOperation = new BigDecimal("0");
    private static BigDecimal currentAllDepositBalanceBeforeOperation = new BigDecimal("0");
    private static BigDecimal currentAccountBalanceAfterOperation = new BigDecimal("0");
    private static BigDecimal currentAllDepositBalanceAfterOperation = new BigDecimal("0");
    private GregorianCalendar operationDate = new GregorianCalendar();
    private double operationAmount = 0;
    // + operationType
    
    private static BigDecimal getCurrentAccountBalanceBeforeOperation() {
        return currentAccountBalanceBeforeOperation;
    }
     
    private static BigDecimal currentAllDepositBalanceBeforeOperation() {
        return currentAllDepositBalanceBeforeOperation;
    }
    
    private static BigDecimal getCurrentAccountBalanceAfterOperation(User user) {
        return user.userCurrentAccountBalance.getCurrentAccountBalance();
    }
     
    private static BigDecimal currentAllDepositBalanceAfterOperation(User user) {
        return user.getUserAllDepositsBalance();
    }

    public static void startTrackingOperationHistory(User user) {
        currentAccountBalanceBeforeOperation = user.userCurrentAccountBalance.getCurrentAccountBalance();
        currentAllDepositBalanceBeforeOperation = user.getUserAllDepositsBalance();
    }
    
    public static void registerDoneOperation(User user) {
        System.out.println ("Saldo ostatniej operacji: \t\t " + (getCurrentAccountBalanceAfterOperation(user).subtract(getCurrentAccountBalanceBeforeOperation())));
        System.out.println ("Saldo ostatniej operacji na lokatach: \t" + (currentAllDepositBalanceAfterOperation(user).subtract(currentAllDepositBalanceBeforeOperation())));
    }
}

//    public static void przesunZdarzenia(AccountHistory[] myLok) {
//        for (int i=0; i<myLok.length-1; i++) {
//            myLok[i].rodzaj = myLok[i+1].rodzaj;
//            myLok[i].kwota = myLok[i+1].kwota;
//            myLok[i].saldoPoZdarzeniu = myLok[i+1].saldoPoZdarzeniu;
//            myLok[i].saldoLokat = myLok[i+1].saldoLokat;
//            myLok[i].data = myLok[i+1].data;
//        }
//        licznikZdarzen--;
//    }
//    void zapiszZdarzenieWplaty(double kwota) {
//        this.rodzaj = "Wpłata środków   ";
//        zmienStan(kwota);
//    }
//    
//    void zapiszZdarzenieWyplaty(double kwota) {
//        this.rodzaj = "Wypłata środków   ";
//        zmienStan(kwota);
//    }
//    
//    void zapiszZdarzenieOtwarciaLokaty (double kwota) {
//        this.rodzaj = "Otwarcie lokaty   ";
//        zmienStan(kwota);
//    }
//    
//    void zapiszZdarzenieZamknieciaLokaty(double kwota) {
//        this.rodzaj = "Zamknięcie lokaty";
//        zmienStan(kwota);
//    }