package redmine.model.role;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TimeEntriesVisibility {
    ALL("Все трудозатраты"),
    OWN("Только собственные трудозатраты");

    public final String description;

    public static TimeEntriesVisibility of(String description) {
        return Stream.of(values())
                .filter(it -> it.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нет такого TimeEntriesVisibility с описанием " + description));
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
