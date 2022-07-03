package game;

import java.util.Scanner;

final class UtilsES {
    private UtilsES() {
        //final class with private constructor to avoid inheritance and make it non instantiable
    }

    static int getRounds() {
        return getInteger("Quantes jugades vols? (%d-%d)%n", "valors valids entre %d i %d%n", 3, 5);
    }

    static int getPlayerBet(int disponibles) {
        return getInteger("", "Valor invalid%n", 0, disponibles);
    }

    /**
     * @param pregunta text a mostrar a l'usuari al demanar entrada
     * @param error    text a mostrar si s'entra un valor en rang erroni
     * @param min      valor minim que es permet entrar
     * @param max      valor maxim que es permet entrar
     * @return valor enter entre min i max
     */
    static int getInteger(String pregunta, String error, int min, int max) {
        Scanner input = new Scanner(System.in);
        boolean enRang;
        int entrada = 0;
        do {
            System.out.printf(pregunta, min, max);
            enRang = input.hasNextInt();
            if (enRang) {
                entrada = input.nextInt();
                if (entrada < min || entrada > max) {
                    enRang = false;
                }
            }
            if (!enRang) {
                System.out.printf(error, min, max);
            }
            input.nextLine();
        } while (!enRang);
        return entrada;
    }


    static String getName(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    static void updateScore(int guanyador, int posicio, String[][] dadesJugadors) {
        if (guanyador == Game.PLAYER) {
            int puntuacio = Integer.parseInt(dadesJugadors[posicio][1]);
            puntuacio++;
            dadesJugadors[posicio][1] = Integer.toString(puntuacio);
        }
    }

    static void showScore(int posicio, String[][] dadesJugadors) {
        System.out.println("posicio jugador: " + posicio + " puntuacio actualitzada: " + dadesJugadors[posicio][1]);
    }


    static void showTitle(String titolPantalla) {
        System.out.print("""
                                 =============================================================================================================================
                                                                                """ + titolPantalla + """                             
                                 \n=============================================================================================================================
                                 """);
    }


    static void separadorLinies() {
        System.out.print("-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    static void showErrorMessage(String missatgeError) {
        System.out.print("""        
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                          \t""" + missatgeError + """                             
                                 \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 """);

    }

    static void showGameWinner(GameData partida) {
        System.out.println(partida);
    }

    //input slips into next round causing exception when scanner reads values
    static void nextGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Press [ENTER] to continue...");
        input.nextLine();
    }

    static String[][] initializeEmptyArray(int rows, int cols) {
        String[][] dadesJugadors = new String[rows][cols];
        for (int i = 0; i < dadesJugadors.length; i++) {
            for (int j = 0; j < 2; j++) {
                dadesJugadors[i][j] = "";
            }
        }
        return dadesJugadors;
    }

}
