package UD21_Calc.UD21_Calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calc.mainApp;

public class mainAppTest {

    @Test
    void testMainMethod() {
    	@SuppressWarnings("unused")
		mainApp c = new mainApp();
        assertDoesNotThrow(() -> mainApp.main(new String[]{}));
    }
}
