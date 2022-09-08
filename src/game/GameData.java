package game;

class GameData {
    private String[] jugadors;
    private String gameType;
    private int torns;
    private int[] resultat;

    void initialize(String nom, int jugades, String gameType) {
        torns = jugades;
        jugadors = new String[2];
        resultat = new int[2];
        jugadors[Game.PLAYER] = nom;
        jugadors[Game.CPU] = "CPU";
        this.gameType = gameType;
    }

    int getTorns() {
        return torns;
    }

    void increaseUserWins() {
        resultat[Game.PLAYER]++;
    }

    void increaseCPUWins() {
        resultat[Game.CPU]++;
    }

    String getJugadors(int index) {
        return jugadors[index];
    }

    int getWinner() {
        if (resultat[Game.PLAYER] > resultat[Game.CPU]) {
            return Game.PLAYER;
        } else if (resultat[Game.CPU] > resultat[Game.PLAYER]) {
            return Game.CPU;
        } else {
            return Game.DRAW;
        }
    }

    String getwinnerName() {
        return switch (getWinner()) {
            case 0 -> jugadors[Game.CPU];
            case 1 -> jugadors[Game.PLAYER];
            default -> "EMPAT";
        };
    }

    @Override
    public String toString() {
        return ("*************************************************************" +
                "\nGAMETYPE: " + gameType +
                "\n-------------------------------------------------------------" +
                "\nGAME RESULTS:" +
                "\n-------------------------------------------------------------" +
                "\n   " + jugadors[Game.CPU] + "\t|\t" + jugadors[Game.PLAYER] +
                "\n\t" + resultat[Game.CPU] + "\t|\t" + resultat[Game.PLAYER] +
                "\n-------------------------------------------------------------" +
                "\nTHE WINNER IS... " + getwinnerName()) + "!" +
                "\n*************************************************************";
    }
}
