# eTutor Task-App: Sql

This application provides a REST-interface for following task type: sql, relalg.

## Development

In development environment, the API documentation is available at http://localhost:8081/docs.

In order to run the application in development environment, the `dev` profile must be activated.
This can be done by setting the environment variable `SPRING_PROFILES_ACTIVE` to `dev` or by setting the profile in the IDE run configuration.

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## Docker

Start a new instance of the application using Docker:

```bash
docker run -p 8090:8081 \ 
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://postgres:5432/etutor_sql" \
  -e SPRING_DATASOURCE_USERNAME=etutor_sql \
  -e SPRING_DATASOURCE_PASSWORD=myPwd \
  -e SPRING_FLYWAY_USER=etutor_sql_admin \
  -e SPRING_FLYWAY_PASSWORD=adPwd \
  -e CLIENTS_API_KEYS_0_NAME=task-administration \
  -e CLIENTS_API_KEYS_0_KEY=some-secret-key \
  -e CLIENTS_API_KEYS_0_ROLES_0=CRUD \
  -e CLIENTS_API_KEYS_0_ROLES_1=SUBMIT \
  -e CLIENTS_API_KEYS_1_NAME=moodle \
  -e CLIENTS_API_KEYS_1_KEY=another-secret-key \
  -e CLIENTS_API_KEYS_1_ROLES_0=SUBMIT \
  -e CLIENTS_API_KEYS_2_NAME=plagiarism-checker \
  -e CLIENTS_API_KEYS_2_KEY=key-for-reading-submissions \
  -e CLIENTS_API_KEYS_2_ROLES_0=READ_SUBMISSION \
  -e SQL_URL=https://etutor.dke.uni-linz.ac.at/api/forwardPublic/sql/query/ \
  -e JDBC_URL="jdbc:postgresql://postgres:5432/etutor_sql_exercises" \
  -e JDBC_ADMIN_USERNAME=etutor_sql_admin \
  -e JDBC_ADMIN_PASSWORD=secret \
  -e JDBC_EXECUTOR_USERNAME=etutor_sql_executor \
  -e JDBC_EXECUTOR_PASSWORD=secret \
  -e JDBC_MAX_POOL_SIZE=10 \
  -e JDBC_MAX_LIFETIME=1800000 \
  -e JDBC_CONNECTION_TIMEOUT=15000 \
  etutorplusplus/task-app-sql
```

or with Docker Compose:

```yaml
version: '3.8'

services:
    task-app-sql:
        image: etutorplusplus/task-app-sql
        restart: unless-stopped
        ports:
            -   target: 8081
                published: 8090
        environment:
            SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/etutor_sql
            SPRING_DATASOURCE_USERNAME: etutor_sql
            SPRING_DATASOURCE_PASSWORD: myPwd
            SPRING_FLYWAY_USER: etutor_sql_admin
            SPRING_FLYWAY_PASSWORD: adPwd
            CLIENTS_API_KEYS_0_NAME: task-administration
            CLIENTS_API_KEYS_0_KEY: some-secret-key
            CLIENTS_API_KEYS_0_ROLES_0: CRUD
            CLIENTS_API_KEYS_0_ROLES_1: SUBMIT
            CLIENTS_API_KEYS_1_NAME: moodle
            CLIENTS_API_KEYS_1_KEY: another-secret-key
            CLIENTS_API_KEYS_1_ROLES_0: SUBMIT
            CLIENTS_API_KEYS_2_NAME: plagiarism-checker
            CLIENTS_API_KEYS_2_KEY: key-for-reading-submissions
            CLIENTS_API_KEYS_2_ROLES_0: READ_SUBMISSION
            SQL_URL: https://etutor.dke.uni-linz.ac.at/api/forwardPublic/sql/query/
            JDBC_URL: jdbc:postgresql://postgres:5432/etutor_sql_exercises
            JDBC_ADMIN_USERNAME: etutor_sql_admin
            JDBC_ADMIN_PASSWORD: secret
            JDBC_EXECUTOR_USERNAME: etutor_sql_executor
            JDBC_EXECUTOR_PASSWORD: secret
            JDBC_MAX_POOL_SIZE: 10
            JDBC_MAX_LIFETIME: 1800000
            JDBC_CONNECTION_TIMEOUT: 15000
```

### Environment Variables

In production environment, the application requires two database users:

* A database administrator user which has the permission to create the tables.
* A JPA user which has read/write access (`SELECT, INSERT, UPDATE, DELETE, TRUNCATE`) to the database tables.

> In development environment, one user will be used for both by default.

The users must be configured via environment variables. The clients have to be configured via environment variables as well (`X`/`Y` stands for a 0-based index).

| Variable                     | Description                                                                                      |
|------------------------------|--------------------------------------------------------------------------------------------------|
| `SERVER_PORT`                | The server port.                                                                                 |
| `SPRING_DATASOURCE_URL`      | JDBC-URL to the database                                                                         |
| `SPRING_DATASOURCE_USERNAME` | The username of the JPA user.                                                                    |
| `SPRING_DATASOURCE_PASSWORD` | The password of the JPA user.                                                                    |
| `SPRING_FLYWAY_USER`         | The username of the database administrator user.                                                 |
| `SPRING_FLYWAY_PASSWORD`     | The password of the database administrator user.                                                 |
| `CLIENTS_API_KEYS_X_NAME`    | The name of the client.                                                                          |
| `CLIENTS_API_KEYS_X_KEY`     | The API key of the client.                                                                       |
| `CLIENTS_API_KEYS_X_ROLES_Y` | The role of the client.                                                                          |
| `SQL_URL`                    | The URL to the public SQL query URL (must end with a slash, id will be appended by application). |
