package virtualpetgame.GUIs;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import virtualpetgame.Game;

public class CreateNewSave extends javax.swing.JFrame implements GetWaitButton, ShowError {

    String petType = "Cat"; //set default here incase the combo box isnt changed
    String saveName;
    
    @Override
    public JButton getWaitButton() {
        return this.continueButton;
    }
    
    public String getPetType() {
        return this.petType;
    }
    
    public String getSaveName() {
        return this.saveName;
    }
    
    @Override
    public void showError() {
        JOptionPane.showMessageDialog(this, "A save with that name already exists!\n"
                                            + "Try something else.", 
                                      "Error",
                                      JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Creates new form CreateNewSave
     */
    public CreateNewSave() {
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

        saveNameLabel = new javax.swing.JLabel();
        saveNameTextField = new javax.swing.JTextField();
        choosePetLabel = new javax.swing.JLabel();
        petTypeComboBox = new javax.swing.JComboBox<>();
        continueButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saveNameLabel.setText("Enter save name:");

        saveNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNameTextFieldActionPerformed(evt);
            }
        });

        choosePetLabel.setText("Choose pet type:");

        petTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cat", "Dog", "Monkey" }));
        petTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petTypeComboBoxActionPerformed(evt);
            }
        });

        continueButton.setText("Continue");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(choosePetLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(petTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(continueButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveNameLabel)
                    .addComponent(saveNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choosePetLabel)
                    .addComponent(petTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNameTextFieldActionPerformed
        this.saveName = saveNameTextField.getText();
    }//GEN-LAST:event_saveNameTextFieldActionPerformed

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        this.petType = String.valueOf(petTypeComboBox.getSelectedItem());
        this.saveName = saveNameTextField.getText();
        synchronized(continueButton) {
            continueButton.notify();
        }
        this.dispose();
    }//GEN-LAST:event_continueButtonActionPerformed

    private void petTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petTypeComboBoxActionPerformed
        this.petType = String.valueOf(petTypeComboBox.getSelectedItem());
    }//GEN-LAST:event_petTypeComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateNewSave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateNewSave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateNewSave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateNewSave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateNewSave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel choosePetLabel;
    private javax.swing.JButton continueButton;
    private javax.swing.JComboBox<String> petTypeComboBox;
    private javax.swing.JLabel saveNameLabel;
    private javax.swing.JTextField saveNameTextField;
    // End of variables declaration//GEN-END:variables
}
