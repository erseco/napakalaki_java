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

public class Monster implements Card {

    //Atributos
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadStuff badStuff;
    private int levelChangeAgainstCultistPlayer;
    
    private String icon;

    //Constructor
    public Monster(String name, int combatLevel, String icon) {
        this.name = name;
        this.combatLevel = combatLevel;
        
        this.icon = icon;

    }

    public Monster(String name, int level, BadStuff bc, Prize price, String icon) {
        this.name = name;
        this.combatLevel = level;

        this.badStuff = bc;
        this.prize = price;

        this.icon = icon;
    }

    public Monster(String name, int level, BadStuff bc, Prize price, int levelChangeAgainstCultistPlayer, String icon) {
        this(name, level, bc, price, icon);
        this.levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer;

        this.icon = icon;
    }

    //Métodos
    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.combatLevel;
    }

    public BadStuff getBadStuff() {
        return this.badStuff;
    }

    public Prize getPrize() {
        return this.prize;
    }
    
    public int getLevelsGained() {
        return this.prize.getLevels();

    }

    public int getTreasuresGained() {
        return this.prize.getTreasures();

    }

    //Devuelve true cuando el mal rollo del monstruo es muerte y false en caso contrario.
    public boolean kills() {
        return this.badStuff.myBadStuffIsDeath(); //1.1.5.1

    }

    @Override
    public String toString() {
        return "Nombre: " + this.name + " Nivel: " + Integer.toString(this.combatLevel);
    }

    @Override
    public int getBasicValue() {
        return this.getLevel();
    }

    @Override
    public int getSpecialValue() {
        return this.getLevel() + this.levelChangeAgainstCultistPlayer;
    }

    @Override
    public String getIcon() {
        return "/resources/monsters/" + this.icon +  ".png";
    }
}
