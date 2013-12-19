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

public class Cultist implements Card {

    //Atributos
    private final String name = "Sectario";
    private int gainedLevels;

    private String icon;

    //Constructor
    Cultist(int gainedLevels, String icon) {

        this.gainedLevels = gainedLevels;
        
        this.icon = icon;

    }

    //Métodos
    @Override
    public String toString() {
        return "Nombre: " + this.name + " Niveles: " + this.gainedLevels;
    }

    @Override
    public int getBasicValue() {
        return this.gainedLevels;
    }

    @Override
    public int getSpecialValue() {
        return this.getBasicValue() * CultistPlayer.getTotalCultistPlayers();
                
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public String getIcon() {
        return "/resources/cultists/" + this.icon +  ".png";
    }
}
