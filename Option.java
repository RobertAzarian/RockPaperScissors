package rockpaperscissors;

public enum Option {
    PAPER("rock", "scissors"),
    SCISSORS("paper", "rock"),
    ROCK("scissors", "paper");

    final String wins;
    final String loses;

    Option(String wins, String loses) {
        this.wins = wins;
        this.loses = loses;
    }
}
