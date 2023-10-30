package UD21_Calc.UD21_Calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calc.Calculadora;

public class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void before() {
        calculadora = new Calculadora();
    }

    @Test
    void testSuma() {
        String resultado = calculadora.evaluarExpresion("2+3");
        assertEquals("5", resultado);
    }

    @Test
    void testResta() {
        String resultado = calculadora.evaluarExpresion("5-2");
        assertEquals("3", resultado);
    }

    @Test
    void testMultiplicacion() {
        String resultado = calculadora.evaluarExpresion("4*6");
        assertEquals("24", resultado);
    }

    @Test
    void testDivision() {
        String resultado = calculadora.evaluarExpresion("10/2");
        assertEquals("5", resultado);
    }

    @Test
    void testDivisionPorCero() {
    	String resultado = calculadora.evaluarExpresion("5/0");
    	assertEquals("Infinity", resultado);
    }

    @Test
    void testExpresionInvalida() {
        assertThrows(ArithmeticException.class, () -> {
            calculadora.evaluarExpresion("2+2*");
        });
    }
}
