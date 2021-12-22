package com.inrhythm;

import java.util.List;

/**
 * Class is used for implementation of pagination
 */
public class PaginationHelper<T> {

    private List<T> items;
    private int itemsPerPage;

    /**
     * Constructor for list of items and number of items per page
     * @param items        list of items
     * @param itemsPerPage number of items per page
     */
    public PaginationHelper(List<T> items, int itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Method for page count
     * @return amount of pages based on the constructor
     */
    public int pageCount() {
        int totalPages = itemCount() / itemsPerPage;
        if (itemCount() % itemsPerPage != 0)
            totalPages++;
        return totalPages;
    }

    /**
     * Method for items count
     * @return amount of items in the List
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * Method for counting items on the specific page
     * @param pageNumber index number of page
     * @return items amount on the specified index page
     */
    public int pageItemCount(int pageNumber) {
        if ((pageNumber < 0) || (pageNumber >= pageCount()))
            return -1;
        else if ((pageNumber == pageCount() - 1) && (itemCount() % itemsPerPage != 0))
            return itemCount() % itemsPerPage;
        else
            return itemsPerPage;
    }

    /**
     * Method for identification page index for specific item index
     * @param itemIndex item index
     * @return page index for specified item index
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= itemCount() || pageCount() == 0)
            return -1;
        return itemIndex / itemsPerPage;
    }


}
