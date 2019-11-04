package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.AddressBook;
import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.note.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.task.Task;
import seedu.address.storage.commands.JsonAdaptedCommand;
import seedu.address.storage.earnings.JsonAdaptedEarnings;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link AddressBook}.
 */
public class JsonAdaptedAddressBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

//    private final Person persons;
//    private final Earnings earning;
//    private final Task tasks;
//    private final CommandObject commands;
//    private final Reminder reminders;
//    private final Notes notes;

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedEarnings> earning = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedCommand> commands = new ArrayList<>();
    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();
    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonAdaptedAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("earning") List<JsonAdaptedEarnings> earning,
                                       @JsonProperty("commands") List<JsonAdaptedCommand> commands,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("reminders") List<JsonAdaptedReminder> reminders,
                                       @JsonProperty("notes") List<JsonAdaptedNote> notes) {

        this.persons.addAll(persons);
        this.earning.addAll(earning);
        this.commands.addAll(commands);
        this.tasks.addAll(tasks);
        this.reminders.addAll(reminders);
        this.notes.addAll(notes);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedAddressBook(AddressBook source) {
        persons = source.getPersonList();
        picture = source.getPicture().value;
        classId = source.getClassId().value;
        attendance = source.getAttendance().value;
        result = source.getResult().value;
        participation = source.getParticipation().value;
    }
}
