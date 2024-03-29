package ru;

public enum Status {
    ACCEPTED,
    LOADING,
    COMPLETED,
    STACKED,
    SUCCESS,
    ERROR;

    public static Status fromString(String text) {
        if (text == null)
            return ERROR;
        for (Status s : Status.values()) {
            if (s.toString().equalsIgnoreCase(text)) {
                return s;
            }
        }
        return ERROR;
    }
}
