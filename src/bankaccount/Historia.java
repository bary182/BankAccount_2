package bankaccount;

import java.util.Date;

public class Historia {
    private String rodzaj = "Empty";
    private double kwota = 0;
    private static int licznikZdarzen = 0;
    private double saldoPoZdarzeniu;
    private double saldoLokat;
    private Date data;
    
    void zapiszZdarzenieWplaty(double kwota) {
        this.rodzaj = "Wpłata środków   ";
        zmienStan(kwota);
    }
    
    void zapiszZdarzenieWyplaty(double kwota) {
        this.rodzaj = "Wypłata środków   ";
        zmienStan(kwota);
    }
    
    void zapiszZdarzenieOtwarciaLokaty (double kwota) {
        this.rodzaj = "Otwarcie lokaty   ";
        zmienStan(kwota);
    }
    
    void zapiszZdarzenieZamknieciaLokaty(double kwota) {
        this.rodzaj = "Zamknięcie lokaty";
        zmienStan(kwota);
    }
    
    public void zmienStan(double kwota){
            this.kwota = kwota;
//            this.saldoPoZdarzeniu = Balance.getBalance();
//            this.saldoLokat = Deposit.getAllSaldoLokat();
            this.data = new Date();
            licznikZdarzen++;
    }
    
    public static void przesunZdarzenia(Historia[] myLok) {
        for (int i=0; i<myLok.length-1; i++) {
            myLok[i].rodzaj = myLok[i+1].rodzaj;
            myLok[i].kwota = myLok[i+1].kwota;
            myLok[i].saldoPoZdarzeniu = myLok[i+1].saldoPoZdarzeniu;
            myLok[i].saldoLokat = myLok[i+1].saldoLokat;
            myLok[i].data = myLok[i+1].data;
        }
        licznikZdarzen--;
    }
    
    @Override
    public String toString() {
        if(this.saldoLokat != 0)
            return this.data + "\t" + this.rodzaj + "\t" + "Kwota operacji: " + this.kwota + "\t" + "Saldo konta: " +
                this.saldoPoZdarzeniu + "\t" + "Saldo lokat: " + this.saldoLokat;
        else
            return this.data + "\t" + this.rodzaj + "\t" + "Kwota operacji: " + this.kwota + "\t" + "Saldo konta: " +
                this.saldoPoZdarzeniu;
    }
    
    public static int getLicznikZdarzen() {
        return licznikZdarzen;
    }
    
    public double getKwotaZdarzenia() {
        return this.kwota;
    }
    public String getRodzajZdarzenia() {
        return this.rodzaj;
    }
}