/*  
 *  Programación y diseño orientado a objetos
 *  Grado en Ingeniería Informática
 * 
 *  2013 © Copyleft - All Wrongs Reserved
 *
 *  Ernesto Serrano <erseco@correo.ugr.es>
 *  Noureddine El Alaoui <nourdine@correo.ugr.es>
 * 
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Random;

public class Napakalaki {

    //Atributos
    private static Napakalaki instance = null;
    private Monster currentMonster;
    private CardDealer dealer = CardDealer.getInstance();
    private Player currentPlayer;
    private ArrayList<Player> players;

    //Constructor
    private Napakalaki() {
    }

    //Patrón Singleton
    public static Napakalaki getInstance() {
        if (instance == null) {
            instance = new Napakalaki();
        }
        return instance;
    }

    //Métodos
    //Inicializa el array de jugadores que contiene Napakalaki, creando tantos 
    //jugadores como elementos haya en names, que es el array de String que 
    //contiene el nombre de los jugadores.
    private void initPlayers(ArrayList<String> names) {

        //Inicializamos el array de jugadores
        players = new ArrayList();

        //Recorremos los nombres pasados y creamos tantos jugadores como nombres
        for (String s : names) {

            players.add(new Player(s));

        }


    }

    // Decide qué jugador es el siguiente en jugar. Se pueden dar dos posibilidades 
    // para calcular el índice que ocupa dicho jugador en la lista de jugadores:
    // 
    // 1) Que sea el primero en jugar, en este caso hay que generar un número alea-
    // torio entre 0 y el numero de jugadores menos 1, este número indicará el índi-
    // ce que ocupa en la lista de jugadores el jugador que comenzará la partida.
    // 
    // 2) Que no sea el primero en jugar, en este caso el índice es el del jugador 
    // que se encuentra en la siguiente posición al jugador actual. Hay que tener en
    // cuenta que si el jugador actual está en la última posición, el siguiente será
    // el que está en la primera posición.
    // 
    //Una vez calculado el índice, el jugador que ocupa esa posición se almacenará 
    //en el atributo currentPlayer de la clase Napakalaki.
    private Player nextPlayer() {

        int nextIndex;
        Player nextPlayer;

        //Obtenemos el numero total de jugadores
        int totalPlayers = this.players.size();

        //Si no está definido el jugador actual es porque es la primera vez
        if (this.currentPlayer == null) {

            //Creamos un objeto Random
            Random rnd = new Random();

            //Obtenemos un numero aleatorio con tope el índice maximo del 
            //numero de jugadores
            nextIndex = rnd.nextInt(totalPlayers);

        } else {

            //Obtenemos el índice del jugador actual
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);

            if (currentPlayerIndex == totalPlayers - 1) {
                //Si es el último seleccionamos el primero
                nextIndex = 0;

            } else {
                //En caso contrario seleccionamos el siguiente
                nextIndex = currentPlayerIndex + 1;
            }

        }

        //Obtenemos el jugador correspondiente al índice aleatorio
        nextPlayer = this.players.get(nextIndex);

        //Establecemos el siguiente jugador
        this.currentPlayer = nextPlayer;

        return this.currentPlayer;
    }

    public CombatResult developCombat() {

        CombatResult combat = this.currentPlayer.combat(this.currentMonster);

        //Comprobamos si el resultado es LoseAndConvert
        if (combat == CombatResult.LoseAndConvert) {

            //Obtenemos una carta de cultista
            Cultist cl = this.dealer.nextCultist();

            //Creamos un nuevo cultista
            CultistPlayer clPlayer = new CultistPlayer(this.currentPlayer, cl);

            //Obrenemos el indice del currentplayer por sustituirlo por el cultista
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);

            //Sustituimos el cultista
            this.players.set(currentPlayerIndex, clPlayer);

            this.currentPlayer = clPlayer;
        }


        this.dealer.giveMonsterBack(this.currentMonster);

        return combat;

    }

    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {

        for (Treasure t : treasures) { //1.1

            this.currentPlayer.discardVisibleTreasure(t); //1.2
            this.dealer.giveTreasureBack(t); //1.3

        }

    }

    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for (Treasure t : treasures) { //1.1

            this.currentPlayer.discardHiddenTreasure(t); //1.2
            this.dealer.giveTreasureBack(t); //1.3

        }

    }

    public void makeTreasuresVisible(ArrayList<Treasure> treasures) {

        for (Treasure t : treasures) { //1.1

            this.currentPlayer.makeTreasureVisible(t); //1.2

        }

    }

    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {


        return this.currentPlayer.buyLevels(visible, hidden);


    }

    //Se encarga de solicitar a CardDealer la inicialización de los mazos de 
    //cartas de Tesoros y de Monstruos, de inicializar los jugadores proporcionándoles 
    //un nombre y de iniciar el juego con la llamada a nextTurn() para pasar al 
    //siguiente turno (que en este caso será el primero).
    public void initGame(ArrayList<String> names) {

        this.initPlayers(names); //1.1

        dealer.initCards(); //1.2

        this.nextTurn(); //1.3


    }

    //Devuelve el jugador actual (currentPlayer)
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    //Devuelve el monstruo en juego (currentMonster)
    public Monster getCurrentMonster() {
        return this.currentMonster;
    }

    //Esta función igual no es necesaria
    public boolean canMakeTreasureVisible(Treasure t) {

        return this.currentPlayer.canMakeTreasureVisible(t);
    }

    public boolean nextTurn() {

        boolean stateOK = this.nextTurnAllowed(); //1.1

        if (stateOK) {

            this.currentMonster = this.dealer.nextMonster(); //1.2

            this.currentPlayer = this.nextPlayer(); //1.3

            boolean dead = this.currentPlayer.isDead(); //1.4

            if (dead) {

                this.currentPlayer.initTreasures(); //1.5

            }

        } else {

            //NOTA: Agregado por mi, ya que si no, al no permitir cambiar de turno, el monstruo no cambia
            this.currentMonster = this.dealer.nextMonster(); //1.2

        }

        return stateOK; //1.6

    }

    //Método que comprueba si el jugador activo (currentPlayer) cumple con las 
    //reglas del juego para poder terminar su turno. Devuelve false si el juga-
    //dor activo no puede pasar de turno y true en caso contrario, para ello 
    //usa el método de Player: validState()
    public boolean nextTurnAllowed() {


        boolean allowed;

        if (this.currentPlayer == null) {
            allowed = true; //La primera vez currentPlayer está sin asignar
        } else {
            allowed = this.currentPlayer.validState(); //1.1.1
        }

        return allowed;

    }

    //Devuelve true si result tiene el valor WinAndWinGame del enumerado 
    //CombatResult, en caso contrario devuelve false.
    public boolean endOfGame(CombatResult result) {

        return result == CombatResult.WinAndWinGame;

    }
}
