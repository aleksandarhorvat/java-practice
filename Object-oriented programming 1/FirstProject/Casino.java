public class Casino {

    public static void main(String[] args) {
        Casino casino = new Casino(new Gambler[] {
            new Gambler("Pera", "poker", 15000),
                new Gambler("Mika", "blackjack", 17000),
                new Gambler("Zika", "roulette", -1500),
                new Gambler("Bora", "blackjack", -11000),
                new Gambler("Dora", "roulette", 5000),
                new Gambler("Zora", "blackjack", -3000),
                new Gambler("Lara", "poker", 22000),
                new Gambler("Mara", "poker", 7000)
        });
        System.out.println(casino);
        System.out.println(casino.mostProfitGame());
    }

    private Gambler[] gamblers;

    public Casino(Gambler[] gamblers) {
        this.gamblers = gamblers;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < gamblers.length; i++) {
            result += gamblers[i].toString() + "\n";
        }
        return result;
    }

    public String mostProfitGame() {
        int pokerS = 0;
        int blackjackS = 0;
        int rouletteS = 0;
        for (int i = 0; i < this.gamblers.length; i++) {
            if (this.gamblers[i].getCurrentGame().equals("poker") && this.gamblers[i].getBalance() < 0)
                pokerS++;
            else if (this.gamblers[i].getCurrentGame().equals("blackjack") && this.gamblers[i].getBalance() < 0)
                blackjackS++;
            else if (this.gamblers[i].getCurrentGame().equals("roulette") && this.gamblers[i].getBalance() < 0)
                rouletteS++;
        }
        if (pokerS > blackjackS && pokerS > rouletteS) {
            return "The most profitable game right now is poker.";
        } else if (blackjackS > rouletteS) {
            return "The most profitable game right now is blackjack.";
        } else {
            return "The most profitable game right now is roulette.";
        }
    }
}