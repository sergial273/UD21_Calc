package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField pantalla;
    private StringBuilder expresion = new StringBuilder();
    private boolean nuevaOperacion = true;

    public Calculadora() {
        setTitle("Calculadora");
        setBounds(400,300,300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pantalla = new JTextField();
        pantalla.setFont(new Font("Arial", Font.PLAIN, 28));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);

        JPanel botonesPanel = new JPanel(new GridLayout(5, 4));

        String[] botonText = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+", "."};

        for (String text : botonText) {
            JButton boton = new JButton(text);
            boton.setFont(new Font("Arial", Font.PLAIN, 24));
            botonesPanel.add(boton);

            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botonPresionado(e);
                }
            });
        }

        add(pantalla, BorderLayout.NORTH);
        add(botonesPanel, BorderLayout.CENTER);
    }

    private void botonPresionado(ActionEvent e) {
        String textoBoton = e.getActionCommand();

        if (nuevaOperacion) {
            pantalla.setText("");
            expresion.setLength(0);
            nuevaOperacion = false;
        }

        if (textoBoton.matches("[0-9]") || textoBoton.equals(".")) {
            pantalla.setText(pantalla.getText() + textoBoton);
            expresion.append(textoBoton);
        } else if (textoBoton.equals("C")) {
            pantalla.setText("");
            expresion.setLength(0);
        } else if (textoBoton.equals("=")) {
            calcularResultado();
        } else {
            if (!pantalla.getText().isEmpty()) {
                pantalla.setText(pantalla.getText() + textoBoton);
                expresion.append(textoBoton);
            }
        }
    }

    private void calcularResultado() {
        try {
            String resultado = evaluarExpresion(expresion.toString());
            pantalla.setText(resultado);
            expresion.setLength(0);
            expresion.append(resultado);
            nuevaOperacion = true;
        } catch (ArithmeticException e) {
            pantalla.setText("Math Error");
            expresion.setLength(0);
            nuevaOperacion = true;
        }
    }

    public String evaluarExpresion(String expresion) {
        String resultado = "0";
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            resultado = engine.eval(expresion).toString();
        } catch (Exception e) {
            throw new ArithmeticException("Math Error");
        }
        return resultado;
    }
}