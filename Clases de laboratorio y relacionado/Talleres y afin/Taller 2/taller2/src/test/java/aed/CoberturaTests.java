package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        assertEquals("FizzBuzz", cobertura.fizzBuzz(15));
        assertEquals("Fizz", cobertura.fizzBuzz(18));
        assertEquals("Buzz", cobertura.fizzBuzz(25));
        assertEquals("88", cobertura.fizzBuzz(88));
    }

    @Test
    void testNumeroCombinatorio() {
        assertEquals(77520, cobertura.numeroCombinatorio(20,7));
        assertEquals(1, cobertura.numeroCombinatorio(20,20));
        assertEquals(1, cobertura.numeroCombinatorio(20,0));
        assertEquals(20, cobertura.numeroCombinatorio(20,1));
        assertEquals(0, cobertura.numeroCombinatorio(20,70));
        assertEquals(1, cobertura.numeroCombinatorio(0,0));
    }

    @Test
    void testRepeticionesConsecutivas() {
        assertEquals(1, cobertura.repeticionesConsecutivas(new int[]{6,7,8,9,3,4,5,1}));
        assertEquals(0, cobertura.repeticionesConsecutivas(new int[0]));
        assertEquals(4, cobertura.repeticionesConsecutivas(new int[]{6,7,7,7,8,8,8,8,9,3,4,5,1}));
        assertEquals(2, cobertura.repeticionesConsecutivas(new int[]{6,7,7,8,8,9,3,4,5,1}));
        assertEquals(8, cobertura.repeticionesConsecutivas(new int[]{7,7,7,7,7,7,7,7}));
    }
}
