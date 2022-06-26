package game;

class RockPaperScissors extends Game {
    private final static int OPCIONS_APOSTA = 2;
    private final static String SHOW_RPS_OPTIONS = "Choose wisely: 0) ROCK 1) PAPER 2) SCISSORS";

    /*
    Retorna el rang valid de les apostes pel joc PEDRA/PAPER/TISSORES
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
        return SHOW_RPS_OPTIONS;
    }
}
