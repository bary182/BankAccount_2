package bankaccount;

import java.math.BigDecimal;

public class AccountBalance {
    private BigDecimal currentAccountBalance = new BigDecimal("100");

    public BigDecimal getCurrentAccountBalance() {
        return currentAccountBalance;
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
        else if (amount.compareTo(BigDecimal.ZERO) == 0)
            Message.negativeAmount();
        else if ((temporaryBalance = temporaryBalance.subtract(amount)).compareTo(BigDecimal.ZERO) < 0) {
            Message.insufficientFunds();
        }
        else
            this.currentAccountBalance = currentAccountBalance.subtract(amount);
    }
}
    
//    FileWriter save;
//    BufferedReader read;
//    
// public Balance() throws IOException {
//     this.loadBalance();
// }

//    public void loadBalance() throws IOException {
//        try {
//            read = new BufferedReader(new FileReader("Saldo.txt"));
//            String stanKonta = read.readLine();
//            balance = Double.parseDouble(stanKonta);
//        }
//        catch (FileNotFoundException ex) {
//            System.out.println("Saldo początkowe wynosi 0,00 PLN \n");
//        }
//        finally {
//            if (read != null)
//                read.close();
//        }
//    
//    public void saveBalanceToFile() throws IOException {
//        try {
//            save = new FileWriter("Saldo.txt");
//            save.write(Double.toString(getBalance()));
//        }
//        catch (IOException ex) {
//            System.out.println("Nie udało się zapisać stanu konta po operacji!");
//        }
//        finally {
//            if (save != null)
//                save.close();
//        }
//        
//    }
//}