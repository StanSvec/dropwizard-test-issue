package util;

import com.fasterxml.jackson.annotation.JsonCreator;

public record IPAddress(String value) {

    public static IPAddress localhost() {
        return new IPAddress("127.0.0.1");
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static IPAddress fromString(String value) {
        return new IPAddress(value);
    }
}
