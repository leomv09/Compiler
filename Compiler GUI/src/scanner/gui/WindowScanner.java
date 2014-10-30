package scanner.gui;

import file.utils.FileManager;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import scanner.LexicalAnalysisReport;
import scanner.LexicalAnalyzer;
import scanner.TokenList;

/**
 *
 * @author Leo
 */
public class WindowScanner extends javax.swing.JFrame {
    
    private LexicalAnalysisReport report;
    
    /**
     * Creates new form WindowScanner.
     */
    public WindowScanner()
    {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        codeArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tokenResultArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        errorResultArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        analyzeButton = new javax.swing.JButton();
        saveReportButton = new javax.swing.JButton();
        loadFileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scanner GUI");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        codeArea.setColumns(20);
        codeArea.setRows(5);
        codeArea.setMinimumSize(new java.awt.Dimension(0, 1000));
        jScrollPane2.setViewportView(codeArea);

        jPanel2.add(jScrollPane2);

        tokenResultArea.setEditable(false);
        tokenResultArea.setColumns(20);
        tokenResultArea.setRows(5);
        jScrollPane5.setViewportView(tokenResultArea);

        jPanel2.add(jScrollPane5);

        errorResultArea.setEditable(false);
        errorResultArea.setColumns(20);
        errorResultArea.setRows(5);
        jScrollPane4.setViewportView(errorResultArea);

        jPanel2.add(jScrollPane4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jLabel3.setText("Errores:");

        jLabel2.setText("Tokens:");

        jLabel1.setText("Texto a analizar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(206, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.NORTH);

        analyzeButton.setText("Analizar");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });

        saveReportButton.setText("Guardar Reporte");
        saveReportButton.setEnabled(false);
        saveReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveReportButtonActionPerformed(evt);
            }
        });

        loadFileButton.setText("Cargar Archivo");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analyzeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadFileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveReportButton)
                .addContainerGap(389, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(analyzeButton)
                    .addComponent(saveReportButton)
                    .addComponent(loadFileButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        loadFileButton.getAccessibleContext().setAccessibleName("");

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void saveReport(File file)
    {
        FileManager manager = new FileManager();
        manager.writeToFile(file, this.report.getReportContent());
    }
    
    private String readFile(File file)
    {
        FileManager manager = new FileManager();
        return manager.readFile(file);
    }
    
    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        TokenList tokens = analyzer.analyze(this.codeArea.getText());
        
        this.report = new LexicalAnalysisReport(tokens);
        this.errorResultArea.setText(this.report.getReportErrors());
        this.tokenResultArea.setText(this.report.getReportTokens());
        this.saveReportButton.setEnabled(true);
    }//GEN-LAST:event_analyzeButtonActionPerformed

    private void saveReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save the report");   
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            this.saveReport(file);
            JOptionPane.showMessageDialog(this, "Reporte guardado exitosamente en:\n" + file.getAbsolutePath());
        }
    }//GEN-LAST:event_saveReportButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to read");   
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String fileContent = this.readFile(file);
            this.codeArea.setText(fileContent);
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed

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
            java.util.logging.Logger.getLogger(WindowScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowScanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowScanner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JTextArea codeArea;
    private javax.swing.JTextArea errorResultArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JButton saveReportButton;
    private javax.swing.JTextArea tokenResultArea;
    // End of variables declaration//GEN-END:variables
}