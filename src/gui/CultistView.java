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
package gui;

import napakalaki.*;

public class CultistView extends javax.swing.JPanel {

    private Cultist cultistModel;
    private boolean selected = false;

    public boolean isSelected() {

        return this.selected;

    }

    public Cultist getCultist() {

        return this.cultistModel;

    }

    public void setCultist(Player p) {

        if (p instanceof CultistPlayer) {
        
            CultistPlayer cp = (CultistPlayer)p;
       
            this.setVisible(true);
        
        
            cultistModel = cp.getCultistCard();

            String nameText = "<html>" + cultistModel.getName() + "</html>";

            lblName.setText(nameText);

            String levelText = Integer.toString(cultistModel.getBasicValue()) + " por cada sectario";

            lblLevel.setText(levelText);


            lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(cultistModel.getIcon())));
            
        } else {
            
            this.setVisible(false);
            
        }
        
        //Forzamos una llamada al evento para que se cambie el color
        formMouseClicked(null);

        //Finalizar con la siguiente orden para que los cambios se hagan efectivos
        repaint();
    }

    /**
     * Creates new form CultistView
     */
    public CultistView() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        lblForever = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setForeground(new java.awt.Color(0, 204, 0));
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(140, 220));
        setSize(new java.awt.Dimension(137, 216));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblName.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        lblName.setText("Sectario");
        lblName.setToolTipText("");
        add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 50, 20));

        lblForever.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblForever.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblForever.setText("Sectario Forever");
        add(lblForever, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 100, -1));

        lblLevel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        lblLevel.setText("+1 por cada sectario");
        add(lblLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 110, -1));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cultists/01.png"))); // NOI18N
        add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 120, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        this.selected = !this.selected;

        setOpaque(this.selected);

        this.repaint();
//        if (this.selected) {
//        
//            
//        
//            this.selected = false;
//        } else {
//        
//
//           
//            this.selected = true;
//        }



    }//GEN-LAST:event_formMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblForever;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}