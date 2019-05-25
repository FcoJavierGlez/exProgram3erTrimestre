package exMayo2019FranciscoJavierGonzálezSabariego.fichero;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaFicheros extends JFrame {

  private JPanel contentPane;
  private JTextPane txtpnSalida;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VentanaFicheros frame = new VentanaFicheros();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public VentanaFicheros() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 500);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JButton btnEntrada = new JButton("Importar fichero");
    btnEntrada.addActionListener(new BotonEntrada());
    btnEntrada.setBounds(124, 11, 191, 41);
    contentPane.add(btnEntrada);
    
    txtpnSalida = new JTextPane();
    txtpnSalida.setEditable(false);
    txtpnSalida.setBounds(28, 98, 377, 239);
    contentPane.add(txtpnSalida);
    
    JLabel lblResultado = new JLabel("Resultado:");
    lblResultado.setBounds(193, 63, 64, 24);
    contentPane.add(lblResultado);
    
    JButton btnExportarFichero = new JButton("Exportar fichero");
    btnExportarFichero.addActionListener(new BotonExportar());
    btnExportarFichero.setBounds(124, 366, 191, 41);
    contentPane.add(btnExportarFichero);
  }
  
  
  
  public class BotonEntrada implements ActionListener {
    public BotonEntrada() {
      
    }
    
    public void actionPerformed(ActionEvent e) {
      JFileChooser fc = new JFileChooser();
      fc.setVisible(true);
      fc.showOpenDialog(null);
      File archivo = fc.getSelectedFile();
      try {
        txtpnSalida.setText("");
        txtpnSalida.setText(CambiaCaracteres.muestraResultado(archivo.getAbsolutePath()));
      } catch (IOException e1) {
        JOptionPane.showMessageDialog(null, "ERROR. No se pudo abrir el fichero.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  
  
  public class BotonExportar implements ActionListener {
    public BotonExportar() {
    }
    
    public void actionPerformed(ActionEvent e) {
      JFileChooser fc = new JFileChooser();
      fc.setVisible(true);
      fc.showSaveDialog(null);
      File archivo = fc.getSelectedFile();
      try {
        CambiaCaracteres.creaFichero(archivo.getAbsolutePath());
        JOptionPane.showMessageDialog(null, "Su archivo fue guardado con éxito.",
            "Información",JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException e1) {
        JOptionPane.showMessageDialog(null, "¡Su archivo no pudo ser guardado!",
               "Advertencia",JOptionPane.WARNING_MESSAGE);
      }
    }
  }
  
  
  
  
}
