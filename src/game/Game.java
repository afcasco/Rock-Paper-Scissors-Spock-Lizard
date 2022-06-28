package game;

import java.util.Random;

abstract class Game {
    final static int MAQUINA = 0;
    final static int USUARI = 1;
    final static int EMPAT = 2;
    private final static String[] GAME_TYPE = {"ROCK/PAPER/SCISSORS", "ROCK/PAPER/SCISSORS & LIZARD SPOCK"};
    private final static String[] APOSTES = {"PEDRA", "PAPER", "TISSORES", "SPOCK", "LLANGARDAIX"};

    /*
    Crea una partida pel jugador i jugades passats per parametre
     */
    DadesPartida crearDadesPartida(String nom, int jugades, String gameType) {
        DadesPartida partida = new DadesPartida();
        partida.inicialitzar(nom, jugades, gameType);
        return partida;
    }

    /*
    Juga una partida tants cops com jugades demanem
    */
    void jugarPartida(DadesPartida partida) {
        for (int i = 0; i < partida.getTorns(); i++) {
            int[] apostes = generarApostes();
            int guanya = quinaApostaGuanya(apostes[MAQUINA], apostes[USUARI]);
            if (guanya == USUARI) {
                partida.increaseUserWins();
            } else if (guanya == MAQUINA) {
                partida.increaseCPUWins();
            }
            if (guanya == 2) {
                System.out.println("Aquest torn hi ha hagut EMPAT");
            } else {
                System.out.println("Aquest torn guanya: " + partida.getJugadors(guanya));
            }
        }
    }

    /**
     * @param apostaM aposta autogenerada per CPU
     * @param apostaU aposta jugador
     * @return guanyador del joc (funciona per pedra/paper/tissores i per l'extensio
     * pedra/paper/tissores/llangardaix/spock
     */
    int quinaApostaGuanya(int apostaM, int apostaU) {
        int result = MAQUINA;
        int resultat = Math.abs(apostaM - apostaU) % 2;
        if (resultat == 0) {
            if (apostaM == apostaU) {
                result = EMPAT;
            } else if (apostaM >= apostaU) {
                result = USUARI;
            }
        } else if (apostaM <= apostaU) {
            result = USUARI;
        }
        return result;
    }

    /**
     * @return if called from RockPaperScissors class returns int array with 2
     * numbers between 0-2
     * if called from RockPaperScissorsSpockLizard returs int array with 2
     * numbers between 0-4
     */
    int[] generarApostes() {
        int[] apostes = new int[2];
        Random apostaM = new Random();
        System.out.println(getShowRPSOptions());
        apostes[USUARI] = UtilsES.demanarAposta(getOpcionsAposta());
        apostes[MAQUINA] = apostaM.nextInt(getOpcionsAposta() + 1);
        System.out.println("La maquina ha triat: " + APOSTES[apostes[MAQUINA]]);
        return apostes;
    }

    String getGameType(int joc) {
        return GAME_TYPE[joc];
    }

    abstract int getOpcionsAposta();

    abstract String getShowRPSOptions();


}
