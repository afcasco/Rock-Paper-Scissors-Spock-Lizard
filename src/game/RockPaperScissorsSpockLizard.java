package game;

class RockPaperScissorsSpockLizard extends Game {
    private final static int OPCIONS_APOSTA = 4;
    private final static String SHOW_RPSSL_OPTIONS = "Choose wisely: 0) ROCK 1) PAPER 2) SCISSORS 3) SPOCK 4) LIZARD";

    /*
    Retorna el rang valid de les apostes pel joc PEDRA/PAPER/TISSORES/LLANGARDAIX/SPOCK
     */
    @Override
    int getOpcionsAposta() {
        return OPCIONS_APOSTA;
    }

    /*
    Retorna string amb opcions del joc per mostrar al jugador
     */
    @Override
    String getShowRPSOptions() {
        return SHOW_RPSSL_OPTIONS;
    }
}
