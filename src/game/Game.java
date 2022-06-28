package game;

import java.util.Random;

abstract class Game {
    final static int CPU = 0;
    final static int PLAYER = 1;
    final static int DRAW = 2;
    private final static String[] GAME_TYPE = {"ROCK/PAPER/SCISSORS", "ROCK/PAPER/SCISSORS & LIZARD SPOCK"};
    private final static String[] BETS_POOL = {"PEDRA", "PAPER", "TISSORES", "SPOCK", "LLANGARDAIX"};

    /*
    Crea una partida pel jugador i jugades passats per parametre
     */
    GameData createGameData(String name, int rounds, String gameType) {
        GameData game = new GameData();
        game.initialize(name, rounds, gameType);
        return game;
    }

    /*
    Juga una partida tants cops com jugades demanem
    */
    void playGame(GameData game) {
        for (int i = 0; i < game.getTorns(); i++) {
            int[] apostes = getRoundBets();
            int guanya = getWinningBet(apostes[CPU], apostes[PLAYER]);
            if (guanya == PLAYER) {
                game.increaseUserWins();
            } else if (guanya == CPU) {
                game.increaseCPUWins();
            }
            if (guanya == 2) {
                System.out.println("Aquest torn hi ha hagut EMPAT");
            } else {
                System.out.println("Aquest torn guanya: " + game.getJugadors(guanya));
            }
        }
    }

    /**
     * @param cpuBet aposta autogenerada per CPU
     * @param playerBet aposta jugador
     * @return guanyador del joc (funciona per pedra/paper/tissores i per l'extensio
     * pedra/paper/tissores/llangardaix/spock
     */
    int getWinningBet(int cpuBet, int playerBet) {
        int winner = CPU;
        int resultat = Math.abs(cpuBet - playerBet) % 2;
        if (resultat == 0) {
            if (cpuBet == playerBet) {
                winner = DRAW;
            } else if (cpuBet >= playerBet) {
                winner = PLAYER;
            }
        } else if (cpuBet <= playerBet) {
            winner = PLAYER;
        }
        return winner;
    }

    /**
     * @return if called from RockPaperScissors class returns int array with 2
     * numbers between 0-2
     * if called from RockPaperScissorsSpockLizard returs int array with 2
     * numbers between 0-4
     */
    int[] getRoundBets() {
        int[] apostes = new int[2];
        Random apostaM = new Random();
        System.out.println(getShowRPSOptions());
        apostes[PLAYER] = UtilsES.getPlayerBet(getOpcionsAposta());
        apostes[CPU] = apostaM.nextInt(getOpcionsAposta() + 1);
        System.out.println("La maquina ha triat: " + BETS_POOL[apostes[CPU]]);
        return apostes;
    }

    String getGameType(int joc) {
        return GAME_TYPE[joc];
    }

    abstract int getOpcionsAposta();

    abstract String getShowRPSOptions();


}
