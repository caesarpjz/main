package seedu.address.model.account;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.note.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.task.Task;

/**
 * Represents a User Account's data.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AccountData {

    private Person persons;
    private Earnings earning;
    private CommandObject commands;
    private Task task;
    private Reminder reminder;
    private Notes notes;

    public AccountData(Person persons, Earnings earning,
                       CommandObject commands, Task task, Reminder reminder, Notes notes) {
        requireAllNonNull(persons, earning, commands, task, reminder, notes);

        this.persons = persons;
        this.earning = earning;
        this.commands = commands;
        this.task = task;
        this.reminder = reminder;
        this.notes = notes;
    }

    public Person getPersons() {
        return persons;
    }

    public Earnings getEarning() {
        return earning;
    }

    public CommandObject getCommands() {
        return commands;
    }

    public Task getTask() {
        return task;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public Notes getNotes() {
        return notes;
    }

    /**
     * Returns true if both earnings of the same date and classId have an identity field that is the same.
     * This defines a weaker notion of equality between two earnings.
     */
    public boolean isSameAccountData(AccountData otherAccountData) {
        if (otherAccountData == this) {
            return true;
        }

        return otherAccountData != null
                && otherAccountData.getPersons().equals(getPersons())
                && otherAccountData.getEarning().equals(getEarning())
                && otherAccountData.getCommands().equals(getCommands())
                && otherAccountData.getTask().equals(getTask())
                && otherAccountData.getReminder().equals(getReminder())
                && otherAccountData.getNotes().equals(getNotes());
    }

    /**
     * Returns true if both account data have the same identity and data fields.
     * This defines a stronger notion of equality between two account data.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AccountData)) {
            return false;
        }

        AccountData otherAccountData = (AccountData) other;
        return otherAccountData.getPersons().equals(getPersons())
                && otherAccountData.getEarning().equals(getEarning())
                && otherAccountData.getCommands().equals(getCommands())
                && otherAccountData.getTask().equals(getTask())
                && otherAccountData.getReminder().equals(getReminder())
                && otherAccountData.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons, earning, commands, task, reminder, notes);
    }

}
