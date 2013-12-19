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

public class CultistPlayer extends Player {

    //Atributos
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;

    //Constructor
    CultistPlayer(Player p, Cultist c) {

        super(p);


        this.myCultistCard = c;
        totalCultistPlayers++; //Incrementamos el contador de cultistPlayers

    }

    //Métodos
    @Override
    public int getCombatLevel() {

        return super.getCombatLevel() + this.myCultistCard.getSpecialValue();

    }

    @Override
    public boolean shouldConvert() {

        //Siempre devuelve false
        return false;

    }

    @Override
    public int getOponentLevel(Monster m) {

        return m.getSpecialValue();

    }

    @Override
    public int computeGoldCoinsValue(ArrayList<Treasure> treasures) {
        
        return super.computeGoldCoinsValue(treasures) * 2;
    }

    public static int getTotalCultistPlayers() {

        return totalCultistPlayers;

    }
    
    public Cultist getCultistCard() {
    
        return this.myCultistCard;
    }
    
}
