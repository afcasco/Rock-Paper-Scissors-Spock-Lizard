package game;

import java.util.Random;

abstract class Game {
    final static int CPU = 0;
    final static int PLAYER = 1;
    final static int DRAW = 2;
    final static String[] OUTCOMES = {"CPU", "Player", "Draw"};
    private final static String[] GAME_TYPE = {"ROCK/PAPER/SCISSORS", "ROCK/PAPER/SCISSORS & LIZARD SPOCK"};
    private final static String[] BETS_POOL = {"PEDRA", "PAPER", "TISSORES", "SPOCK", "LLANGARDAIX"};
    private static final String RPS_RULES = """
             *ROCK PAPER SCISSORS RULES:
                - Rock wins against scissors.
                - Scissors win against paper.
                - Paper wins against rock.
            """;
    private static final String RPSLS_RULES = """
             * ROCK PAPER SCISSORS LIZARD SPOCK RULES
                - Scissors cuts paper.
                - Paper covers rock.
                - Rock crushes lizard.
                - Lizard poisons Spock.
                - Spock smashes scissors.
                - Scissors decapitates lizard.
                - Lizard eats paper.
                - Paper disproves Spock.
                - Spock vaporizes rock.
                - Rock crushes scissors.
            """;

    /**
     * Show rock paper scissors and rock paper scissors lizard spock rules
     */
    static void viewGameRules() {
        int option = UtilsES.getInteger("""
                **********************************************************************
                | 0. ROCK PAPER SCISSORS \t|\t1. ROCK PAPER SCISSORS LIZARD SPOCK |
                **********************************************************************
                """, "Wrong option, try again!", 0, 1);
        switch (option) {
            case 0 -> System.out.println(RPS_RULES);
            case 1 -> System.out.println(RPSLS_RULES);
        }
        UtilsES.nextGame();
    }

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
     * @param cpuBet    aposta autogenerada per CPU
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
