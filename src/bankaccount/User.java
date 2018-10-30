package bankaccount;

import java.math.BigDecimal;
import java.util.ArrayList;

public class User {

    private BigDecimal userAllDepositsBalance = new BigDecimal("0");
    String userName;
    AccountBalance userCurrentAccountBalance = new AccountBalance();
    Deposit[] deposits;
    ArrayList<AccountHistory> history = new ArrayList<>();
    

    public User (String userName, int numberOfDeposits) {
        this.userName = userName;
        deposits = new Deposit[numberOfDeposits];
        this.initiateDepostits();
    }

    public final void initiateDepostits() {
        for (int i=0; i<deposits.length; i++) {
            deposits[i] = new Deposit();
            deposits[i].id = i+1;
        }
    }
    
    public BigDecimal getUserAllDepositsBalance() {
        userAllDepositsBalance = new BigDecimal("0");
        for (Deposit d : deposits)
            userAllDepositsBalance = userAllDepositsBalance.add(d.getDepositBalance());
        return userAllDepositsBalance;
    }
    
    public BigDecimal getAllUserBalances() {
        return this.userCurrentAccountBalance.getCurrentAccountBalance().add(this.userAllDepositsBalance);
    }
    
    public void income() {
        this.userCurrentAccountBalance.increaseAccountBalance(Transfer.enterAmount());
    }
    
    public void payment() {
        if (this.userCurrentAccountBalance.getCurrentAccountBalance().compareTo(BigDecimal.ZERO) > 0)
            userCurrentAccountBalance.decreaseAccountBalance(Transfer.enterAmount());
        else
            Message.noFunds();
    }

    public void newDeposit() {
        if(this.userCurrentAccountBalance.getCurrentAccountBalance().compareTo(BigDecimal.ZERO) == 0)
            Message.noFunds();
        else if (this.hasAnyEmptyDeposit())
            deposits[Deposit.getIndexOfEmptyDeposit(deposits)].setUserDeposit(this);
        else
            Message.noEmptyDeposits();
    }
     
    public boolean hasAnyEmptyDeposit() {
        return (deposits[deposits.length-1].getDepositBalance().compareTo(BigDecimal.ZERO) == 0);
    }

    public void terminateDeposit() {
        if (this.hasUserAcitveDeposit()) {
            Deposit.terminateUserDeposit(this);
        }
        else
            Message.noActiveDeposits();
    }
     
    public boolean hasUserAcitveDeposit() {
        return (getUserAllDepositsBalance().compareTo(BigDecimal.ZERO) > 0);
    }
}