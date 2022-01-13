package com.inrhythm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginationHelperTest {

    static PaginationHelper helperA;
    static PaginationHelper helperB;
    static PaginationHelper helperC;
    static PaginationHelper helperD;

    @BeforeAll
    public static void setUp() {
        //Creating array list where item count more then items per page
        ArrayList<Character> itemList1 = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
        helperA = new PaginationHelper(itemList1, 4);

        //Creating array list where item count less then items per page
        ArrayList<Character> itemList2 = new ArrayList<>(Arrays.asList('a'));
        helperB = new PaginationHelper(itemList2, 4);

        //Creating array list where item count equals items per page
        ArrayList<Character> itemList3 = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        helperC = new PaginationHelper(itemList3, 3);

        //Creating empty array list where item count equals zero
        ArrayList<Character> itemList4 = new ArrayList<>();
        helperD = new PaginationHelper(itemList4, 3);
    }

    /*
    * Initializing parameters for page count test
    */
    private static Stream<Arguments> pageCountParameters() {
        return Stream.of(
                Arguments.of(helperA, 2),
                Arguments.of(helperB, 1),
                Arguments.of(helperC, 1),
                Arguments.of(helperD, 0)
        );
    }

    /*
     * Test for verifying that items are distributed on the correct amount of pages
     */
    @ParameterizedTest(name = "Page count")
    @MethodSource("pageCountParameters")
    void testPageCount(PaginationHelper helper, int expectedPageCount) {
        assertEquals(expectedPageCount, helper.pageCount(),
                "Total Page Count is " + helper.itemCount() + " and items is " + helper.pageCount());
    }

    /*
     * Initializing parameters for item count test
     */
    private static Stream<Arguments> itemCountParameters() {
        return Stream.of(
                Arguments.of(helperA, 6),
                Arguments.of(helperB, 1),
                Arguments.of(helperC, 3),
                Arguments.of(helperD, 0)

        );
    }

    /*
     * Test for verifying that total items count is correct
     */
    @ParameterizedTest(name = "Item count")
    @MethodSource("itemCountParameters")
    void testItemCount(PaginationHelper helper, int expectedItemCount) {
        assertEquals(expectedItemCount, helper.itemCount(), "Total Item Count is " + helper.itemCount());
    }

    /*
     * Initializing parameters for page item count test by index number
     */
    private static Stream<Arguments> pageItemCountParameters() {
        return Stream.of(
                Arguments.of(0, 4),
                Arguments.of(1, 2),
                Arguments.of(2, -1),
                Arguments.of(-1, -1)
        );
    }

    /*
     * Test for verifying correct amount of items on the page by page index number
     */
    @ParameterizedTest(name = "Page index # returns amount items on the page")
    @MethodSource("pageItemCountParameters")
    void testPageItemCount(int pageIndex, int expectedItemCount) {
        assertEquals(expectedItemCount, helperA.pageItemCount(pageIndex),
                "Total Items on page where Index # " + pageIndex + " is ");
    }

    /*
     * Initializing parameters for page index number test with list A (when Item Count more than Items Per Page)
     */
    private static Stream<Arguments> pageIndexListAParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(3, 0),
                Arguments.of(4, 1),
                Arguments.of(5, 1),
                Arguments.of(-1, -1),
                Arguments.of(7, -1)
        );
    }

    /*
     * Test for verifying correct index of the page by item index number for list A (Item Count more than Items Per Page)
     */
    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count more than Items Per Page}")
    @MethodSource("pageIndexListAParameters")
    void testPageIndexItemCountMoreThenItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperA.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is not right");
    }

    /*
     * Initializing parameters for page index number test with list B (when Item Count less than Items Per Page)
     */
    private static Stream<Arguments> pageIndexListBParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, -1),
                Arguments.of(-1, -1)
        );
    }

    /*
     * Test for verifying correct index of the page by item index number for list B (Item Count less than Items Per Page)
     */
    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count < Items Per Page}")
    @MethodSource("pageIndexListBParameters")
    void testPageIndexItemCountLessThenItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperB.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is not right");
    }

    /*
     * Initializing parameters for page index number test with list C (when Item Count equals Items Per Page)
     */
    private static Stream<Arguments> pageIndexListCParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(2, 0),
                Arguments.of(3, -1),
                Arguments.of(-1, -1)
        );
    }

    /*
     * Test for verifying correct index of the page by item index number for list C (Item Count equals Items Per Page)
     */
    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count = Items Per Page}")
    @MethodSource("pageIndexListCParameters")
    void testPageIndexItemCountEqualItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperC.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is not right");
    }

    /*
     * Initializing parameters for page index number test with list D (when Item Count equals zero or negative, returned result -1)
     */
    private static Stream<Arguments> pageIndexListDParameters() {
        return Stream.of(
                Arguments.of(0, -1),
                Arguments.of(-1, -1)
        );
    }

    /*
     * Test for verifying correct index of the page by item index number for list D (Item Count is zero or negative, returned result -1)
     */
    @ParameterizedTest(name = "Item index # returns -1 when Item Count zero}")
    @MethodSource("pageIndexListDParameters")
    void testPageIndexItemCountZero(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperD.pageIndex(itemIndex),
                "When item index zero/invalid >> Page Index should be -1, with item index # " + itemIndex + " it's ");
    }
}
