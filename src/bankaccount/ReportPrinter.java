package bankaccount;

public class ReportPrinter {

    public static void printUserAccountAndDepostitsBalance(User user) {
        printCurrentAccountBalance(user);
        printUserAllDepositsBalance(user);
        System.out.println("Łączne saldo środków wynosi: \t" + user.getAllUserBalances() + " PLN \n");
    }
    
    public static void printCurrentAccountBalance (User user) {
        System.out.println("Saldo bieżące wynosi: \t\t" + user.userCurrentAccountBalance.getCurrentAccountBalance() + " PLN");
    }   
    
    public static void printUserAllDepositsBalance (User user) {
        System.out.println("Saldo lokat wynosi: \t\t" + user.getUserAllDepositsBalance() + " PLN");
    }
    
    public static void printListOfUserDeposits (User user) {
        if (!user.hasUserAcitveDeposit())
            Message.noActiveDeposits();
        else
            for (Deposit d : user.deposits) {
                if (d.getDepositBalance() != 0)
                    System.out.println("Lokata nr " + d.id + "\t" + "Kwota lokaty: " + d.getDepositBalance());
        }
    }
}