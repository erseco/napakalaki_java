package napakalaki;

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


import gui.*;
import java.util.ArrayList;

public class Main {

    /**
     * Punto de entrada al programa
     */
    public static void main(String[] args) {
        
        //Obtenemos el singleton del juego
        Napakalaki napakalakiModel = Napakalaki.getInstance();

        //Inicializamos el juego
        
        NapakalakiView napakalakiView = new NapakalakiView();
        
        
        Dice.createInstance(napakalakiView);
        

        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);

        ArrayList<String> names = namesCapture.getNames();


        napakalakiModel.initGame(names);
        
        napakalakiView.setNapakalaki(napakalakiModel);
        napakalakiView.showView(); 

    }

}
