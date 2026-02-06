import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BoundedQueueTest {

    //the case that 
    @Test
    public void testConstructorValidCapacity() {
        BoundedQueue q = new BoundedQueue(3);
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());
        assertEquals("[]", q.toString());
    }

    @Test
    public void testConstructorNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-1);
        });
    }

    @Test
    public void testEnQueueNormal() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());
        assertEquals("[A]", q.toString());
    }

    @Test
    public void testEnQueueNull() {
        BoundedQueue q = new BoundedQueue(2);
        assertThrows(NullPointerException.class, () -> {
            q.enQueue(null);
        });
    }

    @Test
    public void testEnQueueFull() {
        BoundedQueue q = new BoundedQueue(1);
        q.enQueue("A");
        assertTrue(q.isFull());
        assertThrows(IllegalStateException.class, () -> {
            q.enQueue("B");
        });
    }

    @Test
    public void testDeQueueNormal() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        q.enQueue("B");

        Object result = q.deQueue();
        assertEquals("A", result);
        assertEquals("[B]", q.toString());
        assertFalse(q.isEmpty());
    }

    @Test
    public void testDeQueueEmpty() {
        BoundedQueue q = new BoundedQueue(2);
        assertThrows(IllegalStateException.class, () -> {
            q.deQueue();
        });
    }

    @Test
    public void testFIFOOrder() {
        BoundedQueue q = new BoundedQueue(3);
        q.enQueue("A");
        q.enQueue("B");
        q.enQueue("C");

        assertEquals("A", q.deQueue());
        assertEquals("B", q.deQueue());
        assertEquals("C", q.deQueue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testWrapAroundEnQueueDeQueue() {
        BoundedQueue q = new BoundedQueue(3);

        q.enQueue("A");
        q.enQueue("B");
        q.enQueue("C");

        assertEquals("A", q.deQueue());
        assertEquals("B", q.deQueue());

        q.enQueue("D");
        q.enQueue("E");

        assertTrue(q.isFull());
        assertEquals("[C, D, E]", q.toString());
    }

    @Test
    public void testIsEmptyAndIsFull() {
        BoundedQueue q = new BoundedQueue(2);

        assertTrue(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("A");
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("B");
        assertFalse(q.isEmpty());
        assertTrue(q.isFull());

        q.deQueue();
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());
    }

    @Test
    public void testToStringEmpty() {
        BoundedQueue q = new BoundedQueue(3);
        assertEquals("[]", q.toString());
    }

    @Test
    public void testToStringPartial() {
        BoundedQueue q = new BoundedQueue(3);
        q.enQueue("A");
        q.enQueue("B");
        assertEquals("[A, B]", q.toString());
    }

    @Test
    public void testToStringAfterWrapAround() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        q.enQueue("B");
        q.deQueue();
        q.enQueue("C");

        assertEquals("[B, C]", q.toString());
    }
}
