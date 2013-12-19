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
import java.util.Collections;

public class CardDealer {

    //Atributos
    private static CardDealer instance = null;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Cultist> unusedCultists;

    //Constructor
    private CardDealer() {
    }

    //Métodos
    public static CardDealer getInstance() {
        if (instance == null) {
            instance = new CardDealer();
        }
        return instance;
    }

    private void initTreasureCardDeck() {

        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList(); //inicializamos los dos array

        //Definimos las variables donde luego almacenaremos los datos

        //El constructor de Treasure pide: nombre, gold, min, max y type

        unusedTreasures.add(new Treasure("¡Śı mi amo!", 0, 4, 7, TreasureKind.helmet, "01"));
        unusedTreasures.add(new Treasure("Botas de investigación", 600, 3, 4, TreasureKind.shoe, "02"));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 500, 3, 5, TreasureKind.helmet, "03"));
        unusedTreasures.add(new Treasure("A prueba de babas", 400, 2, 5, TreasureKind.armor, "04"));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 800, 1, 1, TreasureKind.bothHand, "05"));
        unusedTreasures.add(new Treasure("Casco minero", 400, 2, 4, TreasureKind.helmet, "06"));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 600, 4, 8, TreasureKind.bothHand, "07"));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 100, 1, 7, TreasureKind.armor, "08"));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.oneHand, "09"));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.oneHand, "10"));
        unusedTreasures.add(new Treasure("Fez alópodo", 700, 3, 5, TreasureKind.helmet, "11"));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 500, 2, 5, TreasureKind.oneHand, "12"));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.armor, "13"));
        unusedTreasures.add(new Treasure("Gaita", 500, 4, 5, TreasureKind.bothHand, "14"));
        unusedTreasures.add(new Treasure("Insecticida", 300, 2, 3, TreasureKind.oneHand, "15"));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 700, 4, 6, TreasureKind.bothHand, "16"));
        unusedTreasures.add(new Treasure("Garabato Mistico", 300, 2, 2, TreasureKind.oneHand, "17"));
        unusedTreasures.add(new Treasure("La fuerza de Mr. T", 1000, 0, 0, TreasureKind.necklace, "18"));
        unusedTreasures.add(new Treasure("La rebeca metálica", 400, 2, 3, TreasureKind.armor, "19"));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 200, 3, 4, TreasureKind.oneHand, "20"));
        unusedTreasures.add(new Treasure("Necro-playboycon", 300, 3, 5, TreasureKind.oneHand, "21"));
        unusedTreasures.add(new Treasure("Lanzallamas", 800, 4, 8, TreasureKind.bothHand, "22"));
        unusedTreasures.add(new Treasure("Necro-comicón", 100, 1, 1, TreasureKind.oneHand, "23"));
        unusedTreasures.add(new Treasure("Necronomicón", 800, 5, 7, TreasureKind.bothHand, "24"));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 400, 3, 6, TreasureKind.bothHand, "25"));
        unusedTreasures.add(new Treasure("Necro-gnomicón", 200, 2, 4, TreasureKind.oneHand, "26"));
        unusedTreasures.add(new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.helmet, "27"));
        unusedTreasures.add(new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.oneHand, "28"));
        unusedTreasures.add(new Treasure("Tentácula de pega", 200, 0, 1, TreasureKind.helmet, "29"));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 500, 0, 1, TreasureKind.shoe, "30"));
        unusedTreasures.add(new Treasure("Shogulador", 600, 1, 1, TreasureKind.bothHand, "31"));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.oneHand, "32"));

    }

    private void initMonsterCardDeck() {

        unusedMonsters = new ArrayList();
        usedMonsters = new ArrayList(); //inicializamos los dos array

        //Definimos las variables donde luego almacenaremos los datos
        BadStuff badStuff;
        Prize prize;
        ArrayList<TreasureKind> v;
        ArrayList<TreasureKind> h;

        //Monstruo 1
        v = new ArrayList();
        v.add(TreasureKind.armor);
        h = new ArrayList();
        h.add(TreasureKind.armor);
        badStuff = new BadStuff("Pierdes tu armadura visible y otra oculta", 0, v, h);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, badStuff, prize, "01"));

        //Monstruo 2
        v = new ArrayList();
        v.add(TreasureKind.helmet);
        h = new ArrayList();
        badStuff = new BadStuff("Embobados con el lindo primigenio te descartas de tu casco visible", 0, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, badStuff, prize, "02"));

        //Monstruo 3
        v = new ArrayList();
        v.add(TreasureKind.shoe);
        h = new ArrayList();
        badStuff = new BadStuff("El primordial bostezo contagioso. Pierdes el calzado visible", 0, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, badStuff, prize, "03"));

        //Monstruo 4
        v = new ArrayList();
        v.add(TreasureKind.oneHand);
        h = new ArrayList();
        h.add(TreasureKind.oneHand);
        badStuff = new BadStuff("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0, v, h);
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Ángeles de la noche ibicenca", 14, badStuff, prize, "04"));


        //Monstruo 5
        v = new ArrayList();
        v.add(TreasureKind.shoe);
        v.add(TreasureKind.oneHand);
        v.add(TreasureKind.necklace);
        v.add(TreasureKind.helmet);
        v.add(TreasureKind.bothHand);
        v.add(TreasureKind.armor);
        h = new ArrayList();
        badStuff = new BadStuff("Pierdes todos tus tesoros visibles", 0, v, h);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 10, badStuff, prize, "05"));

        //Monstruo 6
        v = new ArrayList();
        v.add(TreasureKind.armor);
        h = new ArrayList();
        badStuff = new BadStuff("Pierdes la armadura visible", 0, v, h);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, badStuff, prize, "06"));

        //Monstruo 7
        v = new ArrayList();
        v.add(TreasureKind.armor);
        h = new ArrayList();
        badStuff = new BadStuff("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bichgooth", 2, badStuff, prize, "07"));

        //Monstruo 8
        badStuff = new BadStuff("Pierdes 5 niveles", 5, 0, 0);
        prize = new Prize(4, 2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, badStuff, prize, "08"));

        //Monstruo 9
        badStuff = new BadStuff("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas", 2, badStuff, prize, "09"));

        //Monstruo 10
        badStuff = new BadStuff("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto", true);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, badStuff, prize, "10"));

        //Monstruo 11
        badStuff = new BadStuff("Pierdes 2 niveles y 2 tesoros ocultos.", 2, 0, 2);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, badStuff, prize, "11"));

        //Monstruo 12
        v = new ArrayList();
        v.add(TreasureKind.oneHand);
        h = new ArrayList();
        badStuff = new BadStuff("Te intentas escaquear. Pierdes una mano visible.", 0, v, h);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, badStuff, prize, "12"));

        //Monstruo 13
        v = new ArrayList();
        h = new ArrayList();
        badStuff = new BadStuff("Da mucho asquito, Pierdes 3 niveles", 3, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, badStuff, prize, "13"));

        //Monstruo 14
        badStuff = new BadStuff("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 12, badStuff, prize, "14"));

        //Monstruo 15
        badStuff = new BadStuff("La familia te atrapa. Estas muerto", true);
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, badStuff, prize, "15"));

        //Monstruo 16
        v = new ArrayList();
        v.add(TreasureKind.bothHand);
        h = new ArrayList();
        badStuff = new BadStuff("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, v, h);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, badStuff, prize, "16"));

        //Monstruo 17
        v = new ArrayList();
        v.add(TreasureKind.helmet);
        h = new ArrayList();
        badStuff = new BadStuff("Te asusta en la noche. Pierdes un casco visible.", 0, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia", 5, badStuff, prize, "17"));

        //Monstruo 18
        badStuff = new BadStuff("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El lenguas", 20, badStuff, prize, "18"));

        //Monstruo 19
        v = new ArrayList();
        v.add(TreasureKind.bothHand);
        h = new ArrayList();
        badStuff = new BadStuff("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.", 3, v, h);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bicefalo", 20, badStuff, prize, "19"));

        
        
         //Montruos contra sectarios

        v = new ArrayList();
        v.add(TreasureKind.oneHand);
        h = new ArrayList();
        badStuff = new BadStuff("Pierdes 1 mano visible.",0, v, h);
        prize = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable", 10, badStuff, prize, -2, "20"));
        
        
        v = new ArrayList();
        v.add(TreasureKind.shoe);
        v.add(TreasureKind.oneHand);
        v.add(TreasureKind.necklace);
        v.add(TreasureKind.helmet);
        v.add(TreasureKind.bothHand);
        v.add(TreasureKind.armor);
        h = new ArrayList();
        
        badStuff = new BadStuff("Pierdes tus tesoros visibles. Jajaja.",0, v, h);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos Oculares", 6, badStuff, prize, +2, "21"));
        
        
        badStuff = new BadStuff("Hoy no es tu dia de suerte. Mueres.",true);
        prize = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, badStuff, prize, +4, "22"));
  
        
        v = new ArrayList();
        h = new ArrayList();
        
        badStuff = new BadStuff("Tu gobierno te recorta 2 niveles.", 2, v, h);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente Político", 8, badStuff, prize, -2, "23"));
        
        
        v = new ArrayList();
        v.add(TreasureKind.helmet);
        v.add(TreasureKind.armor);
        h = new ArrayList();
        h.add(TreasureKind.bothHand);
        h.add(TreasureKind.oneHand);
        
        badStuff = new BadStuff("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 0, v, h);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, badStuff, prize, +5, "24"));
        
        
        v = new ArrayList();
        h = new ArrayList();
        
        badStuff = new BadStuff("Pierdes 2 niveles.", 2, v, h);
        prize = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, badStuff, prize, -4, "25"));
        
        v = new ArrayList();
        h = new ArrayList();
        
        badStuff = new BadStuff("Pintalabios negro. Pierdes 2 niveles.", 2, v, h);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, badStuff, prize, +3, "26"));     

       
          
    }
    
    private void initCultistCardDeck() {

        unusedCultists = new ArrayList();

        //Definimos las variables donde luego almacenaremos los datos

        //El constructor de Cultist pide: nombre, gainedLevels

        unusedCultists.add(new Cultist(1, "01"));
        unusedCultists.add(new Cultist(2, "02"));
        unusedCultists.add(new Cultist(1, "03"));
        unusedCultists.add(new Cultist(2, "04"));
        unusedCultists.add(new Cultist(1, "05"));
        unusedCultists.add(new Cultist(1, "06"));

        
    }

    //Baraja el mazo de cartas de tesoros unusedTreasures.
    private void shuffleTreasures() {
        Collections.shuffle(this.unusedTreasures);
    }

    //Baraja el mazo de cartas de monstruos unusedMonsters
    private void shuffleMonsters() {
        Collections.shuffle(this.unusedMonsters);
    }
    
    //Baraja el mazo de cartas de cultistas unusedCultists
    private void shuffleCultist() {
        Collections.shuffle(this.unusedCultists);
    }

    //Devuelve el siguiente tesoro que hay en el mazo de tesoros (unusedTreasures) 
    //y lo elimina de él. Si el mazo está vacío, pasa el mazo de descartes 
    //(usedTreasures) al mazo de tesoros y lo baraja, dejando el mazo de descartes vacío.
    public Treasure nextTreasure() {
        
        
        //Comprobamos si tenemos cartas en el mazo
        if (this.unusedTreasures.isEmpty()) {
            
            //Recorremos las cartas descartadas
            for (Treasure t: this.usedTreasures) {
                //Las agregamos al mazo sin usar
                this.unusedTreasures.add(t);
            }
            
            //Las barajamos
            this.shuffleTreasures();
            
            //Limpiamos el mazo de descartes
            this.usedTreasures.clear();
        
        }
        
        //Obtengo la primera carta del mazo
        Treasure t = this.unusedTreasures.get(0);
        
        //La agregamos al mazo de descartes
        this.usedTreasures.add(t);
        
        //La eliminamos del mazo
        this.unusedTreasures.remove(t);
        
        //Devolvemos la carta
        return t;

    }
    
    

    //Igual que la anterior pero con el mazo de monstruos.
    public Monster nextMonster() {
        
        //Comprobamos si tenemos cartas en el mazo
        if (this.unusedMonsters.isEmpty()) {
            
            //Recorremos las cartas descartadas
            for (Monster m: this.usedMonsters) {
                //Las agregamos al mazo sin usar
                this.unusedMonsters.add(m);
            }
            
            //Las barajamos
            this.shuffleMonsters();
            
            //Limpiamos el mazo de descartes
            this.usedMonsters.clear();
        
        }
        
        //Obtengo la primera carta del mazo
        Monster m = this.unusedMonsters.get(0);
        
        //La agregamos al mazo de descartes
        this.usedMonsters.add(m);
        
        //La eliminamos del mazo
        this.unusedMonsters.remove(m);
        
        //Devolvemos la carta
        return m;
        
    }



    //Igual que la anterior pero con el mazo de monstruos.
    public Cultist nextCultist() {
        
        //Comprobamos si tenemos cartas en el mazo
        if (this.unusedCultists.isEmpty()) {
            
            //Recorremos las cartas descartadas
            this.initCultistCardDeck();
            
            //Las barajamos
            this.shuffleCultist();
            
        }
        
        //Obtengo la primera carta del mazo
        Cultist m = this.unusedCultists.get(0);
        
        
        //La eliminamos del mazo
        this.unusedCultists.remove(m);
        
        //Devolvemos la carta
        return m;
        
    }
    
    //Introduce en el mazo de descartes de tesoros (usedTreasures) el tesoro t.
    public void giveTreasureBack(Treasure t) {
        this.usedTreasures.add(t);
    }

    //Introduce en el mazo de descartes de monstruos (usedMonsters) al monstruo m.
    public void giveMonsterBack(Monster m) {
        this.usedMonsters.add(m);
    }

    public void initCards() {
        
        this.initTreasureCardDeck(); //1.2.1
        this.shuffleTreasures();
        
        this.initMonsterCardDeck();  //1.2.2
        this.shuffleMonsters();
        
        this.initCultistCardDeck();  //1.2.3
        this.shuffleCultist();
    }

}
