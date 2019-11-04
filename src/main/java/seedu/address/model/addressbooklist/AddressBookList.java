package seedu.address.model.addressbooklist;

import seedu.address.model.AccountBook;
import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.note.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.task.Task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a list of addressbook .
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AddressBookList {

    // Identity fields
    private Person person;
    private Earnings earnings;
    private CommandObject commands;
    private Task tasks;
    private Reminder reminders;
    private Notes notes;

    /**
     * Every field must be present and not null.
     */
    public AddressBookList(Person person, Earnings earnings, CommandObject commands, Task tasks, Reminder reminders, Notes notes) {
        requireAllNonNull(person, earnings, commands, tasks, reminders, notes);
        this.person = person;
        this.earnings = earnings;
        this.commands = commands;
        this.tasks = tasks;
        this.reminders = reminders;
        this.notes = notes;
    }

    public Person getPerson() {
        return person;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public CommandObject getCommands() {
        return commands;
    }

    public Task getTasks() {
        return tasks;
    }

    public Reminder getReminders() {
        return reminders;
    }

    public Notes getNotes() {
        return notes;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBookList)) {
            return false;
        }

        AddressBookList otherAddressBookList = (AddressBookList) other;
        return otherAddressBookList.getPerson().equals(getPerson())
                && otherAddressBookList.getEarnings().equals(getEarnings())
                && otherAddressBookList.getCommands().equals(getCommands())
                && otherAddressBookList.getTasks().equals(getTasks())
                && otherAddressBookList.getReminders().equals(getReminders())
                && otherAddressBookList.getNotes().equals(getNotes());
    }
}
