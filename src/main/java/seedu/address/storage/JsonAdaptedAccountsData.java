package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.AccountData;
import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.note.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link AccountData}.
 */
public class JsonAdaptedAccountsData {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Account Data's %s field is missing!";

    private final Person persons;
    private final Earnings earning;
    private final CommandObject commands;
    private final Task task;
    private final Reminder reminder;
    private final Notes notes;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAccountsData(@JsonProperty("persons") Person persons, @JsonProperty("earning") Earnings earning,
                               @JsonProperty("commands") CommandObject commands, @JsonProperty("task") Task task,
                                   @JsonProperty("reminder") Reminder reminder, @JsonProperty("notes") Notes notes) {
        this.persons = persons;
        this.earning = earning;
        this.commands = commands;
        this.task = task;
        this.reminder = reminder;
        this.notes = notes;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedAccountsData(AccountData source) {
        persons = source.getPersons();
        earning = source.getEarning();
        commands = source.getCommands();
        task = source.getTask();
        reminder = source.getReminder();
        notes = source.getNotes();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public AccountData toModelType() throws IllegalValueException {

        if (persons == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        /*if (!Person.isValidPerson(persons)) {
            throw new IllegalValueException(Person.MESSAGE_CONSTRAINTS);
        }*/
        final Person modelPerson = persons;

        if (earning == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Earnings.class.getSimpleName()));
        }
        /*if (!ClassId.isValidClassId(classId)) {
            throw new IllegalValueException(ClassId.MESSAGE_CONSTRAINTS);
        }*/
        final Earnings modelEarnings = earning;

        if (commands == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CommandObject.class.getSimpleName()));
        }
        /*if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }*/
        final CommandObject modelCommandObject = commands;

        if (task == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.class.getSimpleName()));
        }
        /*if (!Task.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }*/
        final Task modelTask = task;

        if (reminder == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Reminder.class.getSimpleName()));
        }
        final Reminder modelReminder = reminder;

        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Notes.class.getSimpleName()));
        }
        final Notes modelNotes = notes;

        return new AccountData(modelPerson, modelEarnings, modelCommandObject, modelTask, modelReminder, modelNotes);
    }
}
