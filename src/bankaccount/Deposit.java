package bankaccount;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigDecimal;

public class Deposit {
    private BigDecimal depositBalance = new BigDecimal("0");
    int id;
    
    public BigDecimal getDepositBalance() {
        return this.depositBalance;
    }
    
    public void setDepositBalance (BigDecimal amount) {
        this.depositBalance = this.depositBalance.add(amount);
    }
    
    public void setDepositBalanceToZero() {
        this.depositBalance = new BigDecimal("0");
    }

    public void setUserDeposit (User user) {
        BigDecimal bufferedDepositBalance = Transfer.enterAmount();
        if (!Transfer.isAmountAboveZero(bufferedDepositBalance))
            Message.enterAmountAboveZero();
        else if (bufferedDepositBalance.compareTo(user.userCurrentAccountBalance.getCurrentAccountBalance()) > 0)
            Message.insufficientFunds();
        else {
            user.deposits[getAnIndexOfEmptyDeposit(user.deposits)].setDepositBalance(bufferedDepositBalance);
            user.userCurrentAccountBalance.decreaseAccountBalance(bufferedDepositBalance);
        }
    }

    public static int getAnIndexOfEmptyDeposit(Deposit[] deposits) {
        int i = 0;
        while (i<deposits.length-1) {
            if (deposits[i].getDepositBalance().compareTo(BigDecimal.ZERO) == 0)
                break;
            else
            i++;
        }
        return i;
    }
    
    public static void terminateUserDeposit(User user) {
        ReportPrinter.printListOfUserDeposits(user);
        Message.enterIndexOfDepositToTerminate();

        int depositIndex = getIndexOfDepositToTerminate();

        if (isIndexInRangeOfUserDepositsLimitAndIsDepositExists(depositIndex, user.deposits)) {
            user.userCurrentAccountBalance.increaseAccountBalance(user.deposits[depositIndex].getDepositBalance());
            user.deposits[depositIndex].setDepositBalanceToZero();
            organizeDepositsAfterTerminatedOneOfThem(user.deposits);
        }
        else
            Message.wrongDepositIndex();
    }

    public static int getIndexOfDepositToTerminate() {
        Scanner in = new Scanner(System.in);
        int depositIndex = -1;
        try {
            depositIndex = (in.nextInt() - 1);
        }
        catch(InputMismatchException e) {
            e.getMessage();
            in.nextLine();
        }
        return depositIndex;
    }
    
    public static boolean isIndexInRangeOfUserDepositsLimitAndIsDepositExists(int value, Deposit[] deposit) {
        return (value >= 0 && value < deposit.length && deposit[value].getDepositBalance().compareTo(BigDecimal.ZERO) > 0);
    }
    
    public static void organizeDepositsAfterTerminatedOneOfThem(Deposit[] deposit) {
        for (int i=0; i<deposit.length-1; i++)
            if (deposit[i].getDepositBalance().compareTo(BigDecimal.ZERO) == 0 && deposit[i+1].getDepositBalance().compareTo(BigDecimal.ZERO) > 0) {
                deposit[i].setDepositBalance(deposit[i+1].getDepositBalance());
                deposit[i].id = i+1;
                deposit[i+1].setDepositBalanceToZero();
            }
    }  
}