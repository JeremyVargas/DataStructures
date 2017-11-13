import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VectorTest {
    @Rule
    public final ExpectedException exceptionThrown = ExpectedException.none();

    @Test
    public void defaultConstructorTest() {
        // Arrange
        int defaultCapacity = 16;

        // Act
        Vector<String> testVector = new Vector<String>();

        // Assert
        Assert.assertNotNull("Vector was not initialized successfully", testVector);
        Assert.assertEquals("Expected initial Vector size to be empty",0, testVector.size());
        Assert.assertEquals(String.format("Expected default constructor to initialize with capacity of %d",
                defaultCapacity), defaultCapacity, testVector.capacity());
    }

    @Test
    public void validConstructorTest() {
        // Arrange
        int capacity = 32;

        // Act
        Vector<String> testVector = new Vector<String>(capacity);

        // Assert
        Assert.assertNotNull("Vector was not initialized successfully", testVector);
        Assert.assertEquals("Expected initial Vector size to be empty",0, testVector.size());
        Assert.assertEquals(String.format("Expected constructor to initialize with capacity of %d from provided " +
                        "argument", capacity), capacity, testVector.capacity());
    }

    @Test
    public void invalidConstructorTest() {
        // Arrange
        int capacity = - 1;

        // Act
        Vector<String> testVector = new Vector<String>(capacity);

        // Assert
        exceptionThrown.expect(IllegalArgumentException.class);
        exceptionThrown.expectMessage("Expected a positive initial capacity");
    }

    @Test
    public void defaultAddElement() { }

    @Test
    public void defaultAddElementToFullCapacity() {}

    @Test
    public void addElementAtIndex() {}

    @Test
    public void addElementAtIndexFullCapacity() {}

    @Test
    public void removeByElement() {}

    @Test
    public void removeByElementLowCapacity() {}

    @Test
    public void removeByIndex() {}

    @Test
    public void removeByIndexLowCapacity() {}

    @Test
    public void isEmptyTrue() {}

    @Test
    public void isEmptyFalse() {}

    @Test
    public void clearVector(){}

    @Test
    public void getByIndex() {}

    @Test
    public void getByIndexOutOfBounds() {}

    @Test
    public void indexOfFound() {}

    @Test
    public void indexOfNotFound() {}
}
