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

public class BadStuff {

    //Atributos
    private String text;
    private int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();

    //Constructores
    public BadStuff(String text, int levels, int nVisible, int nHidden) {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures = nHidden;

    }

    public BadStuff(String text, boolean death) {
        this.text = text;
        this.death = death;

    }

    public BadStuff(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        this.text = text;
        this.levels = levels;
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;

    }

    //Métodos 
    
    //Devuelve true cuando el mal rollo que tiene que cumplir el jugador está 
    //vacío, eso significa que el conjunto de atributos del mal rollo indican 
    //que no hay mal rollo que cumplir, plánteate qué valores deberán tener.
    public boolean isEmpty() {
        
        boolean empty = false;

        if (this.levels == 0 && this.death == false && this.nHiddenTreasures == 0 
                && this.nVisibleTreasures == 0 && this.specificHiddenTreasures.isEmpty() 
                && this.specificVisibleTreasures.isEmpty()) {

            empty = true;
        }

        return empty;

    }

    public String getText() {
        return this.text;
    }

    public int getLevels() {
        return this.levels;
    }

    public int getNVisibleTreasures() {
        return this.nVisibleTreasures;
    }

    public int getNHiddenTreasures() {
        return this.nHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return this.specificVisibleTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return this.specificHiddenTreasures;
    }

    //Actualiza el mal rollo que tiene que cumplir el jugador, en función del 
    //tipo de tesoro de t y del tipo de mal rollo que tenga que cumplir el jugador.
    public void substractVisibleTreasure(Treasure t) {

        //No tengo muy claro que sea esto lo que hay que hacer
        this.specificVisibleTreasures.remove(t.getType());
        
    }

    //Igual que el anterior, pero para los ocultos.
    public void substractHiddenTreasure(Treasure t) {
        
        //No tengo muy claro que sea esto lo que hay que hacer
        this.specificHiddenTreasures.remove(t.getType());
        
    }

    //Recibe como parámetros los tesoros visibles y ocultos de los que dispone 
    //el jugador y devuelve un nuevo objeto mal rollo que contiene una lista de 
    //tipos tesoros visibles y ocultos de los que el jugador debe deshacerse. El 
    //objeto de mal rollo devuelto por adjustToFitTreasureList solo contendrá 
    //tipos de tesoros y en una cantidad adecuada a los que el jugador puede 
    //llegar a deshacerse en función de los que dispone.
    public BadStuff adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        
        ArrayList<TreasureKind> tVisible = new ArrayList();
        ArrayList<TreasureKind> tHidden = new ArrayList();
        
        //Recorremos los tesoros
        for (Treasure t: v) {
            //Si no contiene el TreasureKind lo agregamos
            if (!tVisible.contains(t.getType())) {
                tVisible.add(t.getType());
            }
        }
        
        //Recorremos los tesoros
        for (Treasure t: h) {
            //Si no contiene el TreasureKind lo agregamos
            if (!tHidden.contains(t.getType())) {
                tHidden.add(t.getType());
            }
        }

        BadStuff bs = new BadStuff(this.text, 0, tVisible, tHidden);

        return bs;

    }

    //Devuelve true si es un mal rollo es muerte, false en caso contrario.
    public boolean myBadStuffIsDeath() {

        return this.death;

    }

    @Override
    public String toString() {

        return "Mal rollo: " + this.text + " Niveles: " + Integer.toString(this.levels) + " Muerte: " + Boolean.toString(this.death);
    }
    
}