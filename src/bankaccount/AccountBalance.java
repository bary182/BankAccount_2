package bankaccount;

import java.math.BigDecimal;

public class AccountBalance {
    private BigDecimal currentAccountBalance = new BigDecimal("0");

    public BigDecimal getCurrentAccountBalance() {
        return currentAccountBalance;
    }
    
    public void setCurrentAccountBalance(BigDecimal amount) {
        this.currentAccountBalance = amount;
    }

    public void increaseAccountBalance(BigDecimal amount) {
        if (Transfer.isAmountAboveZero(amount))
            this.currentAccountBalance = this.currentAccountBalance.add(amount);
        else
            Message.enterAmountAboveZero();
    }

    public void decreaseAccountBalance(BigDecimal amount) {
        BigDecimal temporaryBalance = this.currentAccountBalance;
        if (this.currentAccountBalance.compareTo(BigDecimal.ZERO) == 0) {
            Message.noFunds();
        }
        else if (amount.compareTo(BigDecimal.ZERO) < 0)
            Message.negativeAmount();
        else if ((temporaryBalance.subtract(amount)).compareTo(BigDecimal.ZERO) < 0) {
            Message.insufficientFunds();
        }
        else
            this.currentAccountBalance = currentAccountBalance.subtract(amount);
    }
}