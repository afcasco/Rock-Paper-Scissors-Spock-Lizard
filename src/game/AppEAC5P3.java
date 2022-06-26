package game;

public class AppEAC5P3 {

    private static final String PUNTUACIO_INICIAL = "0";
    public static void main(String[] args) {
        AppEAC5P3 programa = new AppEAC5P3();
        programa.inici();
    }

    void inici() {
        String[][] dadesJugadors = {{"Adria", "5"}, {"Agnes", "0"}, {"Anna", "3"}, {"Arnau", "2"}, {"Beth", "9"},
                {"Blanca", "6"}, {"Bruna", "1"}, {"Carla", "7"}, {"Cesc", "0"}, {"Clara", "5"}, {"Duna", "2"},
                {"Laia", "4"}, {"Eloi", "3"}, {"Emma", "6"}, {"Gerard", "8"}, {"Guillem", "5"}, {"Lluc", "7"},
                {"Jordi", "1"}, {"Martí", "5"}, {"Max", "3"}, {"Neus", "6"}, {"Nico", "2"}, {"Nina", "1"},
                {"Noa", "6"}, {"Nora", "1"}, {"Nuria", "8"}, {"Oriol", "6"}, {"Pau", "1"}, {"Paula", "9"},
                {"Pep", "0"}, {"Pol", "10"}, {"Queralt", "3"}, {"Quim", "1"}, {"Rita", "7"}, {"Roc", "8"},
                {"Roger", "9"}, {"Sergi", "3"}, {"Txell", "1"}, {"Xavi", "9"}, {"Alex", "4"}, {"Èlia", "0"},
                {"Èric", "6"}, {"", ""}, {"", ""}};

        while (usuariVolJugar()) {
            String nom = UtilsES.demanarNom();
            int posicio = esJugadorValid(nom, dadesJugadors);
            if (posicio != -1) {
                int tornsPartida = UtilsES.demanarQuantesJugades();
                Game partida;
                int joc = escollirJoc();
                partida = (joc == 0) ? new RockPaperScissors() : new RockPaperScissorsSpockLizard();
                DadesPartida partidaActual = partida.crearDadesPartida(nom, tornsPartida, Game.GAME_TYPE[joc]);
                partida.jugarPartida(partidaActual);
                System.out.println(partidaActual);
                System.out.println("posicio jugador: " + posicio);
                System.out.println("Aquest joc era de tipus: "+partidaActual.gameType);
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
        int jugar = UtilsES.demanarEnter("1. Jugar%n0. Sortir%n", "Escull una opcio valida. (%d o %d)%n", 0, 1);
        return jugar == 1;
    }

    int escollirJoc() {
        return UtilsES.demanarEnter("""
                0. Pedra/Paper/Tissores
                1. Pedra/Paper/Tissores/Spock/Llangardaix
                """, "Opcio incorrecte", 0, 1);
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
