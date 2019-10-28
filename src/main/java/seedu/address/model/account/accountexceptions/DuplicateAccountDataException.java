package seedu.address.model.account.accountexceptions;

/**
 * Signals that the operation will result in duplicate account data
 * (Account data are considered duplicates if they have the same
 * persons, earnings, commands, task, reminder and notes).
 */
public class DuplicateAccountDataException extends RuntimeException {
    public DuplicateAccountDataException() {
        super("Operation would result in duplicate account data");
    }
}
