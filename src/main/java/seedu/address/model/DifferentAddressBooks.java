package seedu.address.model;

import seedu.address.model.addressbooklist.AddressBookList;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps all data at the account-book level
 */
public class DifferentAddressBooks {

    private final List<AddressBookList> listOfDifferentAddressBooks;

    public DifferentAddressBooks() {
        listOfDifferentAddressBooks = new ArrayList<>();
    }


    public void addAccount(AddressBookList a) {
        listOfDifferentAddressBooks.add(a);
    }

    public List<AddressBookList> getList() {
        return listOfDifferentAddressBooks;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountBook // instanceof handles nulls
                && listOfDifferentAddressBooks.equals(((DifferentAddressBooks) other).listOfDifferentAddressBooks));
    }

    @Override
    public int hashCode() {
        return listOfDifferentAddressBooks.hashCode();
    }
}
