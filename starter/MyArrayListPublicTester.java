/**
 * This file contains all the public tests(visible on Gradescope)
 * Use this as a guide to write tests to verify your MyArrayList implementation 
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class creates a test fixture and runs multiple tests on 
 * your implementation for MyArrayList.  
 * 
 * Instance variables:
 * nullArray - Object array that will be used to set up the test fixture
 * intArray - Integer array that will be used to set up the test fixture
 */
public class MyArrayListPublicTester {

    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] nullArray = new Object[10];
    Integer[] intArray = { 1, 2, 3 };
    Integer[] size1Array = {1, null, null}; // NOTE: LIST OF SIZE ONE

    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
            listCustomCapacity, listWithNull, listWithInt;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listNonEmpty = new MyArrayList<>(size1Array);
        listNonEmpty.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(nullArray);
        listWithInt = new MyArrayList<Integer>(intArray);
    }

    // ======================== Public Tests ========================

    /**
     * Tests Constructor when no arguements are given during instantiation
     */
    @Test
    public void testCtor() {
        assertEquals("Check size for default constructor", 0, listEmpty.size);
        assertArrayEquals("Check data", new Integer[5], listEmpty.data);

        assertEquals("Check size for the constructor with size argument",
                0, listCustomCapacity.size);
        assertArrayEquals("Check data", new Integer[3], 
                listCustomCapacity.data);

        assertEquals("Check size for the constructor with list argument",
                3, listWithInt.size);
        assertArrayEquals("Check data", 
                new Integer[]{1, 2, 3}, listWithInt.data);
    }

    /**
     * Tests Append method when MyArrayList is not empty
     */
    @Test
    public void testAppendNonEmpty() {
        listNonEmpty.append(2);

        assertArrayEquals("Check for successful append", 
                new Integer[]{1, 2, null}, listNonEmpty.data);
        assertEquals("Check list size after the append", 2, listNonEmpty.size);
    }

    /**
     * Tests Append method when MyArrayList is empty
     */
    @Test
    public void testAppendEmpty() {
        listDefaultCap.append(5);

        assertArrayEquals("Check for successful append", 
        new Integer[]{5, null, null, null, null}, listDefaultCap.data);
        assertEquals("Check list size after the append",
                1, listDefaultCap.size);
    }

    /**
     * Tests Prepend method when MyArrayList is empty
     */
    @Test
    public void testPrependEmpty() {
        listDefaultCap.prepend(5);

        assertArrayEquals("Check for successful prepend", 
        new Integer[]{5, null, null, null, null}, listDefaultCap.data);
        assertEquals("Check list size after the prepend",
                1, listDefaultCap.size);
    }

    /**
     * Tests Prepend method when MyArrayList is not empty
     */
    @Test
    public void testPrependNonEmpty() {
        listNonEmpty.prepend(2);

        assertArrayEquals("Check for successful prepend", 
        new Integer[]{2, 1, null}, listNonEmpty.data);
        assertEquals("Check list size after the prepend", 2, listNonEmpty.size);
    }

    /**
     * Tests Insert method when an element is inserted at the end
     */
    @Test
    public void testInsert() {
        listWithInt.insert(0, 10);
        listDefaultCap.insert(0, 10);

        assertArrayEquals("check data", 
        new Integer[]{10, 1, 2, 3, null, null}, listWithInt.data);
        assertEquals("should increment size", 4, listWithInt.size);

        assertArrayEquals("check data", 
        new Integer[]{10, null, null, null, null}, listDefaultCap.data);
        assertEquals("should increment size", 1, listDefaultCap.size);
    }

    /**
     * Tests Remove method when an element is removed from the end
     */
    @Test
    public void testRemove() {
        listWithInt.remove(2);
        assertArrayEquals("check data", new Integer[]{1, 2, null}, 
                listWithInt.data);
        assertEquals("check size after removing an element", 2, 
                listWithInt.size);
    }

    /**
     * Tests Set method when the previous element is a non-null integer
     */
    @Test
    public void testSetFromNonNull() {
        assertEquals("return value of set is correct",
                2, listWithInt.set(1, 4));
        assertArrayEquals("element is set correctly",
                new Integer[]{1, 4, 3}, listWithInt.data);
        assertEquals("size should not get incremented", 3, listWithInt.size);
    }

    /**
     * Tests Set method when the previous element is null
     */
    @Test
    public void testSetFromNull() {
        listWithNull.size = 3;
        assertNull("return value of set is correct", 
                listWithNull.set(2, 4));
        assertArrayEquals("element is set correctly",
                new Integer[]{null, null, 4, null, null, null, null, null,
                null, null}, listWithNull.data);
        assertEquals("size should not get incremented", 3, listWithNull.size);
    }

    /**
     * Tests Set method when the value being set to is null
     */
    @Test
    public void testSetToNull() {
        assertEquals("return value of set is correct",
                3, listWithInt.set(2, null));
        assertArrayEquals("element is set correctly",
                new Integer[]{1, 2, null}, listWithInt.data);
        assertEquals("size should not get incremented",
                3, listWithInt.size);
    }

    /**
     * Tests Get method to return the correct value
     */
    @Test
    public void testGetNonNull() {
        assertEquals("should get 3 from the list", 3, listWithInt.get(2));
        assertEquals("size is not changed", 3, listWithInt.size);
        assertArrayEquals("data array not modified",
                new Integer[]{1, 2, 3}, listWithInt.data);

        assertEquals("Should get 2 from the list", 2, listWithInt.get(1));
        assertEquals("size is not changed", 3, listWithInt.size);
        assertArrayEquals("data array not modified",
                new Integer[]{1, 2, 3}, listWithInt.data);

        assertEquals("Should get 1 from the list", 1, listWithInt.get(0));
        assertEquals("size is not changed", 3, listWithInt.size);
        assertArrayEquals("data array not modified",
                new Integer[]{1, 2, 3}, listWithInt.data);
    }

    /**
     * Tests Get method when element is null
     */
    @Test
    public void testGetNull() {
        listWithNull.size = 3;
        assertNull("Should get null from the list", listWithNull.get(1));
        assertEquals("size is not changed", 3, listWithNull.size);
        assertArrayEquals("data array unchanged",
                new Integer[10], listWithNull.data);
    }

    /**
     * Tests Size method to return the correct size of MyArrayList
     */
    @Test
    public void testSize() {
        assertEquals("Check size with non-empty list",
                0, listDefaultCap.size());
        assertArrayEquals("data array not changed",
                new Integer[5], listDefaultCap.data); 

        assertEquals("Check size with empty list",
                0, listCustomCapacity.size());
        assertArrayEquals("data array not changed",
                new Integer[3], listCustomCapacity.data);

        assertEquals("Check size with non-empty list", 3, listWithInt.size());
        assertArrayEquals("data array not changed",
                new Integer[]{1, 2, 3}, listWithInt.data);
    }


    /**
     * Tests expandCapacity method when required capacity
     * is current capacity + 1
     */
    @Test
    public void testExpandCapacityTwice() {
        listWithInt.expandCapacity(4);
        assertArrayEquals("Capacity should update", 
                new Integer[]{1, 2, 3, null, null, null}, listWithInt.data);
        assertEquals("The size should still be the same", 3, listWithInt.size);

        listWithInt.expandCapacity(12);
        assertArrayEquals("Second capacity expand", 
                new Integer[]{1, 2, 3, null, null, null, null, null, null, null, null, null}, 
                listWithInt.data);
        assertEquals("The size should still be the same", 3, listWithInt.size);
    }

    /**
     * Tests if the capacity is explanded to less than 5
     * it is reset to the default capacity
     */
    @Test
    public void testExpandCapacityReset() {
        MyArrayList<String> list = new MyArrayList<>();
        String[] origArray = {};
        int origSize = 0;
        list.data = origArray;
        list.expandCapacity(origSize + 1);
        assertArrayEquals("Capacity should be updated", 
                new Integer[]{null, null, null, null, null}, list.data);
        assertEquals("The size should still be the same", origSize, list.size);

        list.expandCapacity(6);
        assertArrayEquals("Capacity should be updated again", 
                new Integer[]{null, null, null, null, null, null, null, null, null, null},
                list.data);
        assertEquals("The size should still be the same", origSize, list.size);
    }

    /**
     * Tests getCapacity method to return the correct capacity
     */
    @Test
    public void testGetCapacity() {
        Integer[] largeIntArr = new Integer[]{10,9,8,7,6,5,4};
        MyArrayList<Integer> listWithIntLarge = new MyArrayList<>(largeIntArr);

        MyArrayList<Integer> zeroCapList = new MyArrayList<>(0);

        assertEquals("getCapacity should return the size instance variable "
                + "data", 10, listWithNull.getCapacity());
        assertArrayEquals("check data", new Integer[10], listWithNull.data);
        assertEquals("getCapacity should return the size instance variable "
                + "data", 3, listCustomCapacity.getCapacity());
        assertArrayEquals("check data", 
                new Integer[3], listCustomCapacity.data);
        assertEquals("getCapacity should return the size instance variable "
                + "data", 5, listDefaultCap.getCapacity());
        assertArrayEquals("check data", new Integer[5], listDefaultCap.data);
        assertEquals("getCapacity should return the size instance variable "
                + "data", 7, listWithIntLarge.getCapacity());
        assertArrayEquals("check data", 
                new Integer[]{10,9,8,7,6,5,4}, listWithIntLarge.data);
        assertEquals("getCapacity should return the size instance variable "
                + "data", 0, zeroCapList.getCapacity());
        assertArrayEquals("check data", new Integer[0], zeroCapList.data);
    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is in the range [0, size - 1]
     */
    @Test
    public void testRotateSingle() {
        listNonEmpty.rotate(0);
        assertArrayEquals("check data", new Integer[]{1, null, null}, listNonEmpty.data);
        assertEquals("size should not get incremented", 1, listNonEmpty.size);

    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is in the range [0, size - 1]
     */
    @Test
    public void testRotateFullList() {
        listWithInt.rotate(0);
        assertArrayEquals("check data", new Integer[]{1, 2, 3}, listWithInt.data);
        assertEquals("size should not get incremented", 3, listWithInt.size);

        listWithInt.rotate(1);
        assertArrayEquals("check data", new Integer[]{3, 1, 2}, listWithInt.data);
        assertEquals("size should not get incremented", 3, listWithInt.size);

        listWithInt.rotate(2);
        assertArrayEquals("check data", new Integer[]{1, 2, 3}, listWithInt.data);
        assertEquals("size should not get incremented", 3, listWithInt.size);
    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is in the range [0, size - 1]
     */
    @Test
    public void testRotatePartialNullList() {
        Integer[] arrPartialNull = new Integer[]{null, 1, 2, null, null, 3, null, null};
        MyArrayList<Integer> listPartialNull = new MyArrayList<>(arrPartialNull);
        listPartialNull.size = 6;

        listPartialNull.rotate(1);
        assertArrayEquals("check data", new Integer[]{3, null, 1, 2, null, null, null, null}, listPartialNull.data);
        assertEquals("size should not get incremented", 6, listPartialNull.size);

        listPartialNull.rotate(3);
        assertArrayEquals("check data", new Integer[]{2, null, null, 3, null, 1, null, null}, listPartialNull.data);
        assertEquals("size should not get incremented", 6, listPartialNull.size);

        listPartialNull.rotate(5);
        assertArrayEquals("check data", new Integer[]{null, null, 3, null, 1, 2, null, null}, listPartialNull.data);
        assertEquals("size should not get incremented", 6, listPartialNull.size);
    }
	
    /**
     * Aims to test the find method when 
     * input element exists in the list with index in the range [0, size - 1]
     */
    @Test
    public void testFind() {
        Integer[] intArr = new Integer[]{1, 2, 3};
        MyArrayList<Integer> list = new MyArrayList<>();
		list.data = intArr;
		list.size = 3;
	    
        int index = list.find(2);
        assertEquals("Verify correct index", 1, index);
        index = list.find(3);
        assertEquals("Verify correct index", 2, index);
	    
        assertEquals("The capacity should stay the same", 3, list.data.length);
        assertEquals("The size should stay the same", 3, list.size);
    }
}
