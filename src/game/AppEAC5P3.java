package game;

public class AppEAC5P3 {

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

        FileUtils.inicialitza();
        /*
        Names and scores array to be removed later, just used for testing purposes
         */
        String[][] dadesJugadors = {
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""},
                {"", ""}, {"", ""}, {"", ""}, {"", ""}

        };

        //Load players from file if it exists otherwise return empty array
        FileUtils.loadPlayers(dadesJugadors);
        int options;

        do {
            options = getUserOption();
            switch (options) {
                case 1 -> playTheGame(dadesJugadors);
                case 2 -> listGameFiles();
                case 3 -> listPlayerGames();
            }
        } while (options != 0);
    }


    void playTheGame(String[][] dadesJugadors) {
        UtilsES.showTitle("GAME CONFIGURATION");
        String nom = UtilsES.getName("What's your name? ");
        int posicio = isValidPlayer(nom, dadesJugadors);
        if (posicio != -1) {
            Game partida;
            int tornsPartida = UtilsES.getRounds();
            UtilsES.separadorLinies();
            int joc = chooseGame();
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

    void listGameFiles() {
        String[][] games = FileUtils.getGameFiles();
        if (games != null) {
            UtilsES.showTitle("SAVED PLAYER FILES");
            for (String[] game : games) {
                System.out.println("Name: " + game[0] + "\t" + "Size: " + game[1]);
            }
        } else {
            System.out.println("NO GAME FILES FOUND");
        }
        UtilsES.nextGame();
    }

    void listPlayerGames() {
        String playerName = UtilsES.getName("Enter a player name to show his/her game history...");
        int[][] playerHistory = FileUtils.getPlayerHistory(playerName);
        if (playerHistory != null) {
            for (int i = 0; i < playerHistory.length; i++) {
                System.out.println(i + "\t" + playerHistory[i][0] + "\t" + playerHistory[i][1]);
            }
        } else {
            System.out.println("Player file not found ");
        }
        UtilsES.nextGame();
    }

    int findPlayerPosition(String nom, String[][] dadesJugadors) {
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

    int recordNewPlayer(String nom, String[][] dadesJugadors) {
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
    int getUserOption() {
        UtilsES.showTitle(GAME_TITLE);
        return UtilsES.getInteger("1. PLAY%n2. Llista de fitxers de partides%n" +
                                  "3. Partides d'un jugador%n0. EXIT%n", "Escull una opcio valida. (%d o %d)%n", 0, 3);
    }

    int chooseGame() {
        return UtilsES.getInteger("""
                **********************************************************************
                | 0. ROCK PAPER SCISSORS \t|\t1. ROCK PAPER SCISSORS LIZARD SPOCK |
                **********************************************************************
                """, "Wrong option, try again!", 0, 1);
    }

    int isValidPlayer(String nom, String[][] dadesJugadors) {
        int posicio = findPlayerPosition(nom, dadesJugadors);
        if (posicio == -1) {
            posicio = recordNewPlayer(nom, dadesJugadors);
        }
        if (posicio == -1) {
            UtilsES.showErrorMessage(NO_MORE_SPACE_ERROR);
        }
        return posicio;
    }
}
