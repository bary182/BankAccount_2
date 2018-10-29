package bankaccount;

public class User {

    private double userAllDepositsBalance;
    String userName;
    AccountBalance userCurrentAccountBalance = new AccountBalance();
    Deposit[] deposits;
    

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
    
    public double getUserAllDepositsBalance() {
        userAllDepositsBalance = 0;
        for (Deposit d : deposits)
            userAllDepositsBalance += d.getDepositBalance();
        return userAllDepositsBalance;
    }
    
    public double getAllUserBalances() {
        return this.userCurrentAccountBalance.getCurrentAccountBalance() + this.userAllDepositsBalance;
    }
    
    public void payment() {
        if (this.userCurrentAccountBalance.getCurrentAccountBalance() !=0)
            userCurrentAccountBalance.decreaseAccountBalance(Transfer.enterAmount());
        else
            Message.noFunds();
    }

    public void newDeposit() {
        if(this.userCurrentAccountBalance.getCurrentAccountBalance() == 0)
            Message.noFunds();
        else if (this.hasAnyEmptyDeposit())
            deposits[Deposit.getIndexOfEmptyDeposit(deposits)].setUserDeposit(this);
        else
            Message.noEmptyDeposits();
    }
     
    public boolean hasAnyEmptyDeposit() {
        return (deposits[deposits.length-1].getDepositBalance() == 0);
    }

    public void terminateDeposit() {
        if (this.hasUserAcitveDeposit()) {
            Deposit.terminateUserDeposit(this);
        }
        else
            Message.noActiveDeposits();
    }
     
    public boolean hasUserAcitveDeposit() {
        return (getUserAllDepositsBalance() != 0);
    }
}