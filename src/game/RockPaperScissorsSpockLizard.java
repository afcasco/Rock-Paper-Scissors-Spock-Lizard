package game;

class RockPaperScissorsSpockLizard extends Game {
    private final static int OPCIONS_APOSTA = 4;
    private final static String SHOW_RPSSL_OPTIONS = "0)Perda 1)Paper 2)Tissores 3)Spock 4)Llangardaix";

    /*
    Retorna el rang valid de les apostes pel joc PEDRA/PAPER/TISSORES/LLANGARDAIX/SPOCK
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
        return SHOW_RPSSL_OPTIONS;
    }
}
