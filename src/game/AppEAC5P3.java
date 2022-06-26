package game;

public class AppEAC5P3 {

    private static final String PUNTUACIO_INICIAL = "0";
    private static final String GAME_TITLE = "ROCK/PAPER/SCISSORS & LIZARD SPOCK!";


    public static void main(String[] args) {
        AppEAC5P3 programa = new AppEAC5P3();
        programa.inici();
    }

    void inici() {
        /*
        Names and scores array to be removed later, just used for testing purposes
         */
        String[][] dadesJugadors = {{"Adria", "5"}, {"Agnes", "0"}, {"Anna", "3"}, {"Arnau", "2"}, {"Beth", "9"},
                {"Blanca", "6"}, {"Bruna", "1"}, {"Carla", "7"}, {"Cesc", "0"}, {"Clara", "5"}, {"Duna", "2"},
                {"Laia", "4"}, {"Eloi", "3"}, {"Emma", "6"}, {"Gerard", "8"}, {"Guillem", "5"}, {"Lluc", "7"},
                {"Jordi", "1"}, {"Martí", "5"}, {"Max", "3"}, {"Neus", "6"}, {"Nico", "2"}, {"Nina", "1"},
                {"Noa", "6"}, {"Nora", "1"}, {"Nuria", "8"}, {"Oriol", "6"}, {"Pau", "1"}, {"Paula", "9"},
                {"Pep", "0"}, {"Pol", "10"}, {"Queralt", "3"}, {"Quim", "1"}, {"Rita", "7"}, {"Roc", "8"},
                {"Roger", "9"}, {"Sergi", "3"}, {"Txell", "1"}, {"Xavi", "9"}, {"Alex", "4"}, {"Èlia", "0"},
                {"Èric", "6"}, {"", ""}, {"", ""}};

        while (usuariVolJugar()) {
            UtilsES.mostrarTitol("      GAME CONFIGURATION");
            String nom = UtilsES.demanarNom();
            int posicio = esJugadorValid(nom, dadesJugadors);
            if (posicio != -1) {
                Game partida;
                int tornsPartida = UtilsES.demanarQuantesJugades();
                int joc = escollirJoc();
                UtilsES.mostrarTitol("               LET'S GO!");
                partida = (joc == 0) ? new RockPaperScissors() : new RockPaperScissorsSpockLizard();
                DadesPartida partidaActual = partida.crearDadesPartida(nom, tornsPartida, Game.GAME_TYPE[joc]);
                partida.jugarPartida(partidaActual);
                System.out.println(partidaActual);
                UtilsES.actualitzarPuntuacio(partidaActual.getWinner(), posicio, dadesJugadors);
                UtilsES.mostrarPuntuacio(posicio, dadesJugadors);
            }
        }
    }

    int cercaPosJugador(String nom, String[][] dadesJugadors) {
        boolean trobat = false;
        int i = 0;
        while (!trobat && i < dadesJugadors.length) {
            if (dadesJugadors[i][0].trim().equalsIgnoreCase(nom)) {
                trobat = true;
            } else {
                i++;
            }
        }
        return trobat ? i : -1;
    }

    int enregistrarNouJugador(String nom, String[][] dadesJugadors) {
        boolean espaiBuit = false;
        int i = 0;
        while (!espaiBuit && i < dadesJugadors.length) {
            if (dadesJugadors[i][0].trim().equalsIgnoreCase("")) {
                espaiBuit = true;
                dadesJugadors[i][0] = nom;
                dadesJugadors[i][1] = PUNTUACIO_INICIAL; // Canviar a constant
            } else {
                i++;
            }
        }
        return espaiBuit ? i : -1;
    }

    /*
    Retorna true quan l'usuari entra 1, i fals quan l'usuari entra 0
    Qualsevol altre valor introduit mostra error i torna a començar
     */
    boolean usuariVolJugar() {
        UtilsES.mostrarTitol(GAME_TITLE); 
        int jugar = UtilsES.demanarEnter("1. PLAY%n0. EXIT%n", "Escull una opcio valida. (%d o %d)%n", 0, 1);
        return jugar == 1;
    }

    int escollirJoc() {
        return UtilsES.demanarEnter("""
                0. ROCK PAPER SCISSORS
                1. ROCK PAPER SCISSORS LIZARD SPOCK
                """, "Wrong option, try again!", 0, 1);
    }

    int esJugadorValid(String nom, String[][] dadesJugadors) {
        int posicio = cercaPosJugador(nom, dadesJugadors);
        if (posicio == -1) {
            posicio = enregistrarNouJugador(nom, dadesJugadors);
        }
        if (posicio == -1) {
            System.out.println("No queda espai per registrar mes jugadors," +
                               " pero encara poden jugar els jugadors existents");
        }
        return posicio;
    }
}
