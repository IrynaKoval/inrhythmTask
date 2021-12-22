# Pagination Helper

## PaginationHelper class 

Class that is used for implementation of pagination UI components and set of Unit tests to properly test it.
The class contains constructor and methods.

Contractor:
PaginationHelper - takes a list of items and number of items per page

Methods:

- pageCount - return amount of pages based on the constructor
- itemCount - return amount of items in the List
- pageItemCount - return items amount on the specified index page
- pageIndex - return page index for specified item index 


## PaginationHelperTest 
Unit tests for each method with the following set up:

Item Count > Items Per Page (default)

Item Count < Items Per Page (if needed)

Item Count = Items Per Page (if needed)

Item Count Zero (if needed)


- pageCount - returns right total numbers of pages based on the constructor
- itemCount - returns right amount of items in the List
- pageItemCount - returns right items amount on the specified index page. If the page index invalid >> return -1.
- pageIndex - returns right index of the page for the specified item index. If the page index invalid or negative >> return -1.

