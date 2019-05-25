package exMayo2019FranciscoJavierGonzálezSabariego.fecha;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaFechas extends JFrame {

  private JPanel contentPane;
  private JTextField textEntrada;
  private JTextField textSalida;
  
  private Fechas f = new Fechas();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VentanaFechas frame = new VentanaFechas();
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
  public VentanaFechas() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 320);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblIntroduzcaUnaFecha = new JLabel("Fecha (dd/mm/aaaa):");
    lblIntroduzcaUnaFecha.setBounds(31, 11, 122, 27);
    contentPane.add(lblIntroduzcaUnaFecha);
    
    textEntrada = new JTextField();
    textEntrada.setBounds(163, 11, 242, 27);
    contentPane.add(textEntrada);
    textEntrada.setColumns(10);
    
    JButton btnValidaFecha = new JButton("Valida Fecha");
    btnValidaFecha.addActionListener(new BotonValidar(textEntrada));
    btnValidaFecha.setBounds(156, 49, 114, 32);
    contentPane.add(btnValidaFecha);
    
    JButton btnIncrementaDa = new JButton("Día posterior");
    btnIncrementaDa.addActionListener(new BotonIncrementaDia(textEntrada));
    btnIncrementaDa.setBounds(20, 93, 114, 32);
    contentPane.add(btnIncrementaDa);
    
    JButton btnDecrementaDa = new JButton("Día anterior");
    btnDecrementaDa.addActionListener(new BotonDecrementaDia(textEntrada));
    btnDecrementaDa.setBounds(156, 92, 114, 32);
    contentPane.add(btnDecrementaDa);
    
    JButton btnDiasHastaHoy = new JButton("Días hasta hoy");
    btnDiasHastaHoy.addActionListener(new BotonDiasHastaHoy(textEntrada));
    btnDiasHastaHoy.setBounds(291, 92, 122, 32);
    contentPane.add(btnDiasHastaHoy);
    
    textSalida = new JTextField();
    textSalida.setEditable(false);
    textSalida.setBounds(20, 136, 393, 84);
    contentPane.add(textSalida);
    textSalida.setColumns(10);
    
    JButton btnTerminar = new JButton("Terminar");
    btnTerminar.addActionListener(new BotonTerminar());
    btnTerminar.setBounds(164, 231, 89, 34);
    contentPane.add(btnTerminar);
  }
  
  
  public class BotonValidar implements ActionListener {
    private JTextField textEntrada;
    
    public BotonValidar(JTextField textEntrada) {
      this.textEntrada=textEntrada;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        if (f.validaFecha(textEntrada.getText())) {
          textSalida.setText("");
          textSalida.setText("La fecha es válida.");
        }
      } catch (fechaInvalida e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "La fecha es inválida.",
            "Error", JOptionPane.ERROR_MESSAGE);
      } catch (insertaFecha e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "Inserta una fecha.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  public class BotonIncrementaDia implements ActionListener {
    private JTextField textEntrada;
    
    public BotonIncrementaDia(JTextField textEntrada) {
      this.textEntrada=textEntrada;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        textSalida.setText(f.incrementaDia(textEntrada.getText()));
      } catch (fechaInvalida e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "La fecha es inválida.",
            "Error", JOptionPane.ERROR_MESSAGE);
      } catch (insertaFecha e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "Inserta una fecha.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  public class BotonDecrementaDia implements ActionListener {
    private JTextField textEntrada;
    
    public BotonDecrementaDia(JTextField textEntrada) {
      this.textEntrada=textEntrada;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        textSalida.setText(f.decrementaDia(textEntrada.getText()));
      } catch (fechaInvalida e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "La fecha es inválida.",
            "Error", JOptionPane.ERROR_MESSAGE);
      } catch (insertaFecha e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "Inserta una fecha.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  public class BotonDiasHastaHoy implements ActionListener {
    private JTextField textEntrada;
    
    public BotonDiasHastaHoy(JTextField textEntrada) {
      this.textEntrada=textEntrada;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        textSalida.setText(String.valueOf(f.diasHastaHoy(textEntrada.getText())+" días de diferencia."));
      } catch (fechaInvalida e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "La fecha es inválida.",
            "Error", JOptionPane.ERROR_MESSAGE);
      } catch (insertaFecha e1) {
        textSalida.setText("");
        JOptionPane.showMessageDialog(null, "Inserta una fecha.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  
  public class BotonTerminar implements ActionListener {
    
    public BotonTerminar() {      
    }
    
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }
  
  
}
