/*  
 *  Programación y diseño orientado a objetos
 *  Grado en Ingeniería Informática
 * 
 *  2013 © Copyleft - All Wrongs Reserved
 *
 *  Ernesto Serrano <erseco@correo.ugr.es>
 * 
 */
package napakalaki;

import java.util.ArrayList;

public class Player {

    //Atributos
    private boolean dead = true;
    private String name;
    private int level;
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList();
    private BadStuff pendingBadStuff;

    //Constructor
    public Player(String name) {
        this.name = name;
        this.level = 1; //El nivel al principio siempre está a 1

    }

    public Player(Player p) {
        this.name = p.name;
        this.level = p.level;
        this.pendingBadStuff = p.pendingBadStuff;
        this.dead = p.dead;
        this.hiddenTreasures = p.hiddenTreasures;
        this.visibleTreasures = p.visibleTreasures;

    }

    //Métodos
    public boolean isDead() {
        return this.dead;
    }

    //Devuelve el nivel de combate del jugador, que viene dado por su nivel más 
    //los bonus que le proporcionan los tesoros que tenga equipados, según las 
    //reglas del juego.
    public int getCombatLevel() {

        int lvl = this.level;

        //Primero comprobamos si tenemos el collar
        boolean hasNeckLace = false;
        for (Treasure t : this.visibleTreasures) {
            if (t.getType() == TreasureKind.necklace) {
                hasNeckLace = true;
                break;
            }
        }

        //Ahora recorremos los tesoros para obtener el bonus
        //NOTA: podria hacerse un solo if y dos for, en lugar de hacer el
        //if dentro del for, para optimizar
        for (Treasure t : this.visibleTreasures) {

            if (hasNeckLace) {
                lvl += t.getMaxBonus(); //Si tiene el necklace el maximo
            } else {
                lvl += t.getMinBonus(); //En caso contrario el minimo
            }
        }

        return lvl;

    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return this.hiddenTreasures;
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }

    //Devuelve la vida al jugador, modificando el atributo correspondiente.
    private void bringToLife() {
        this.dead = false;
    }

    //Incrementa el nivel del jugador en i niveles
    private void incrementLevels(int l) {
        this.level += l;
    }

    //Decrementa el nivel del jugador en i niveles
    private void decrementLevels(int l) {
        this.level -= l;

        //El nivel mínimo siempre será 1;
        if (this.level < 1) {
            this.level = 1;
        }

    }

    //Asigna el mal rollo al jugador, dándole valor a su atributo pendingBadStuff.
    private void setPendingBadStuff(BadStuff b) {
        this.pendingBadStuff = b;

    }

    private void die() {

        //ponemos el nivel a 1
        this.level = 1; //1

        CardDealer dealer = CardDealer.getInstance(); //2

        for (Treasure t : this.visibleTreasures) {

            dealer.giveTreasureBack(t); //4

        }

        this.visibleTreasures.clear(); //5

        for (Treasure t : this.hiddenTreasures) {

            dealer.giveTreasureBack(t); //7

        }

        this.hiddenTreasures.clear(); //8


        //Si no tiene tesoros muere
        this.dieIfNoTreasures(); //9

    }

    //Si el jugador tiene equipado el tesoro tipo NECKLACE, se lo pasa al 
    //CardDealer y lo elimina de sus tesoros visibles
    private void discardNecklaceIfVisible() {

        for (Treasure t : this.visibleTreasures) {

            if (t.getType() == TreasureKind.necklace) {

                //Le pasamos el tesoro al CardDealer
                CardDealer cd = CardDealer.getInstance();
                cd.giveTreasureBack(t);

                //Lo eliminamos de nuestros tesoros visibles (equipados)
                this.visibleTreasures.remove(t);

                //Salimos del bucle
                break;
            }

        }


    }

    //Cambia el estado de jugador a muerto si no tiene ni tesoros visibles ni 
    //ocultos, modificandon el correspondiente atributo.
    private void dieIfNoTreasures() {

        if (this.visibleTreasures.isEmpty() && this.hiddenTreasures.isEmpty()) {
            this.dead = true;
        }

    }

    //Calcula y devuelve los niveles que puede comprar el jugador con 
    //la lista treasures de tesoros.
    public int computeGoldCoinsValue(ArrayList<Treasure> treasures) {

        int levels;
        int coins = 0;

        //Obtenemos el total de monedas
        for (Treasure t : treasures) {

            coins += t.getGoldCoins();

        }

        //Dividimos entre 1000 cogiendo la parte entera, para no devolver cambio
        levels = coins / 1000;

        //Devolvemos el valor
        return levels;

    }

