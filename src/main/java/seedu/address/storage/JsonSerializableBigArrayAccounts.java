package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.model.DifferentAddressBooks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook with different data that is serializable to JSON format.
 */
@JsonRootName(value = "BigAccount")
public class JsonSerializableBigArrayAccounts {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonSerializableAddressBook> accountsWithData = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBigArrayAccounts(@JsonProperty("accounts") List<JsonSerializableAddressBook> accounts) {
        this.accountsWithData.addAll(accounts);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableBigArrayAccounts(DifferentAddressBooks source) {
        accountsWithData.addAll(source.getList().stream().map(JsonSerializableAddressBook::new).collect(Collectors.toList()));
    }
}
