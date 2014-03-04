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

public class Prize {

    //Atributos
    private int treasures;
    private int levels;

    //Constructor
    Prize(int treasures, int levels) {

        this.treasures = treasures;
        this.levels = levels;

    }

    //Métodos
    public int getTreasures() {
        return this.treasures;
    }

    public int getLevels() {
        return this.levels;
    }

    @Override
    public String toString() {
        return "Tesoros ganados: " + Integer.toString(this.treasures) + "\nNiveles: " + Integer.toString(this.levels);
    }
}
