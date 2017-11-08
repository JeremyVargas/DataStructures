import java.util.*
;
public class Vector <T>{
    // Default size capacity of the vector
    private final int DEFAULT_CAPACITY = 16;

    // Array for implementing vector storage
    private Object[] items;

    // Current vector size
    private int size = 0;

    /**
     * Default constructor.
     */
    public Vector() {
       items = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Initializes Vector with provided capacity value.
     * @param initialCapacity The capacity to intialize this vector with.
     * @exception java.lang.IllegalArgumentException
     *            If argument is negative.
     */
    public Vector(int initialCapacity) {
        try {
            items = new Object[initialCapacity];
        } catch (NegativeArraySizeException e) {
            throw new IllegalArgumentException("Expected a positive initial capacity");
        }
    }

    // public boolean add (<T> element)

    // public void add (int index, <T> element)

    // public boolean remove (<T> element)

    // public <T> remove(int index)

    // public void clear ()

    // public int size ()

    // public int capacity ()

    // public boolean isEmpty()

    // public <T> get (int index)

    // public int indexOf(<T> element)

    // private void resize (int newCapacity)
}