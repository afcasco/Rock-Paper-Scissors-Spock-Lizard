package game;

class DadesPartida {
    private String[] jugadors;
    private String gameType;
    private int torns;
    private int[] resultat;

    void inicialitzar(String nom, int jugades, String gameType) {
        torns = jugades;
        jugadors = new String[2];
        resultat = new int[2];
        jugadors[Game.USUARI] = nom;
        jugadors[Game.MAQUINA] = "CPU";
        this.gameType = gameType;
    }

    int getTorns() {
        return torns;
    }

    void increaseUserWins() {
        resultat[Game.USUARI]++;
    }

    void increaseCPUWins() {
        resultat[Game.MAQUINA]++;
    }

    String getJugadors(int index) {
        return jugadors[index];
    }

    int getWinner() {
        if (resultat[Game.USUARI] > resultat[Game.MAQUINA]) {
            return Game.USUARI;
        } else if (resultat[Game.MAQUINA] > resultat[Game.USUARI]) {
            return Game.MAQUINA;
        } else {
            return Game.EMPAT;
        }
    }

    String getwinnerName() {
        return switch (getWinner()) {
            case 0 -> jugadors[Game.MAQUINA];
            case 1 -> jugadors[Game.USUARI];
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
                "\n   " + jugadors[Game.MAQUINA] + "\t|\t" + jugadors[Game.USUARI] +
                "\n\t" + resultat[Game.MAQUINA] + "\t|\t" + resultat[Game.USUARI] +
                "\n-------------------------------------------------------------" +
                "\nTHE WINNER IS... " + getwinnerName()) + "!" +
               "\n*************************************************************";
    }
}
