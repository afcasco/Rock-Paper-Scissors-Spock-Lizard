package game;

class DadesPartida {
    int torn;
    int tornsTotals;
    int guanyador;
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

    @Override
    public String toString() {
        String nomGuanyador = "EMPAT";
        if (guanyador != 2) nomGuanyador = jugadors[guanyador];
        return ("---------------------------------------------------------------------+" +
                "\nAquest joc era de tipus: " + gameType +
                "\nGUANYADOR FINAL: " + nomGuanyador);
    }
}
