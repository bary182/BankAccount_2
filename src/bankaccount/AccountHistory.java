package bankaccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public final class AccountHistory {
    
    private BigDecimal currentAccountBalanceAfterOperation = new BigDecimal("0");
    private BigDecimal currentAllDepositBalanceAfterOperation = new BigDecimal("0");
    private BigDecimal operationAmount = new BigDecimal("0");
    enum OperationType {Incoming_Transfer, Outgoing_Transfer, Open_New_Deposit, Terminate_Deposit};
    private final OperationType operationType;
    private final Date operationDate;
    
    public AccountHistory(User user) {
        if (user.history.isEmpty()) {
            this.operationAmount = this.getFirstIncomingTransfer(user);
            this.operationType = OperationType.Incoming_Transfer;
            this.operationDate = new GregorianCalendar().getTime();
            this.currentAccountBalanceAfterOperation = user.userCurrentAccountBalance.getCurrentAccountBalance();
            this.currentAllDepositBalanceAfterOperation = user.getUserAllDepositsBalance();
        }
        else {
            this.operationAmount = this.recognizeOperationAmount(user);
            this.operationType = this.recognizeOperationType(user);
            this.operationDate = new GregorianCalendar().getTime();
            this.currentAccountBalanceAfterOperation = user.userCurrentAccountBalance.getCurrentAccountBalance();
            this.currentAllDepositBalanceAfterOperation = user.getUserAllDepositsBalance();
        }
    }

    private BigDecimal getFirstIncomingTransfer (User user) {
        return user.userCurrentAccountBalance.getCurrentAccountBalance();
    }

    private BigDecimal recognizeOperationAmount (User user) {
        if (this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceNotChanged(user))
            return this.incomingTransferAmount(user);
        else if (!this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceNotChanged(user))
            return this.outgoingTransferAmount(user);
        else if (!this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceIncreased(user))
            return this.depositOpenAmount(user);
        else
            return this.depositCloseAmount(user);
    }
    
    private BigDecimal incomingTransferAmount(User user) {
        return user.userCurrentAccountBalance.getCurrentAccountBalance().subtract(user.history.get(user.history.size()-1).currentAccountBalanceAfterOperation);
    }
    
    private BigDecimal outgoingTransferAmount(User user) {
        return user.history.get(user.history.size()-1).currentAccountBalanceAfterOperation.subtract(user.userCurrentAccountBalance.getCurrentAccountBalance());
    }
        
    private BigDecimal depositOpenAmount(User user) {
        return user.history.get(user.history.size()-1).currentAccountBalanceAfterOperation.subtract(user.userCurrentAccountBalance.getCurrentAccountBalance());
    }
            
    private BigDecimal depositCloseAmount(User user) {
        return user.userCurrentAccountBalance.getCurrentAccountBalance().subtract(user.history.get(user.history.size()-1).currentAccountBalanceAfterOperation);
    }

    private OperationType recognizeOperationType (User user) {
        if (this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceNotChanged(user))
            return OperationType.Incoming_Transfer;
        else if (!this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceNotChanged(user))
            return OperationType.Outgoing_Transfer;
        else if (!this.currentAccountBalanceIncreased(user) && this.currentAllDepositBalanceIncreased(user))
            return OperationType.Open_New_Deposit;
        else
            return OperationType.Terminate_Deposit;
    }
    
    private boolean currentAccountBalanceIncreased(User user) {
        return user.userCurrentAccountBalance.getCurrentAccountBalance().compareTo(user.history.get(user.history.size()-1).currentAccountBalanceAfterOperation) > 0;
    }
    
    private boolean currentAllDepositBalanceNotChanged(User user) {
        return (user.getUserAllDepositsBalance().compareTo(user.history.get(user.history.size()-1).currentAllDepositBalanceAfterOperation)) == 0;
    }
        
    private boolean currentAllDepositBalanceIncreased(User user) {
        return (user.getUserAllDepositsBalance().compareTo(user.history.get(user.history.size()-1).currentAllDepositBalanceAfterOperation)) > 0;
    }
    
    public BigDecimal getOperationAmount () {
        return this.operationAmount;
    }
    
    public String getOperationType() {
        return operationType.toString();
    }

    public BigDecimal getCurrentAccountBalanceAfterOperation () {
        return this.currentAccountBalanceAfterOperation;
    }
    
    public Date getOperationDate () {
        return this.operationDate;
    }
}