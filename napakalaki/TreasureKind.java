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

public enum TreasureKind {

    //Una enumeracion puede ser sin valores (valor automático)
    armor,
    oneHand,
    bothHand,
    helmet,
    shoe,
    necklace;
    
    @Override
    public String toString() {

        switch (this) {

            case armor:
                return "Armadura";

            case oneHand:
                return "1 Mano";

            case bothHand:
                return "2 Manos";

            case helmet:
                return "Cubrecabeza";

            case shoe:
                return "Calzado";

            case necklace:
                return "Collar";

            default:
                return "Error";
        }
    }
}

