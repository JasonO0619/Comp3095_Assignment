#!/bin/bash

echo "Waiting for PostgreSQL to be ready..."

# Wait until PostgreSQL is ready to accept connections
until pg_isready -h postgres -p 5432 -U "$POSTGRES_USER"; do
  sleep 1
done

# Create databases for Room and User services
echo "Creating databases..."

# Use psql to check if databases exist, and create them if not
psql -U "$POSTGRES_USER" <<-EOSQL
    DO \$$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'room_service_db') THEN
            CREATE DATABASE room_service_db;
        END IF;
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'user_service_db') THEN
            CREATE DATABASE user_service_db;
        END IF;
    END
    \$$;
    GRANT ALL PRIVILEGES ON DATABASE room_service_db TO "$POSTGRES_USER";
    GRANT ALL PRIVILEGES ON DATABASE user_service_db TO "$POSTGRES_USER";
EOSQL
