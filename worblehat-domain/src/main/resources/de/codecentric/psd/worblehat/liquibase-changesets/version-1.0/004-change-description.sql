-- liquibase formatted sql

-- changeset action:add_beschreibung
ALTER TABLE book MODIFY description VARCHAR(4000);