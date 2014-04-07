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

public class Treasure implements Card {

    //Atributos
    private String name;
    private int goldCoins;
    private int minBonus;
    private int maxBonus;
    private TreasureKind type;
    
    private String icon;

    //Constructor
    Treasure(String n, int g, int min, int max, TreasureKind t, String icon) {

        this.name = n;
        this.goldCoins = g;
        this.minBonus = min;
        this.maxBonus = max;
        this.type = t;
        this.icon = icon;

    }

    //Métodos
    public String getName() {
        return this.name;
    }

    public int getGoldCoins() {
        return this.goldCoins;
    }

    public int getMinBonus() {
        return this.minBonus;
    }

    public int getMaxBonus() {
        return this.maxBonus;
    }

    public TreasureKind getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.name + " Tipo: " + this.type;
    }

    @Override
    public int getBasicValue() {
        return this.getMinBonus();
    }

    @Override
    public int getSpecialValue() {
        return this.getMaxBonus();
    }

    @Override
    public String getIcon() {
        return "/resources/treasures/" + this.icon +  ".png";
    }
}
