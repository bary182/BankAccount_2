package bankaccount;

public class BankAccount {

    public static void main(String[] args) {

        User testUser = new User("Test", 5);

        Message.logIn();

        UserMenu.run(testUser);

        Message.logOut();
    }
}