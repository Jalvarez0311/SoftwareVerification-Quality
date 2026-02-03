import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CupTest {
    @Test
    void getPercentFull(){
        Cup c = new Cup(liquidType: "orange juice", percentFull: 85.5);
        assertEquals(85.5, c.getPercentFull());
    }

    @Test
    void getLiquidType(){
        Cup c = new Cup(liquidType: "orange juice", percentFull: 85.5);
        assertEquals(85.5, c.getPercentFull(), 0.001);
    }
}
