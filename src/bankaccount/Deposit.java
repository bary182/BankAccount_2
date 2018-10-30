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
    
    public void increaseDepositBalance (BigDecimal amount) {
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
            user.deposits[getIndexOfEmptyDeposit(user.deposits)].increaseDepositBalance(bufferedDepositBalance);
            user.userCurrentAccountBalance.decreaseAccountBalance(bufferedDepositBalance);
        }
    }

    public static int getIndexOfEmptyDeposit(Deposit[] deposits) {
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

        if (isIndexInRangeOfUserDepositsLimitAndDepositExists(depositIndex, user.deposits)) {
            user.userCurrentAccountBalance.increaseAccountBalance(user.deposits[depositIndex].getDepositBalance());
            user.deposits[depositIndex].setDepositBalanceToZero();
            organizeDepositsAfterTerminatedOneOfThem(user.deposits);
        }
        else
            Message.wrongDepositIndex();
    }
     
    public static boolean isIndexInRangeOfUserDepositsLimitAndDepositExists(int value, Deposit[] deposit) {
        return (value >= 0 && value < deposit.length && deposit[value].getDepositBalance().compareTo(BigDecimal.ZERO) > 0);
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
    
    public static void organizeDepositsAfterTerminatedOneOfThem(Deposit[] deposit) {
        for (int i=0; i<deposit.length-1; i++)
            if (deposit[i].getDepositBalance().compareTo(BigDecimal.ZERO) == 0 && deposit[i+1].getDepositBalance().compareTo(BigDecimal.ZERO) > 0) {
                deposit[i].increaseDepositBalance(deposit[i+1].getDepositBalance());
                deposit[i].id = i+1;
                deposit[i+1].setDepositBalanceToZero();
            }
    }  
}






//    BufferedWriter zapis;
//    BufferedReader odczyt;
//    
//    void zamknijLokate() {
//        if (saldoLokaty == 0)
//                System.out.println("Nie masz lokaty takim numerze!");
//        else {    
//            Balance.increaseBalance(this.saldoLokaty);
//            allSaldoLokat -= this.saldoLokaty;
//            liczbaLokat--;
//            File plik = new File("Lokata_" + this.id + ".txt");
//            plik.delete();
//            this.id = 0;
//            this.saldoLokaty = 0;
//            this.name = "Empty";
//        }
//        
//        // nadanie nowej numeracji plikom przechowującym dane lokat
//        for (int i=1; i<=liczbaLokat; i++) {
//             File plik = new File("Lokata_" + i + ".txt");
//             File plik2 = new File("Lokata_" + (i+1) + ".txt");             
//             if(plik.exists())
//                 continue;
//             plik2.renameTo(plik);
//        }
//    
//    public static int getLiczbaLokat() {
//        return liczbaLokat;
//    }    
//    public static double getAllSaldoLokat() {
//        return allSaldoLokat;
//    }
//    
//    public void zapiszStanLokaty() throws IOException {
//        try {
//            zapis = new BufferedWriter(new FileWriter("Lokata_" + this.id + ".txt"));
//            zapis.write(this.name);
//            zapis.newLine();
//            zapis.write(Double.toString(this.getSaldoLokaty()));
//        }
//        catch (IOException ex) {
//            System.out.println("Nie udało się zapisać stanu konta po operacji!");
//        }
//        finally {
//            if (zapis != null)
//                zapis.close();
//        }
//    }
//        
//    public void wczytajLokate() throws IOException {
//        try {
//            liczbaLokat++;
//            this.id = liczbaLokat;
//            String lokata = "Lokata_" + this.id + ".txt";
//            odczyt = new BufferedReader(new FileReader(lokata));
//            this.name = odczyt.readLine();
//            String stanLokaty = odczyt.readLine();
//            saldoLokaty = Double.parseDouble(stanLokaty);
//            allSaldoLokat += saldoLokaty;
//            }
//        catch (FileNotFoundException ex) {
//            System.out.println("Nie udało się wczytać lokaty!");
//        }
//        finally {
//            if (odczyt != null)
//                odczyt.close();
//        }    
//    }