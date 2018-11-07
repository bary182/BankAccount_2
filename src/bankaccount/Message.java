package bankaccount;

public class Message {

    public static void logIn() {
        System.out.println("Zalogowałeś się do konta bankowego:");
    }

    public static void enterAmountAboveZero() {
        System.out.println("Wprowadź kwotę większą niż 0,00 \n");
    }

    public static void noFunds() {
        System.out.println("Brak dostępnych środków! \n");
    }
    
    public static void insufficientFunds() {
        System.out.println("Brak wystarczającej ilości środków!");
    }
     
    public static void negativeAmount() {
        System.out.println("Nie możesz wypłacić ujemnej kwoty!");
    }
    
    public static void wrongChoice() {
        System.out.println("Błędny wybór!");
    }

    public static void noEmptyDeposits() {    
        System.out.println("Wykorzystałeś wszytskie lokaty!");
    }
    public static void logOut() {        
        System.out.println("Jesteś wylogowany!");
    }
    
    public static void noActiveDeposits() {  
        System.out.println("Nie masz otwartych lokat!");
    }
    
    public static void noAccountHistory() {  
        System.out.println("Brak zarejestrowanych operacji!");
    }

    public static void enterIndexOfDepositToTerminate() {    
        System.out.print("Podaj nr lokaty do zamknięcia: ");
    }

    public static void wrongDepositIndex() {       
        System.out.println("Nieprawidłowy nr lokaty!");
    }
}