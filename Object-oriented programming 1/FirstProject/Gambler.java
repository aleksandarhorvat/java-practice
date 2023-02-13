public class Gambler {

    private String name;
    private String currentGame;
    private int balance;

    public Gambler(String name, String currentGame, int balance) {
        this.name = name;
        setCurrentGame(currentGame);
        this.balance = balance;
    }

    public String toString() {
        return name + " plays " + currentGame + " and has " + balance + " on account.";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCurrentGame(String name) {
        if (name.equals("poker") || name.equals("ajnc") || name.equals("rulet"))
            this.currentGame = name;
        else
            this.currentGame = "poker";
    }

    public String getCurrentGame() {
        return this.currentGame;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }
}