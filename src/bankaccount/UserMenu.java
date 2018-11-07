package bankaccount;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {
    public static void run(User user) {
        DataFiles.loadAccountBalanceFromFile(user);
        DataFiles.loadDepositsFromFile(user);
        int choice = menu();
        while(choice != 6) {
            switch(choice) {
                case 1: ReportPrinter.printUserAccountAndDepostitsBalance(user);
                    break;
                case 2: user.incomingTransfer();
                        user.registerHistory();
                    break;
                case 3: user.outgoingTransfer();
                        user.registerHistory();
                    break;
                case 4: int depositMenuChoice = depositMenu();
                        while (depositMenuChoice != 4) {
                            switch(depositMenuChoice) {
                                case 1: user.newDeposit();
                                        user.registerHistory();
                                    break;
                                case 2: ReportPrinter.printListOfUserDeposits(user);
                                    break;
                                case 3: user.terminateDeposit();
                                        user.registerHistory();
                                    break;
                                default: Message.wrongChoice();
                                    break;
                            }
                            depositMenuChoice = depositMenu();
                        }
                    break;
                case 5: ReportPrinter.printAccountHistory(user);
                    break;
                default: Message.wrongChoice();
                    break;
            }
            DataFiles.saveAccountBalanceToFile(user);
            DataFiles.saveDepositsToFile(user);
            choice = menu();
        }
    }

    public static int menu() {
        System.out.println("\nMENU GŁÓWNE:");
        System.out.println("1. Sprawdź saldo");
        System.out.println("2. Wpłać środki");
        System.out.println("3. Wykonaj przelew");
        System.out.println("4. Lokaty");
        System.out.println("5. Historia rachunku");
        System.out.println("6. Wyloguj");
        System.out.println();

        Scanner in = new Scanner(System.in);
        int choice = 0;
        try {
            choice = in.nextInt();
        }
        catch(InputMismatchException e) {
            e.getMessage();
        }
        return choice;
    }
    
    public static int depositMenu() {
        System.out.println();
        System.out.println("MENU LOKAT:");
        System.out.println("1. Otwórz lokatę");
        System.out.println("2. Pokaż otwarte lokaty");
        System.out.println("3. Zamknij lokatę");
        System.out.println("4. Powrót do Menu Głównego");
        System.out.println();

        Scanner in = new Scanner(System.in);
        int choice = 0;
        try {
            choice = in.nextInt();
        }
        catch(InputMismatchException e) {
            e.getMessage();
        }
        return choice;
    }
}