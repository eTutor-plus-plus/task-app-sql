package at.jku.dke.task_app.sql.data.repositories;

import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroupQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for entity {@link SqlRaTaskGroupQuery}.
 */
public interface SqlRaTaskGroupQueryRepository extends JpaRepository<SqlRaTaskGroupQuery, UUID> {
    /**
     * Finds all {@link SqlRaTaskGroupQuery}s by the specified task group.
     *
     * @param id The task group identifier.
     * @return The {@link SqlRaTaskGroupQuery}s.
     */
    List<SqlRaTaskGroupQuery> findByTaskGroup_Id(Long id);

    /**
     * Finds a {@link SqlRaTaskGroupQuery} by its table name and task group identifier.
     *
     * @param tableName The table name.
     * @param id        The task group identifier.
     * @return The {@link SqlRaTaskGroupQuery} if found.
     */
    @Query("select t from SqlRaTaskGroupQuery t where upper(t.tableName) = upper(?1) and t.taskGroup.id = ?2")
    Optional<SqlRaTaskGroupQuery> findByTableNameIgnoreCaseAndTaskGroup_Id(String tableName, Long id);

    /**
     * Deletes all {@link SqlRaTaskGroupQuery}s by the specified task group and not in the specified collection of identifiers.
     *
     * @param taskGroup The task group.
     * @param ids       The identifiers.
     * @return The number of deleted {@link SqlRaTaskGroupQuery}s.
     */
    long deleteByTaskGroupAndIdNotIn(SqlRaTaskGroup taskGroup, Collection<UUID> ids);
}
