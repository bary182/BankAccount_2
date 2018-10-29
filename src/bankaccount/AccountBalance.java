package bankaccount;

public class AccountBalance {
    private double currentAccountBalance = 0;

    public double getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public void increaseAccountBalance(double amount) {
        if (Transfer.isAmountAboveZero(amount))
            this.currentAccountBalance += amount;
        else
            Message.enterAmountAboveZero();
    }

    public void decreaseAccountBalance(double amount) {
        double temporaryBalance = this.currentAccountBalance;
        if (this.currentAccountBalance == 0) {
            Message.noFunds();
        }
        else if (amount < 0)
            Message.negativeAmount();
        else if ((temporaryBalance -= amount) < 0) {
            Message.insufficientFunds();
        }
        else
            this.currentAccountBalance -= amount;
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