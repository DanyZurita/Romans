package edu.elsmancs.romans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import edu.elsmancs.romans.RomanSymbols;
import org.junit.BeforeClass;
import org.junit.Test;

public class RomanSymbolsTest {

    public static RomanSymbols numeroRomano;

    @BeforeClass
    public static void setup() {
        numeroRomano = new RomanSymbols();
        numeroRomano.initRegexDicionario();
    }

    /**
     * Grupos sumatorios M, C, X, I
     */

    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "UMMU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "UMMMU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setRomanSymbols(testCase);
        assertNotEquals(1000, numeroRomano.toDecimal());

        /**
         * El caso MMMM es control de errores 
         * y no puede estas en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que programar la gestion de errores
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "UMMMUCCCU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(3300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UMMMUXXXU";
        numeroRomano.setRomanSymbols(testCase);

        assertEquals(3030, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UMMMUIIIU";
        numeroRomano.setRomanSymbols(testCase);

        assertEquals(3003, numeroRomano.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "UMMMUDUIIIU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(3503, numeroRomano.toDecimal());

        testCase = "MMMUCDUIIIU";
        numeroRomano.setRomanSymbols(testCase);
        assertNotEquals(3503, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
     * IV(4), IX(9), 
     * XL(40), XC(90), 
     * CD(400), CM(900)
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "UCDU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(400, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "UXLU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(40, numeroRomano.toDecimal());  

        testCase = "UXCU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(90, numeroRomano.toDecimal());        
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "UIVU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(4, numeroRomano.toDecimal());  

        testCase = "UIXU";
        numeroRomano.setRomanSymbols(testCase);
        assertEquals(9, numeroRomano.toDecimal());  
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String test = "MMMDCCCLXXXVIII"; // 3888
        numeroRomano.setRomanSymbols(test);
        assertEquals(3888, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String test = "MMDCCLXXVII"; // 2777
        numeroRomano.setRomanSymbols(test);
        assertEquals(2777, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String test = "CDXLIV"; // 444
        numeroRomano.setRomanSymbols(test);
        assertEquals(444, numeroRomano.toDecimal());

        test = "CDXXXIX"; // 439
        numeroRomano.setRomanSymbols(test);
        assertEquals(439, numeroRomano.toDecimal());
    }

    @Test
    public void initArrayRegex_test() {
        String test = "V";
        numeroRomano.setRomanSymbols(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().longitud());
        assertEquals(5, numeroRomano.valorDecimal(test));
        assertEquals("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSumatorio"));
		assertEquals("(C[DM])|(X[LC])|(I[VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSustractivo"));
    }

    @Test
    public void toDecimal() {
        String test = "V";
        numeroRomano.setRomanSymbols(test);
        assertEquals(2, numeroRomano.getExpresionesRegulares().size());
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])"));
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(C[DM])|(X[LC])|(I[VX])"));		
    }

    @Test
    public void valorDecimal_test() {
        String test = "V";
        numeroRomano.setRomanSymbols(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().getRegex().size());
        assertEquals(5, numeroRomano.valorDecimal(test));

        test = "IV"; 
        numeroRomano.setRomanSymbols(test);
        assertEquals(4, numeroRomano.valorDecimal(test));

        test = "CM"; 
        numeroRomano.setRomanSymbols(test);
        assertEquals(900, numeroRomano.valorDecimal(test));

        /**
         *  test = "U";
         * numeroRomano.setNumeroRomano("U");
         * assertEquals(900, numeroRomano.valorDecimal(test));
         */
    }
}