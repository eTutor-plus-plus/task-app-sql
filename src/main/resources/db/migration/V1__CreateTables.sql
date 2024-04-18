CREATE TYPE task_status AS ENUM ('draft', 'ready_for_approval', 'approved');
CREATE TYPE submission_mode AS ENUM ('run', 'diagnose', 'submit');
CREATE TYPE task_type AS ENUM ('sql', 'relalg');

CREATE CAST (CHARACTER VARYING as task_type) WITH INOUT AS IMPLICIT;
CREATE CAST (CHARACTER VARYING as task_status) WITH INOUT AS IMPLICIT;
CREATE CAST (CHARACTER VARYING as submission_mode) WITH INOUT AS IMPLICIT;

CREATE TABLE task_group
(
    id                      BIGINT       NOT NULL,
    status                  TASK_STATUS  NOT NULL,
    -- Definition
    ddl_statements          TEXT         NOT NULL,
    diagnose_dml_statements TEXT         NOT NULL,
    submit_dml_statements   TEXT         NOT NULL,
    schema_description      JSONB,
    -- Database
    schema_name             VARCHAR(200) NOT NULL,
    CONSTRAINT task_group_pk PRIMARY KEY (id)
);

CREATE TABLE task_group_query
(
    id            UUID         NOT NULL DEFAULT gen_random_uuid(),
    task_group_id BIGINT       NOT NULL,
    table_name    VARCHAR(255) NOT NULL,
    query         TEXT         NOT NULL,
    CONSTRAINT task_group_query_pk PRIMARY KEY (id),
    CONSTRAINT task_group_query_task_group_fk FOREIGN KEY (task_group_id) REFERENCES task_group (id)
        ON DELETE CASCADE
);

CREATE TABLE task
(
    id                          BIGINT        NOT NULL,
    max_points                  NUMERIC(7, 2) NOT NULL,
    status                      TASK_STATUS   NOT NULL,
    task_group_id               BIGINT        NOT NULL,
    type                        TASK_TYPE     NOT NULL,
    solution                    TEXT          NOT NULL,
    relalg_solution             TEXT,
    wrong_order_penalty         NUMERIC(5, 2) NOT NULL DEFAULT -1, -- -1 = solution is fully invalid
    superfluous_columns_penalty NUMERIC(5, 2) NOT NULL DEFAULT -1,
    CONSTRAINT task_pk PRIMARY KEY (id),
    CONSTRAINT task_task_group_fk FOREIGN KEY (task_group_id) REFERENCES task_group (id)
        ON DELETE CASCADE,
    CONSTRAINT task_wrong_order_penalty_ck CHECK (wrong_order_penalty >= -1),
    CONSTRAINT task_superfluous_columns_penalty_ck CHECK (superfluous_columns_penalty >= -1),
    CONSTRAINT task_relalg_solution_ck CHECK ((type = 'sql' AND relalg_solution IS NULL) OR (type = 'relalg' AND relalg_solution IS NOT NULL))
-- TODO: wrong column naming penalty (check table names)
-- TODO: wrong/missing having clause
);

CREATE TABLE submission
(
    id                UUID                     DEFAULT gen_random_uuid(),
    user_id           VARCHAR(255),
    assignment_id     VARCHAR(255),
    task_id           BIGINT,
    submission_time   TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    language          VARCHAR(2)      NOT NULL DEFAULT 'en',
    mode              submission_mode NOT NULL,
    feedback_level    INT             NOT NULL,
    evaluation_result JSONB,
    submission        TEXT,
    CONSTRAINT submission_pk PRIMARY KEY (id),
    CONSTRAINT submission_task_fk FOREIGN KEY (task_id) REFERENCES task (id)
        ON DELETE CASCADE
);
