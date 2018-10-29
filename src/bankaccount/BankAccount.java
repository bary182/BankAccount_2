package bankaccount;

public class BankAccount {

    public static void main(String[] args) {

        User testUser = new User("Test", 5);

        Message.logIn();

            Menu.run(testUser);

        Message.logOut();
    }
}

//    // Wczytywanie istniejących lokat
//    File plik = new File("Lokata_" + (i+1) + ".txt");
//    if (plik.exists())
//        deposit[i].wczytajLokate();
//    }
//
//    Historia[] historia = new Historia[10];
//    for (int i=0; i<historia.length; i++) {
//    historia[i] = new Historia();
//    }
//                case 2: double czyWykonanoZdarzenie = Balance.getBalance();
//                        balance.income();
//                        if (czyWykonanoZdarzenie != Balance.getBalance()) {
//                            if (Historia.getLicznikZdarzen() > historia.length-1)
//                                Historia.przesunZdarzenia(historia);
//                            historia[Historia.getLicznikZdarzen()].zapiszZdarzenieWplaty(Balance.getBalance() - czyWykonanoZdarzenie);
//                            balance.saveBalanceToFile();
//                        }
//                        break;
//                //wypłata
//                case 3: czyWykonanoZdarzenie = Balance.getBalance();
//                        balance.payoff();
//                        if (czyWykonanoZdarzenie != Balance.getBalance()) {
//                            if (Historia.getLicznikZdarzen() > historia.length-1)
//                                Historia.przesunZdarzenia(historia);
//                            historia[Historia.getLicznikZdarzen()].zapiszZdarzenieWyplaty(czyWykonanoZdarzenie - Balance.getBalance());
//                            balance.saveBalanceToFile();
//                        }
//                        break;
//                case 4: int lokataChoice = Menu.menuLokat();
//                        while (lokataChoice != 4) {
//                                switch(lokataChoice) {
//                                    // Otwieranie lokat
//                                    case 1: if (Deposit.getLiczbaLokat() == deposit.length)
//                                                System.out.println("Wykorzystałeś wszytskie lokaty!");
//                                            else {
//                                                 // zmienna do sprawdzenia czy otwarto lokatę
//                                                czyWykonanoZdarzenie = Deposit.getAllSaldoLokat();
//                                                deposit[Deposit.getLiczbaLokat()].createDeposit();
//                                                     // sprawdzenie czy otwarto lokatę w celu rejestracji bądź nie w historii rachunku
//                                                    if (czyWykonanoZdarzenie != Deposit.getAllSaldoLokat()) {
//                                                        if (Historia.getLicznikZdarzen() > historia.length-1)
//                                                            Historia.przesunZdarzenia(historia);
//                                                        historia[Historia.getLicznikZdarzen()].zapiszZdarzenieOtwarciaLokaty
//                                                                            (Deposit.getAllSaldoLokat() - czyWykonanoZdarzenie);
//                                                        balance.saveBalanceToFile();
//                                                    }
//                                            }
//                                            break;
//                                    // Wydruk listy lokat
//                                    case 2: if (Deposit.getAllSaldoLokat() != 0)
//                                                for (Deposit myLok : deposit) {
//                                                    if (myLok.getSaldoLokaty() != 0)
//                                                        System.out.println(myLok.toString());
//                                                }
//                                            else
//                                                System.out.println("Nie masz otwartych lokat!");

//                case 5: System.out.println("Historia 10 ostatnich operacji:");
//                        System.out.println();
//                        for (Historia myLok : historia) {
//                           if (myLok.getRodzajZdarzenia() != "Empty")         //pominięcie w wydruku pustych rekordów
//                                System.out.println(myLok.toString());