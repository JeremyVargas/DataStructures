public class Vector <T>{
    // Default size capacity of the vector
    private final int DEFAULT_CAPACITY = 16;

    // Array for implementing vector storage
    private Object[] items;

    // Current vector size
    private int size = 0;

    // Current vector capacity
    private int capacity = 0;

    /**
     * Default constructor.
     */
    public Vector() {
       items = new Object[DEFAULT_CAPACITY];
       capacity = DEFAULT_CAPACITY;
    }

    /**
     * Initializes Vector with provided capacity value.
     * @param initialCapacity The capacity to initialize this vector with.
     * @exception java.lang.IllegalArgumentException
     *            If argument is negative.
     */
    public Vector(int initialCapacity) {
        try {
            items = new Object[initialCapacity];
            capacity = initialCapacity;
        } catch (NegativeArraySizeException e) {
            throw new IllegalArgumentException("Expected a positive initial capacity");
        }
    }

    /**
     * Adds an element to the end of this Vector and return True if successful. If at capacity, resize Vector to double
     * capacity.
     * @param element
     * @return boolean
     */
    public boolean add (T element) {
        // Adds an element and increments the current size
        try {
            items[size] = element;
            this.size++;

            // Resize if needed
            this.resize();

            // Need to reconsider this behavior. May not want to hide an exception here if adding operation goes wrong.
            // Also compiler shouldn't allow this theoretically
        } catch (ArrayStoreException e) {
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            this.resize();
            return false;
        }

        return true;
    }

    /**
     * Inserts element into the provided index and shifts everything at index and greater back by one.
     * @param index
     */
    public void add (int index, T element) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException(String.format("Index must be a positive integer less than %d",
                    this.size()));
        }

        System.arraycopy(this.items, index, this.items, index + 1, this.size() - index);
        this.items[index] = element;
        this.size++;

        // Resize if needed
        this.resize();
    }

    // public boolean remove (<T> element)

    // public <T> remove(int index)

    // public void clear ()

    /**
     * Return the current size of the vector.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Return the current maximum capacity of the vector.
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Return whether the Vector is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the element found at the given index.
     * @param index
     * @return T
     * @exception java.lang.IndexOutOfBoundsException
     *            If argument is negative or greater than the current array size.
     */
    public T get (int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(String.format("Attempted to access out of bounds. Index: %d, Size" +
                    ": %d", index, this.size));
        }

        // Not too happy with this casting, need to see if there's a better way here. Feel like it defeats the whole
        // point of using generics in the first place but I got it working (so far).
        return (T) this.items[index];
    }

    // public int indexOf(<T> element)

    /**
     *  Utility method for resizing when we hit full capacity or one quarter capacity. Copies elements into a new array
     *  of double/one half the previous capacity, respectively.
     */
    private void resize () {
        Object[] newItems;
        if (this.size() >= this.capacity()) {
            newItems = new Object[this.capacity() * 2];
            this.capacity = this.capacity() * 2;
        }

        else if (this.size() <= this.capacity() / 4) {
            newItems = new Object[this.capacity() / 2];
            this.capacity = this.capacity() / 2;
        }

        else {
            return;
        }
        System.arraycopy(this.items, 0, newItems, 0, this.size);
        this.items = newItems;
    }
}