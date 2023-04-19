package rockpaperscissors;

public enum Option {
    PAPER("ROCK", "SCISSORS"),
    SCISSORS("PAPER", "ROCK"),
    ROCK("SCISSORS", "PAPER");

    final String wins;
    final String loses;

    Option(String wins, String loses) {
        this.wins = wins;
        this.loses = loses;
    }
}
