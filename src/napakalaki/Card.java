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

public interface Card {

    //Métodos
    public int getBasicValue();
   
    public int getSpecialValue();

    //Este metodo devolverá la cadena que usaremos para mostrar el icono
    public String getIcon();
    
    
}
