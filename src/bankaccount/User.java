package bankaccount;

import java.math.BigDecimal;
import java.util.ArrayList;

public class User {

    private BigDecimal userAllDepositsBalance = new BigDecimal("0");
    private final String userName;
    AccountBalance userCurrentAccountBalance = new AccountBalance();
    Deposit[] deposits;
    ArrayList<AccountHistory> history = new ArrayList<>();
    DataFiles file;

    public User (String userName, int numberOfDeposits) {
        this.userName = userName;
        deposits = new Deposit[numberOfDeposits];
        this.initiateDepostits();
        file = new DataFiles(userName);
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
    
    public String getUserName() {
        return this.userName;
    }
    
    public void incomingTransfer() {
        this.userCurrentAccountBalance.increaseAccountBalance(Transfer.enterAmount());
    }
    
    public void outgoingTransfer() {
        if (this.userCurrentAccountBalance.getCurrentAccountBalance().compareTo(BigDecimal.ZERO) > 0)
            userCurrentAccountBalance.decreaseAccountBalance(Transfer.enterAmount());
        else
            Message.noFunds();
    }

    public void newDeposit() {
        if(!this.hasAnyFundsToOpenDeposit())
            Message.noFunds();
        else if (this.hasAnyEmptyDeposit())
            deposits[Deposit.getAnIndexOfEmptyDeposit(deposits)].setUserDeposit(this);
        else
            Message.noEmptyDeposits();
    }

    public boolean hasAnyFundsToOpenDeposit() {
        return (userCurrentAccountBalance.getCurrentAccountBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    public boolean hasAnyEmptyDeposit() {
        return (deposits[deposits.length-1].getDepositBalance().compareTo(BigDecimal.ZERO) == 0);
    }

    public void terminateDeposit() {
        if (this.hasUserAnyAcitveDeposit()) {
            Deposit.terminateUserDeposit(this);
        }
        else
            Message.noActiveDeposits();
    }
     
    public boolean hasUserAnyAcitveDeposit() {
        return (getUserAllDepositsBalance().compareTo(BigDecimal.ZERO) > 0);
    }
    
    public void registerHistory() {
        if (this.hasUserDoneOperationToRegisterInAccountHistory())
            this.history.add(new AccountHistory(this));
    }

    public boolean hasUserDoneOperationToRegisterInAccountHistory() {
        if (this.history.isEmpty())
            return this.userCurrentAccountBalance.getCurrentAccountBalance().compareTo(BigDecimal.ZERO) > 0;
        else
            return (this.userCurrentAccountBalance.getCurrentAccountBalance().compareTo
                (this.history.get(this.history.size()-1).getCurrentAccountBalanceAfterOperation()) != 0);
    }
}