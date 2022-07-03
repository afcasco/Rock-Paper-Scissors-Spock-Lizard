package game;

/**
 * Play the classic game Rock Paper Scissors against the CPU, as well
 * as the extended version Rock Paper Scissors Lizard Spock
 */
public class AppEAC5P3 {

    private static final String PLAYER_OPTIONS = "1. PLAY%n2. Llista de fitxers de partides%" +
                                                 "n3. Partides d'un jugador%n4. View Rules%n0. EXIT%n";
    private static final String PUNTUACIO_INICIAL = "0";
    private static final int MAX_PLAYERS = 40;
    private static final int DATA_FIELDS = 2;
    private static final String NO_MORE_SPACE_ERROR = """
                         NO QUEDA ESPAI PER REGISTRAR MES JUGADORS
                                   PERO ELS JUGADORS EXISTENTS ENCARA PODEN JUGAR
            """;
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

        // Checks for (or creates) data directory structure
        FileUtils.inicialitza();
        // Loads players from file
        String[][] dadesJugadors = FileUtils.loadPlayers();
        // if no file is found, generates a working 40*2 array to store data
        if (dadesJugadors == null) dadesJugadors = UtilsES.initializeEmptyArray(MAX_PLAYERS, DATA_FIELDS);
        GameData partidaActual = null;
        int options;
        do {
            options = getUserMenuOption();
            switch (options) {
                case 1 -> partidaActual = playTheGame(dadesJugadors);
                case 2 -> listGameFiles();
                case 3 -> listPlayerGames();
                case 4 -> viewGameRules();
            }
        } while (options != 0);
        if (partidaActual != null) {
            // Previously part of playTheGame method, moved here to update file only on exit
            FileUtils.savePlayers(dadesJugadors);
            FileUtils.guardarPartidaEnHistoric(partidaActual.getPlayerName(Game.PLAYER), partidaActual.getTorns(), partidaActual.getWinner());
        }


    }

    private void viewGameRules() {
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

    GameData playTheGame(String[][] dadesJugadors) {
        UtilsES.showTitle("GAME CONFIGURATION");
        String nom = UtilsES.getName("What's your name? ");
        GameData partidaActual = null;
        int posicio = isValidPlayer(nom, dadesJugadors);
        if (posicio != -1) {
            Game partida;
            int tornsPartida = UtilsES.getRounds();
            UtilsES.separadorLinies();
            int joc = chooseGameMenu();
            UtilsES.showTitle("LET'S GO!");
            partida = (joc == 0) ? new RockPaperScissors() : new RockPaperScissorsSpockLizard();
            partidaActual = partida.createGameData(nom, tornsPartida, partida.getGameType(joc));
            partida.playGame(partidaActual);
            UtilsES.showGameWinner(partidaActual);
            UtilsES.nextGame();
        }
        return partidaActual;
    }

    void listGameFiles() {
        String[][] games = FileUtils.getGameFiles();
        if (games != null) {
            UtilsES.showTitle("SAVED PLAYER FILES");
            for (String[] game : games) {
                System.out.println("Name: " + game[0] + "\t" + "Size: " + game[1]);
            }
        } else {
            System.out.println("NO GAME FILE FOUND");
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
            System.out.println("Game file not found for player " + playerName.toUpperCase());
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
    int getUserMenuOption() {
        UtilsES.showTitle(GAME_TITLE);
        return UtilsES.getInteger(PLAYER_OPTIONS, "Escull una opcio valida. (%d o %d)%n", 0, 4);
    }

    int chooseGameMenu() {
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
