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

public enum CombatResult {

    //Una enumeracion puede ser sin valores (valor automático)
    WinAndWinGame,
    Win,
    Lose,
    LoseAndEscape,
    LoseAndDie,
    LoseAndConvert;

    @Override
    public String toString() {

        switch (this) {

            case WinAndWinGame:
                return "Ganaste el Juego";

            case Win:
                return "Ganaste";

            case Lose:
                return "Perdiste";

            case LoseAndEscape:
                return "Perdiste  pero escapaste";

            case LoseAndDie:
                return "Perdiste y te mueres!";

            case LoseAndConvert:
                return "Perdiste pero te conviertes";

            default:
                return "Error";
        }
    }
}
