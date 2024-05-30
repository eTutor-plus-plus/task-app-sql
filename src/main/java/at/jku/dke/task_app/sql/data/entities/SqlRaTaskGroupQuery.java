package at.jku.dke.task_app.sql.data.entities;

import at.jku.dke.etutor.task_app.data.entities.TaskGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Represents a task group query.
 */
@Entity
@Table(name = "task_group_query")
public class SqlRaTaskGroupQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_group_id", nullable = false)
    private SqlRaTaskGroup taskGroup;

    @Size(max = 255)
    @NotNull
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @NotNull
    @Column(name = "query", nullable = false, length = Integer.MAX_VALUE)
    private String query;

    /**
     * Creates a new instance of class {@link SqlRaTaskGroupQuery}.
     */
    public SqlRaTaskGroupQuery() {
    }

    /**
     * Creates a new instance of class {@link SqlRaTaskGroupQuery}.
     *
     * @param taskGroup The task group.
     * @param tableName The table name.
     * @param query     The query.
     */
    public SqlRaTaskGroupQuery(SqlRaTaskGroup taskGroup, String tableName, String query) {
        this.taskGroup = taskGroup;
        this.tableName = tableName;
        this.query = query;
    }

    /**
     * Gets the id.
     *
     * @return The id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id The id.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the task group.
     *
     * @return The task group.
     */
    public SqlRaTaskGroup getTaskGroup() {
        return taskGroup;
    }

    /**
     * Sets the task group.
     *
     * @param taskGroup The task group.
     */
    public void setTaskGroup(SqlRaTaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    /**
     * Gets the table name.
     *
     * @return The table name.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the table name.
     *
     * @param tableName The table name.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the query.
     *
     * @return The query.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query.
     *
     * @param query The query.
     */
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SqlRaTaskGroupQuery that = (SqlRaTaskGroupQuery) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SqlRaTaskGroupQuery.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("tableName='" + tableName + "'")
            .add("query='" + query + "'")
            .toString();
    }
}
