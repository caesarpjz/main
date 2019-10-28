package seedu.address.storage;

import static seedu.address.logic.commands.AddEarningsCommand.MESSAGE_DUPLICATE_EARNINGS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.account.AccountData;
import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.note.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.storage.commands.JsonAdaptedCommand;
import seedu.address.storage.earnings.JsonAdaptedEarnings;


/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_COMMAND = "Commands list contains duplicate command(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_NOTE = "Notes list contains duplicate note(s)";
    public static final String MESSAGE_DUPLICATE_ACCOUNT_DATA = "Account Data list contains duplicate account data(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedEarnings> earning = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedCommand> commands = new ArrayList<>();
    private final List<JsonAdaptedNote> notes = new ArrayList<>();
    private final List<JsonAdaptedAccountsData> accountsData = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("earning") List<JsonAdaptedEarnings> earning,
                                       @JsonProperty("commands") List<JsonAdaptedCommand> commands,
                                        @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("notes") List<JsonAdaptedNote> notes,
                                       @JsonProperty("accountsData") List<JsonAdaptedAccountsData> accountsData) {
        this.persons.addAll(persons);
        this.earning.addAll(earning);
        this.commands.addAll(commands);
        this.tasks.addAll(tasks);
        this.notes.addAll(notes);
        this.accountsData.addAll(accountsData);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        earning.addAll(source.getEarningsList().stream().map(JsonAdaptedEarnings::new).collect(Collectors.toList()));
        commands.addAll(source.getCommandsList().stream().map(JsonAdaptedCommand::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        notes.addAll(source.getNotesList().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
//        accountsData.addAll(source.getAccountDataList().stream()
//                .map(JsonAdaptedAccountsData::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedEarnings jsonAdaptedEarnings : earning) {
            Earnings earnings = jsonAdaptedEarnings.toModelType();
            if (addressBook.hasEarnings(earnings)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EARNINGS);
            }
            addressBook.addEarnings(earnings);
        }

        for (JsonAdaptedCommand jsonAdaptedCommand : commands) {
            CommandObject command = jsonAdaptedCommand.toModelType();
            if (addressBook.hasCommand(command)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMMAND);
            }
            addressBook.addCommand(command);
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            addressBook.addTask(task);

        }

        for (JsonAdaptedNote jsonAdaptedNotes : notes) {
            Notes notes = jsonAdaptedNotes.toModelType();
            if (addressBook.hasNotes(notes)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            addressBook.addNotes(notes);

        }

//        for (JsonAdaptedAccountsData jsonAdaptedAccountsData : accountsData) {
//            AccountData accountData = jsonAdaptedAccountsData.toModelType();
//            if (addressBook.hasAccountData(accountData)) {
//                throw new IllegalValueException(MESSAGE_DUPLICATE_ACCOUNT_DATA);
//            }
//            addressBook.addAccountData(accountData);
//        }
        return addressBook;
    }

}
