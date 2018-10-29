package bankaccount;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    
    public static void run(User user) {
        int choice = menu();
        while(choice != 6) {
            switch(choice) {
                case 1: ReportPrinter.printUserAccountAndDepostitsBalance(user);
                    break;
                case 2: user.userCurrentAccountBalance.increaseAccountBalance(Transfer.enterAmount());
                    break;
                case 3: user.payment();
                    break;
                case 4: int lokataChoice = depositMenu();
                        while (lokataChoice != 4) {
                            switch(lokataChoice) {
                                case 1: user.newDeposit();
                                    break;
                                case 2: ReportPrinter.printListOfUserDeposits(user);
                                    break;
                                case 3: user.terminateDeposit();
                                    break;
                                default: Message.wrongChoice();
                                    break;
                            }
                            lokataChoice = depositMenu();
                        }
                    break;
                case 5:
                    break;
                default: Message.wrongChoice();
                    break;
            }         
            choice = Menu.menu();
        }
    }

    public static void depositRun() {
        depositMenu();
    }

    public static int menu() {
        System.out.println("MENU GŁÓWNE:");
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