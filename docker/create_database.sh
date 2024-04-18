#!/bin/bash
set -e
POSTGRES="psql -v ON_ERROR_STOP=1 --username ${POSTGRES_USER} --dbname ${POSTGRES_DB}"

echo "Creating database: etutor_sql_exercises"
$POSTGRES <<-EOSQL
CREATE DATABASE etutor_sql_exercises WITH OWNER etutor_sql;
EOSQL