    //Devuelve true si con los niveles que compra no gana la partida y false en caso contrario.
    private boolean canIBuyLevels(float l) {

        boolean canI = false;

        //Si el nivel del jugador mas los niveles a comprar es menor de 10
        if (this.level + l < 10) {

            canI = true;

        }

        return canI;

    }

    public void applyPrize(Monster currentMoster) {

        int nLevels = currentMoster.getLevelsGained();

        this.incrementLevels(nLevels);

        int nTreasures = currentMoster.getTreasuresGained();

        if (nTreasures > 0) {

            CardDealer dealer = CardDealer.getInstance();

            //Agregamos tantas cartas como hemos ganado
            for (int i = 1; i <= nTreasures; i++) {

                Treasure t = dealer.nextTreasure();
                this.hiddenTreasures.add(t);

            }

        }

    }

    public CombatResult combat(Monster m) {

        CombatResult result;

        int myLevel = this.getCombatLevel(); //1.1.1

        int monsterLevel = this.getOponentLevel(m); //1.1.2

        if (myLevel > monsterLevel) {

            this.applyPrize(m);

            if (this.level >= 10) {
                result = CombatResult.WinAndWinGame;
            } else {
                result = CombatResult.Win;
            }

        } else {

            Dice dice = Dice.getInstance();
            int escape = dice.nextNumber(); //1.1.4

            if (escape < 5) {

                boolean amIDead = m.kills(); //1.1.5

                if (amIDead) {

                    this.die();

                    result = CombatResult.LoseAndDie;

                } else if (this.shouldConvert()) {

                    result = CombatResult.LoseAndConvert;


                } else {

                    BadStuff bad = m.getBadStuff();

                    result = CombatResult.Lose;

                    this.applyBadStuff(bad);
                }

            } else {

                result = CombatResult.LoseAndEscape;

            }
        }
        this.discardNecklaceIfVisible();

        return result;


    }

    public void applyBadStuff(BadStuff bad) {

        int nLevels = this.getLevels(); //1

        this.decrementLevels(nLevels); //2

        this.pendingBadStuff = bad.adjustToFitTreasureLists(this.visibleTreasures, this.hiddenTreasures); //3

        this.setPendingBadStuff(this.pendingBadStuff); //4


    }

    public void makeTreasureVisible(Treasure t) {

        boolean canI = this.canMakeTreasureVisible(t); //1.2.1

        if (canI) {
            this.visibleTreasures.add(t); //1.2.2
            this.hiddenTreasures.remove(t); //1.2.3
        }

    }

    //Comprueba si el tesoro (t) se puede pasar de oculto a visible, 
    //según las reglas del juego
    public boolean canMakeTreasureVisible(Treasure t) {

        boolean result = false;

        //Comprobamos que no tenga ya 4 tesoros visibles
        if (this.visibleTreasures.size() < 4) {
            TreasureKind type = t.getType();
            switch (type) {

                case oneHand: //En el caso de los de una mano hay que comprar algunas cosas

                    //Si está equipado con dos manos no puede agregar un tesoro de una mano
                    if (isTreasureKindInUse(TreasureKind.bothHand)) {
                        result = false;
                    } else {

                        //Recorremos los tesoros visibles para ver si ya tiene alguno de una mano (0, 1 o 2)
                        int i = 0;
                        for (Treasure tv : this.visibleTreasures) {
                            if (tv.getType().equals(TreasureKind.oneHand)) {
                                i++;
                            }
                        }

                        if (i == 2) {
                            //Si están las dos manos ocupadas no puede
                            result = false;
                        } else {
                            //En caso contrario si que puede
                            result = true;
                        }
                    }
                    break;

                default: //El resto de casos, si esta en uso false, si no true
                    result = !isTreasureKindInUse(type);
                    break;

            }

        }

        return result;
    }

    //Este método lo he hecho personalmente para no duplicar codigo en el método anterior
    private boolean isTreasureKindInUse(TreasureKind type) {
        boolean result = false;
        for (Treasure tv : this.visibleTreasures) {

            if (type.equals(tv.getType())) {

                result = true;
                break;

            }

        }
        return result;
    }

