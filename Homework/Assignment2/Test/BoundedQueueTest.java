import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BoundedQueueTest {
 
    @Test//test every variable to ensure that the constructor initializes them correctly
    public void testConstructorValid() {
        BoundedQueue q = new BoundedQueue(3);
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());
        assertEquals("[]", q.toString());
    }

    @Test//test the exception to make sure it is thrown properly
    public void testConstructorNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-1);
        });
    }

    @Test//test the enQueue method to make sure it adds elements correctly
    public void testEnQueue() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());
        assertEquals("[A]", q.toString());
    }

    @Test//test the exception to make sure it is thrown properly
    public void testEnQueueNull() {
        BoundedQueue q = new BoundedQueue(2);
        assertThrows(NullPointerException.class, () -> {
            q.enQueue(null);
        });
    }

    @Test//test the exception to make sure it is thrown properly
    public void testEnQueueFull() {
        BoundedQueue q = new BoundedQueue(1);
        q.enQueue("A");
        assertTrue(q.isFull());
        assertThrows(IllegalStateException.class, () -> {
            q.enQueue("B");
        });
    }

    @Test//test the deQueue method to make sure it removes elements correctly
    public void testDeQueue() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        q.enQueue("B");

        Object result = q.deQueue();
        assertEquals("A", result);
        assertEquals("[B]", q.toString());//this needs brackets
        assertFalse(q.isEmpty());
    }

    @Test//test the exception to make sure it is thrown properly
    public void testDeQueueEmpty() {
        BoundedQueue q = new BoundedQueue(2);
        assertThrows(IllegalStateException.class, () -> {
            q.deQueue();
        });
    }

    @Test//test to ensure that the queue maintains FIFO order
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

    @Test//test to ensure that the queue can wrap around correctly when elements are added and removed
    public void testEnQueueAfterDeQueue() {
        BoundedQueue q = new BoundedQueue(3);

        q.enQueue("A");
        q.enQueue("B");
        q.enQueue("C");//after all three enqueues, the queue should be [A, B, C]

        assertEquals("A", q.deQueue());
        assertEquals("B", q.deQueue());//queue should now be [C]

        q.enQueue("D");
        q.enQueue("E");//queue should now be [C, D, E]

        assertTrue(q.isFull());
        assertEquals("[C, D, E]", q.toString());
    }

    @Test//test to ensure that the isEmpty and isFull methods return the correct values at different stages of the queue's state
    public void testIsEmptyAndIsFull() {
        BoundedQueue q = new BoundedQueue(3);
        
        //queue should be [ ]
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("A");//queue should be [A]
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("B");//queue should be [A, B]
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());

        q.deQueue();//queue should be [B]
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("C");//queue should be [B, C]
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());

        q.enQueue("D");//queue should be [B, C, D]
        assertFalse(q.isEmpty());
        assertTrue(q.isFull());

        q.deQueue();//queue should be [C, D]
        assertFalse(q.isEmpty());
        assertFalse(q.isFull());
    }

    @Test//test to make sure the string representation is correct
    public void testToStringEmpty() {
        BoundedQueue q = new BoundedQueue(3);
        assertEquals("[]", q.toString());
    }

    @Test//test to make sure the string representation when partially completed is correct
    public void testToStringPartial() {
        BoundedQueue q = new BoundedQueue(3);
        q.enQueue("A");
        q.enQueue("B");
        assertEquals("[A, B]", q.toString());
    }

    @Test//test to make sure the string representation after a dequeue is correct
    public void testToStringAfterDeQueue() {
        BoundedQueue q = new BoundedQueue(2);
        q.enQueue("A");
        q.enQueue("B");
        q.deQueue();
        q.enQueue("C");

        assertEquals("[B, C]", q.toString());
    }
}
