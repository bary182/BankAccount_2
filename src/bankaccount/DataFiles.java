package bankaccount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataFiles {
    
    private final String accountBalanceFilePath;
    private final String depositsFilePath;
    private final String accountHistroryFilePath;

    public DataFiles (String userName) {
        accountBalanceFilePath = userName + "_AccountBalanceFile.txt";
        File accountBalanceFile = new File(accountBalanceFilePath);
        DataFiles.ifFileNotExistsCreateNew(accountBalanceFile);
        
        depositsFilePath = userName + "_DepositsFile.txt";
        File depositsFile = new File(depositsFilePath);
        DataFiles.ifFileNotExistsCreateNew(depositsFile);
        
        accountHistroryFilePath = userName + "_AccountHistroryFile.txt";
        File accountHistroryFile = new File(accountHistroryFilePath);
        DataFiles.ifFileNotExistsCreateNew(accountHistroryFile);
    }

    private static void ifFileNotExistsCreateNew(File file) {
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DataFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static void saveAccountBalanceToFile(User user) {
        try {
            try (PrintWriter writer = new PrintWriter(user.file.accountBalanceFilePath)) {
                writer.println(user.userCurrentAccountBalance.getCurrentAccountBalance());
                writer.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static void saveDepositsToFile(User user) {
        try {
            try (PrintWriter writer = new PrintWriter(user.file.depositsFilePath)) {
                for (Deposit d : user.deposits) {
                     writer.println(d.getDepositBalance());
                }
                writer.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadAccountBalanceFromFile(User user) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(user.file.accountBalanceFilePath))) {
                String accountBalanceToLoad = reader.readLine();
                if (accountBalanceToLoad != null)
                    user.userCurrentAccountBalance.setCurrentAccountBalance(new BigDecimal(accountBalanceToLoad));
                reader.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DataFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public static void loadDepositsFromFile(User user) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(user.file.depositsFilePath))) {
                if (new File((user.file.depositsFilePath)).length() != 0)
                for (Deposit deposit : user.deposits) {
                    deposit.setDepositBalance(new BigDecimal(reader.readLine()));
                }
                reader.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DataFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}