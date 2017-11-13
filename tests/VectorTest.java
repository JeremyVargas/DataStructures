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

        // Expect
        exceptionThrown.expect(IllegalArgumentException.class);
        exceptionThrown.expectMessage("Expected a positive initial capacity");

        // Act
        Vector<String> testVector = new Vector<String>(capacity);
    }

    @Test
    public void defaultAddElementToEmpty() {
        // Arrange
        String element = "insert me";
        Vector<String> testVector = new Vector<String>();

        // Act
        boolean result = testVector.add(element);

        // Assert
        Assert.assertTrue("Vector did not return successful add", result);
        Assert.assertEquals("Expected Vector size of 1 after adding an element", 1, testVector.size());
        Assert.assertEquals(String.format("Expected: %s . Got: %s", element, testVector.get(0)), element,
                testVector.get(0));
    }

    @Test
    public void defaultAddElementToFullCapacity() {
        // Arrange
        int expectedSize = 7;
        boolean allElementsAdded = true;
        Vector<Integer> testVector = new Vector<Integer>(expectedSize);


        // Act
        for (int i = 0; i < expectedSize; i++) {
            allElementsAdded = allElementsAdded && testVector.add(i);
        }

        // Assert
        Assert.assertTrue("Vector did not return all successful adds", allElementsAdded);
        Assert.assertEquals(String.format("Expected Vector size of %d after adding elements", expectedSize),
                expectedSize, testVector.size());
        Assert.assertEquals(String.format("Expected Vector capacity to double to %d", expectedSize * 2),
                expectedSize * 2, testVector.capacity());
    }

    @Test
    public void addElementAtIndex() {
        // Arrange
        int expectedSize = 7;
        int indexToAddAt = 2;
        int element = 1000;
        Vector<Integer> testVector = new Vector<Integer>();
        for (int i = 0; i < expectedSize - 1; i++) {
            testVector.add(i);
        }

        int oldElementAtIndex = testVector.get(indexToAddAt);

        // Act
        testVector.add(indexToAddAt, element);

        // Assert
        Assert.assertEquals(String.format("Expected Vector size %d. Got: %d", expectedSize, testVector.size()),
                expectedSize, testVector.size());
        Assert.assertEquals(String.format("Expected: %d . Got: %d", element, testVector.get(indexToAddAt)), element,
                (int) testVector.get(indexToAddAt));
        Assert.assertEquals(String.format("Expected: %d . Got: %d", oldElementAtIndex,
                testVector.get(indexToAddAt + 1)), oldElementAtIndex, (int) testVector.get(indexToAddAt + 1));
    }

    @Test
    public void addElementAtIndexFront() {
        // Arrange
        int expectedSize = 4;
        int indexToAddAt = 0;
        Vector<String> testVector = new Vector<String>();
        String s1 = "one";
        String s2 = "two";
        String s3 = "three";
        String s4 = "four";

        testVector.add(s1);
        testVector.add(s2);
        testVector.add(s3);

        // Act
        testVector.add(indexToAddAt, s4);

        // Assert
        Assert.assertEquals(String.format("Expected Vector size %d. Got: %d", expectedSize, testVector.size()),
                expectedSize, testVector.size());
        Assert.assertEquals(String.format("Expected: %s . Got: %s", s4, testVector.get(indexToAddAt)), s4,
                testVector.get(indexToAddAt));
        Assert.assertEquals(String.format("Expected: %s to be shifted right by 1. Got: %s", s1,
                testVector.get(indexToAddAt + 1)), s1,
                testVector.get(indexToAddAt + 1));
        Assert.assertEquals(String.format("Expected: %s to be shifted right by 1. Got: %s", s2,
                testVector.get(indexToAddAt + 2)), s2,
                testVector.get(indexToAddAt + 2));
        Assert.assertEquals(String.format("Expected: %s to be shifted right by 1. Got: %s", s3,
                testVector.get(indexToAddAt + 3)), s3,
                testVector.get(indexToAddAt + 3));
    }

    @Test
    public void addElementAtIndexFullCapacity() {
        // Arrange
        int expectedSize = 4;
        int indexToAddAt = 2;
        int element = 1000;
        Vector<Integer> testVector = new Vector<Integer>(expectedSize);
        for (int i = 0; i < expectedSize - 1; i++) {
            testVector.add(i);
        }

        int oldElementAtIndex = testVector.get(indexToAddAt);

        // Act
        testVector.add(indexToAddAt, element);

        // Assert
        Assert.assertEquals(expectedSize * 2, testVector.capacity());
        Assert.assertEquals(String.format("Expected Vector size %d. Got: %d", expectedSize, testVector.size()),
                expectedSize, testVector.size());
        Assert.assertEquals(String.format("Expected: %d . Got: %d", element, testVector.get(indexToAddAt)), element,
                (int) testVector.get(indexToAddAt));
        Assert.assertEquals(String.format("Expected: %d . Got: %d", oldElementAtIndex,
                testVector.get(indexToAddAt + 1)), oldElementAtIndex, (int) testVector.get(indexToAddAt + 1));
    }

    @Test
    public void addElementByIndexException() {
        // Arrange
        int index = 1;
        String element = "element";
        Vector<String> testVector = new Vector<String>();

        // Expect
        exceptionThrown.expect(IndexOutOfBoundsException.class);

        // Act
        testVector.add(index, element);
    }

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
