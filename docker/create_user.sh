#!/bin/bash
set -e
POSTGRES="psql -v ON_ERROR_STOP=1 --username ${POSTGRES_USER} --dbname ${POSTGRES_DB}"

echo "Creating database user: etutor_sql_executor"
$POSTGRES <<-EOSQL
CREATE USER etutor_sql_executor PASSWORD 'etutor_sql_executor_pwd';
EOSQL
