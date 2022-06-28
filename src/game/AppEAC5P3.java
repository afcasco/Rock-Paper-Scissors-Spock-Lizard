package game;

public class AppEAC5P3 {

    // FileUtilsBranch
    // Testing file IO
    private static final String PUNTUACIO_INICIAL = "0";
    private static final String NO_MORE_SPACE_ERROR = """
                         NO QUEDA ESPAI PER REGISTRAR MES JUGADORS
                                   PERO ELS JUGADORS EXISTENTS ENCARA PODEN JUGAR
            """;
    private static final String GAME_TITLE = """
 ____   ___   ____ _  __   ____   _    ____  _____ ____     ____   ____ ___ ____ ____   ___  ____  ____ \s
            |  _ \\ / _ \\ / ___| |/ /  |  _ \\ / \\  |  _ \\| ____|  _ \\   / ___| / ___|_ _/ ___/ ___| / _ \\|  _ \\/ ___|\s
            | |_) | | | | |   | ' /   | |_) / _ \\ | |_) |  _| | |_) |  \\___ \\| |    | |\\___ \\___ \\| | | | |_) \\___ \\\s
            |  _ <| |_| | |___| . \\   |  __/ ___ \\|  __/| |___|  _ <    ___) | |___ | | ___) |__) | |_| |  _ < ___) |
            |_| \\_\\\\___/ \\____|_|\\_\\  |_| /_/   \\_\\_|   |_____|_| \\_\\  |____/ \\____|___|____/____/ \\___/|_| \\_\\____/\s
             _     ___ _____   _    ____  ____     ____  ____   ___   ____ _  __     __                             \s
            | |   |_ _|__  /  / \\  |  _ \\|  _ \\   / ___||  _ \\ / _ \\ / ___| |/ /  _  \\ \\                            \s
            | |    | |  / /  / _ \\ | |_) | | | |  \\___ \\| |_) | | | | |   | ' /  (_)  | |                           \s
            | |___ | | / /_ / ___ \\|  _ <| |_| |   ___) |  __/| |_| | |___| . \\   _   | |                           \s
            |_____|___/____/_/   \\_\\_| \\_\\____/   |____/|_|    \\___/ \\____|_|\\_\\ (_)  | |                           \s
                                                                                     /_/   \s""";

    public static void main(String[] args) {
        AppEAC5P3 app = new AppEAC5P3();
        app.start();
    }

    void start() {

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

        while (userWantsToPlay()) {
            UtilsES.showTitle("GAME CONFIGURATION");
            String nom = UtilsES.getName();
            int posicio = esJugadorValid(nom, dadesJugadors);
            if (posicio != -1) {
                Game partida;
                int tornsPartida = UtilsES.getRounds();
                UtilsES.separadorLinies();
                int joc = escollirJoc();
                UtilsES.showTitle("LET'S GO!");
                partida = (joc == 0) ? new RockPaperScissors() : new RockPaperScissorsSpockLizard();
                GameData partidaActual = partida.createGameData(nom, tornsPartida, partida.getGameType(joc));
                partida.playGame(partidaActual);
                UtilsES.showGameWinner(partidaActual);
                UtilsES.updateScore(partidaActual.getWinner(), posicio, dadesJugadors);
                UtilsES.showScore(posicio, dadesJugadors);
                UtilsES.nextGame();
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
                dadesJugadors[i][1] = PUNTUACIO_INICIAL;
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
    boolean userWantsToPlay() {
        UtilsES.showTitle(GAME_TITLE);
        int jugar = UtilsES.getInteger("1. PLAY%n0. EXIT%n", "Escull una opcio valida. (%d o %d)%n", 0, 1);
        return jugar == 1;
    }

    int escollirJoc() {
        return UtilsES.getInteger("""
                || 0. ROCK PAPER SCISSORS\t||\t1. ROCK PAPER SCISSORS LIZARD SPOCK ||
                """, "Wrong option, try again!", 0, 1);
    }

    int esJugadorValid(String nom, String[][] dadesJugadors) {
        int posicio = cercaPosJugador(nom, dadesJugadors);
        if (posicio == -1) {
            posicio = enregistrarNouJugador(nom, dadesJugadors);
        }
        if (posicio == -1) {
            UtilsES.showErrorMessage(NO_MORE_SPACE_ERROR);
        }
        return posicio;
    }
}
