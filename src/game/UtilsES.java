package game;

import java.util.Scanner;

final class UtilsES {
    private UtilsES() {
        // Constructor privat perque no es pugui instanciar la classe
    }

    static int demanarQuantesJugades() {
        return demanarEnter("Quantes jugades vols? (%d-%d)%n", "valors valids entre %d i %d%n", 3, 5);
    }

    static int demanarAposta(int disponibles) {
        return demanarEnter("", "Valor invalid%n", 0, disponibles);
    }

    /**
     * @param pregunta text a mostrar a l'usuari al demanar entrada
     * @param error    text a mostrar si s'entra un valor en rang erroni
     * @param min      valor minim que es permet entrar
     * @param max      valor maxim que es permet entrar
     * @return valor enter entre min i max
     */
    static int demanarEnter(String pregunta, String error, int min, int max) {
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

    static String demanarNom() {
        System.out.println("What's your name? ");
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    static void actualitzarPuntuacio(int guanyador, int posicio, String[][] dadesJugadors) {
        if (guanyador == Game.USUARI) {
            int puntuacio = Integer.parseInt(dadesJugadors[posicio][1]);
            puntuacio++;
            dadesJugadors[posicio][1] = Integer.toString(puntuacio);
        }
    }

    static void mostrarPuntuacio(int posicio, String[][] dadesJugadors) {
        System.out.println("posicio jugador: " + posicio + " puntuacio actualitzada: " + dadesJugadors[posicio][1]);
    }


    static void mostrarTitol(String titolPantalla) {
        System.out.print("""
                                 =============================================================================================================================
                                          \t""" + titolPantalla + """                             
                                 \n=============================================================================================================================
                                 """);
    }


    static void separadorLinies() {
        System.out.print("-------------------------------------------------------");
    }

    static void mostrarError(String missatgeError) {
        System.out.print("""        
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                          \t""" + missatgeError + """                             
                                 \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 """);

    }

    static void mostrarGuanyadorPartida(DadesPartida partida) {
        System.out.println(partida);
    }

    //input slips into next round causing exception when scanner reads values
    static void nextGame() {
        System.out.println("Press [ENTER] to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
