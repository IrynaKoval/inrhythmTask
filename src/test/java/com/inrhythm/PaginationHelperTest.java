package com.inrhythm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginationHelperTest {

    static PaginationHelper helperA;
    static PaginationHelper helperB;
    static PaginationHelper helperC;
    static PaginationHelper helperD;

    @BeforeAll
    public static void setUp() {
        //Item Count > Items Per Page
        ArrayList<Character> itemList1 = new ArrayList<>();
        itemList1.add('a');
        itemList1.add('b');
        itemList1.add('c');
        itemList1.add('d');
        itemList1.add('e');
        itemList1.add('f');
        helperA = new PaginationHelper(itemList1, 4);

        //Item Count < Items Per Page
        ArrayList<Character> itemList2 = new ArrayList<>();
        itemList2.add('a');
        helperB = new PaginationHelper(itemList2, 4);

        //Item Count = Items Per Page
        ArrayList<Character> itemList3 = new ArrayList<>();
        itemList3.add('a');
        itemList3.add('b');
        itemList3.add('x');
        helperC = new PaginationHelper(itemList3, 3);

        //Item Count Zero
        ArrayList<Character> itemList4 = new ArrayList<>();
        helperD = new PaginationHelper(itemList4, 3);
    }

    private static Stream<Arguments> pageCountParameters() {
        return Stream.of(
                Arguments.of(helperA, 2),
                Arguments.of(helperB, 1),
                Arguments.of(helperC, 1),
                Arguments.of(helperD, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("pageCountParameters")
    void testPageCount(PaginationHelper helper, int expectedPageCount) {
        assertEquals(expectedPageCount, helper.pageCount(),
                "Total Page Count is " + helper.itemCount() + " and items is " + helper.pageCount());
    }


    private static Stream<Arguments> itemCountParameters() {
        return Stream.of(
                Arguments.of(helperA, 6),
                Arguments.of(helperB, 1),
                Arguments.of(helperC, 3),
                Arguments.of(helperD, 0)

        );
    }

    @ParameterizedTest
    @MethodSource("itemCountParameters")
    void testItemCount(PaginationHelper helper, int expectedItemCount) {
        assertEquals(expectedItemCount, helper.itemCount(), "Total Item Count is " + helper.itemCount());
    }

    private static Stream<Arguments> pageItemCountParameters() {
        return Stream.of(
                Arguments.of(0, 4),
                Arguments.of(1, 2),
                Arguments.of(2, -1),
                Arguments.of(-1, -1)
        );
    }

    @ParameterizedTest(name = "Page index # returns amount items on the page")
    @MethodSource("pageItemCountParameters")
    void testPageItemCount(int pageIndex, int expectedItemCount) {
        assertEquals(expectedItemCount, helperA.pageItemCount(pageIndex),
                "Total Items on page where Index # " + pageIndex + " is ");
    }

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

    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count > Items Per Page}")
    @MethodSource("pageIndexListAParameters")
    void testPageIndexItemCountMoreThenItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperA.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is");
    }

    private static Stream<Arguments> pageIndexListBParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, -1),
                Arguments.of(-1, -1)
        );
    }

    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count < Items Per Page}")
    @MethodSource("pageIndexListBParameters")
    void testPageIndexItemCountLessThenItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperB.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is ");
    }

    private static Stream<Arguments> pageIndexListCParameters() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(2, 0),
                Arguments.of(3, -1),
                Arguments.of(-1, -1)
        );
    }

    @ParameterizedTest(name = "Item index # returns page index of the page when Item Count = Items Per Page}")
    @MethodSource("pageIndexListCParameters")
    void testPageIndexItemCountEqualItemsPerPage(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperC.pageIndex(itemIndex),
                "When item index: " + itemIndex + ", Page Index of the page is");
    }

    private static Stream<Arguments> pageIndexListDParameters() {
        return Stream.of(
                Arguments.of(0, -1),
                Arguments.of(-1, -1)
        );
    }

    @ParameterizedTest(name = "Item index # returns -1 when Item Count zero}")
    @MethodSource("pageIndexListDParameters")
    void testPageIndexItemCountZero(int itemIndex, int pageIndex) {
        assertEquals(pageIndex, helperD.pageIndex(itemIndex),
                "When item index zero/invalid >> Page Index should be -1, with item index # " + itemIndex + " it's ");
    }
}
