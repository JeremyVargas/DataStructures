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
    public void removeByElementFound() {
        // Arrange
        Vector<String> testVector = new Vector<String>();
        String element = "element";
        testVector.add(element);

        // Act
        boolean successfullyRemoved = testVector.remove(element);

        // Assert
        Assert.assertTrue("Vector did not return successful element removal", successfullyRemoved);
        Assert.assertEquals("Vector size should have been decremented after element removal",
                0, testVector.size());
    }

    @Test
    public void removeByElementNotFound() {
        // Arrange
        Vector<String> testVector = new Vector<String>();
        String element = "element";
        testVector.add(element);

        // Act
        boolean successfullyRemoved = testVector.remove("Not present");

        // Assert
        Assert.assertFalse("Vector did not return unsuccessful element removal", successfullyRemoved);
        Assert.assertEquals("Vector size should have remained unchanged", 1, testVector.size());
        Assert.assertEquals("Element was unexpectedly removed from Vector", element, testVector.get(0));
    }

    @Test
    public void removeByElementLowCapacity() {
        // Arrange
        int initialCapacity = 4;
        Vector<String> testVector = new Vector<String> (initialCapacity);
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        String element4 = "element4";
        testVector.add(element1);
        testVector.add(element2);
        testVector.add(element3);
        testVector.add(element4);

        // Act
        boolean successfullyRemoved1 = testVector.remove(element1);
        boolean successfullyRemoved2 = testVector.remove(element2);
        boolean successfullyRemoved3 = testVector.remove(element3);

        // Assert
        Assert.assertTrue("Vector did not return successful element removal (#1)", successfullyRemoved1);
        Assert.assertTrue("Vector did not return successful element removal (#2)", successfullyRemoved2);
        Assert.assertTrue("Vector did not return successful element removal (#3)", successfullyRemoved3);
        Assert.assertEquals("Vector size should have been decremented after element removals",
                1, testVector.size());
        Assert.assertEquals("Vector should have been resized to half after element removals",
                initialCapacity / 2, testVector.capacity());
    }

    @Test
    public void removeByIndexFront() {
        // Arrange
        int initialSize = 3;
        Vector<String> testVector = new Vector<String> ();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        testVector.add(element1);
        testVector.add(element2);
        testVector.add(element3);

        // Act
        String result = testVector.remove(0);

        // Assert
        Assert.assertEquals("Vector did not return the correct element after removal", element1, result);
        Assert.assertEquals("Vector size should have been decremented after element removal",
                initialSize - 1, testVector.size());
    }

    @Test
    public void removeByIndexEnd() {
        // Arrange
        int initialSize = 3;
        Vector<String> testVector = new Vector<String> ();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        testVector.add(element1);
        testVector.add(element2);
        testVector.add(element3);

        // Act
        String result = testVector.remove(2);

        // Assert
        Assert.assertEquals("Vector did not return the correct element after removal", element3, result);
        Assert.assertEquals("Vector size should have been decremented after element removal",
                initialSize - 1, testVector.size());
    }

    @Test
    public void removeByIndexException() {
        // Arrange
        int initialSize = 3;
        Vector<String> testVector = new Vector<String> ();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        testVector.add(element1);
        testVector.add(element2);
        testVector.add(element3);

        // Expect
        exceptionThrown.expect(IndexOutOfBoundsException.class);

        // Act
        testVector.remove(4);
    }

    @Test
    public void removeByIndexLowCapacity() {

        // Arrange
        int initialSize = 3;
        int initialCapacity = initialSize + 1;
        Vector<String> testVector = new Vector<String> (initialCapacity);
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        testVector.add(element1);
        testVector.add(element2);
        testVector.add(element3);

        // Act
        String resultEnd = testVector.remove(2);
        String resultMid = testVector.remove(1);

        // Assert
        Assert.assertEquals("Vector did not return the correct element after removing element at the end",
                element3, resultEnd);
        Assert.assertEquals("Vector did not return the correct element after removing middle element",
                element2, resultMid);
        Assert.assertEquals("Vector should have been decremented twice after element removal",
                initialSize - 2, testVector.size());
        Assert.assertEquals("Vector capacity should have been reduced to half after element removals",
                initialCapacity / 2, testVector.capacity());
    }

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
