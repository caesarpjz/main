package seedu.address.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.account.accountexceptions.AccountDataNotFoundException;
import seedu.address.model.account.accountexceptions.DuplicateAccountDataException;

/**
 * A list of account data that enforces uniqueness between its elements and does not allow nulls.
 * An account data is considered unique by comparing using
 * {@code AccountData#isSameAccountData(AccountData)}. As such, adding and updating of
 * account data uses AccountData#isSameAccountData(AccountData) for equality
 * so as to ensure that the earnings being added or updated is
 * unique in terms of identity in the AccountsDataList. However,
 * the removal of a person uses AccountData#equals(Object) so
 * as to ensure that the account data with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see AccountData#isSameAccountData(AccountData)
 */
public class AccountsDataList implements Iterable<AccountData> {

    private final ObservableList<AccountData> internalList = FXCollections.observableArrayList();
    private final ObservableList<AccountData> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(AccountData toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAccountData);
    }

    /**
     * Adds an account data to the list.
     * The account data must not already exist in the list.
     */
    public void add(AccountData toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAccountDataException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the account data {@code target} in the list with {@code editedAccountData}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedAccountData} must not be the same as another existing person in the list.
     */
    public void setAccountData(AccountData target, AccountData editedAccountData) {
        requireAllNonNull(target, editedAccountData);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AccountDataNotFoundException();
        }

        if (!target.isSameAccountData(editedAccountData) && contains(editedAccountData)) {
            throw new DuplicateAccountDataException();
        }

        internalList.set(index, editedAccountData);
    }

    public void setAccountData(AccountsDataList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code accountData}.
     * {@code accountData} must not contain duplicate account data.
     */
    public void setAccountData(List<AccountData> accountData) {
        requireAllNonNull(accountData);
        if (!accountDataAreUnique(accountData)) {
            throw new DuplicateAccountDataException();
        }

        internalList.setAll(accountData);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(AccountData toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AccountDataNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AccountData> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AccountData> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountsDataList // instanceof handles nulls
                && internalList.equals(((AccountsDataList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean accountDataAreUnique(List<AccountData> accountData) {
        for (int i = 0; i < accountData.size() - 1; i++) {
            for (int j = i + 1; j < accountData.size(); j++) {
                if (accountData.get(i).isSameAccountData(accountData.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
