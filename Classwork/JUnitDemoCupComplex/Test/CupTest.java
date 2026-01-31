package Classwork.JUnitDemoCupComplex.Test;

import java.beans.Transient;

import org.junit.jupiter.api.Test

public class CupTest {
    @BeforeEach
    void setup(){//this setup runs before each method
        Cup cup = new Cup("water", 75.0);
        System.out.println("Starting a new test...");
    }

    @Test
    void testObjectCreation(){
        Cup cup = new Cup("water", 75.0);
        assertEquals("water", cup.getLiquidType());
        assertEquals(75.0, cup.getPercentFull(), 0.001);
    }
    
    @Test 
    void testObjectCreation(){
        assertEquals("water", cup.getLiquidType());
        assertEquals(75.0, cup.getPercentFull(), 0.001);
    }
    @Test 
    void testObjectCreationWithAssertAll(){
        assertAll("Correctly build object",
            () -> assertEquals("waters", cup.getLiquidType()),
            () -> assertEquals(79.0, cup.getPercentFull(), 0.001)
        );

    }


    @Test
    void testisEmpty(){
        Cup cup = new Cup("water", 75.0);
        assertFalse(cup.isEmpty());
        
        //assertTrue(!cup.isEmpty());
    }

    @Test 
    void testSetLiquidwithNull(){
        Cup cup = new Cup("water", 75.0);
        cup.setLiquidType(null);
        assertNotNull(cup.getLiquidType());
    }


    @Test 
    void testExternalLibrary(){
        //this fails on urpose ppurely to show that if something is still in development, this can be created without it affecting anything else 
        fail();
    }
    //assertThrows is very different in JUnit4
    @Test 
    void testSetBadPercentThrows(){
        assertThrows(IllegalArgumentException.class, () -> cup.setPercentFull(-1));//remember () -> is a lambda expression

    }

}
