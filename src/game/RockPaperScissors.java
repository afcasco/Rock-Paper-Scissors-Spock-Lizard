package game;

class RockPaperScissors extends Game {
    private final static int OPCIONS_APOSTA = 3;
    private final static String SHOW_RPS_OPTIONS = "0)Perda 1)Paper 2)Tissores";

    /*
    Retorna el rang valid de les apostes pel joc PEDRA/PAPER/TISSORES
     */
    @Override
    int opcionsTriablesJocEscollit() {
        return OPCIONS_APOSTA;
    }

    /*
    Retorna string amb opcions del joc per mostrar al jugador
     */
    @Override
    String opcionsJocPerMostrar() {
        return SHOW_RPS_OPTIONS;
    }
}