    public void discardVisibleTreasure(Treasure t) {

        this.visibleTreasures.remove(t); //1.2.1

        if (this.pendingBadStuff != null && !this.pendingBadStuff.isEmpty()) {

            this.pendingBadStuff.substractVisibleTreasure(t); //1.2.2

        }

        this.dieIfNoTreasures(); //1.2.3
    }

    public void discardHiddenTreasure(Treasure t) {

        this.hiddenTreasures.remove(t); //1.2.1

        if (this.pendingBadStuff != null && !this.pendingBadStuff.isEmpty()) {

            this.pendingBadStuff.substractHiddenTreasure(t); //1.2.2

        }

        this.dieIfNoTreasures(); //1.2.3
    }

    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {

        int levelsMayBought = 0;
        levelsMayBought += this.computeGoldCoinsValue(visible); //1.1.1
        levelsMayBought += this.computeGoldCoinsValue(hidden); //1.1.2

        boolean canI = canIBuyLevels(levelsMayBought); //1.1.3

        if (canI) {
            this.incrementLevels(levelsMayBought); //1.1.4

        }

        this.visibleTreasures.removeAll(visible); //1.1.5
        this.hiddenTreasures.removeAll(hidden); //1.1.6


        CardDealer dealer = CardDealer.getInstance(); //1.1.7

        for (Treasure t : visible) {
            dealer.giveTreasureBack(t); //1.1.9

        }

        for (Treasure t : hidden) {
            dealer.giveTreasureBack(t); //1.1.11

        }

        return canI;

    }

    //Devuelve true cuando el jugador no tiene que cumplir con ningún mal rollo 
    //que cumplir (pendingBadStuff.isEmpty() == true) y no tiene más de 4 tesoros 
    //ocultos y false en caso contrario.
    public boolean validState() {

        return this.pendingBadStuff == null || (this.pendingBadStuff.isEmpty() && this.hiddenTreasures.size() <= 4);

    }

    public void initTreasures() {

        CardDealer dealer = CardDealer.getInstance(); //1

        Dice dice = Dice.getInstance(); //2

        this.bringToLife(); //3

        //Por defecto robamos 1
        Treasure treasure = dealer.nextTreasure(); //4
        this.hiddenTreasures.add(treasure); //5

        int number = dice.nextNumber(); //6

        //NOTA: Le he dado la vuelta, porque segun el esquema siempre entaría en > 1
        if (number == 6) {

            // == 6 roba 3 (1+2)
            treasure = dealer.nextTreasure();  //9
            this.hiddenTreasures.add(treasure); //10


            //NOTA: Aunque no está en el diagrama, se supone que en el GuionP3.pdf
            //Dice que en el caso de == 6 se roban 3 tesoros asi que hay
            //que robar uno mas
            treasure = dealer.nextTreasure();  //9
            this.hiddenTreasures.add(treasure); //10

        } else if (number > 1) {

            // > 1 roba 2 (1+1)
            treasure = dealer.nextTreasure(); //7
            this.hiddenTreasures.add(treasure); //8

        }

    }

    //Devuelve true cuando el jugador tiene algún tesoro visible y false en caso contrario.
    public boolean hasVisibleTreasures() {

        return !this.visibleTreasures.isEmpty();
    }

    public int getLevels() {
        return this.level;
    }

    //Devuelve el número de tesoros visibles que tiene del tipo tKind.
    private int howManyVisibleTreasures(TreasureKind tKind) {

        int i = 0;
        for (Treasure t : this.visibleTreasures) {

            if (t.getType() == tKind) {

                //Incrementamos el numero
                i++;

            }

        }

        return i;

    }

    public int getOponentLevel(Monster m) {

        return m.getBasicValue();

    }

    public boolean shouldConvert() {

        Dice dice = Dice.getInstance();
        int n = dice.nextNumber();
        boolean sholdC = false;

        //Si el dado da 6 debe convertirse
        if (n == 6) {
            sholdC = true;

        }


        return sholdC;


    }

    @Override
    public String toString() {
        return "Nombre: " + this.name + " Nivel: " + this.level;
    }

    public String getName() {
        return this.name;
    }
    
    //EXAMEN
    public String receivePresent(Treasure t) {

        //Agregamos el tesoro
        this.hiddenTreasures.add(t);
        
        //Damos las gracias
        return this.name + " dice: Muchas Gracias!!";
    }
    
    
    //FIN EXAMEN
}
