package at.jku.dke.task_app.sql.data.converters;

import at.jku.dke.task_app.sql.data.entities.TaskType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * Concerts the database {@code task_type} enum to the java {@link TaskType} enum and to/from string.
 */
@Converter
public class TaskTypeConverter implements AttributeConverter<TaskType, String> {
    /**
     * Creates a new instance of class {@linkplain TaskTypeConverter}.
     */
    public TaskTypeConverter() {
    }

    @Override
    public String convertToDatabaseColumn(TaskType type) {
        if (type == null)
            return null;
        return type.name().toLowerCase();
    }

    @Override
    public TaskType convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;

        return Stream.of(TaskType.values())
            .filter(g -> g.name().toLowerCase().equals(dbData))
            .findAny().orElseThrow(IllegalArgumentException::new);
    }
}
