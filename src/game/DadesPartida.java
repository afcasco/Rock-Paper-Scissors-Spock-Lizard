package game;

class DadesPartida {
    int torn;
    int tornsTotals;
    int[] resultat = new int[2];
    String[] jugadors = {"MAQUINA", "?"};
    String gameType = "";

    void inicialitzar(String nom, int jugades, String gameType) {
        torn = 0;
        tornsTotals = jugades;
        resultat[Game.MAQUINA] = 0;
        resultat[Game.USUARI] = 0;
        jugadors[Game.USUARI] = nom;
        this.gameType = gameType;
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
        return ("---------------------------------------------------------------------+" +
                "\nAquest joc era de tipus: " + gameType +
                "\nGUANYADOR FINAL: " + getwinnerName());
    }
}
