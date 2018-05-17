-- liquibase formatted sql

-- changeset action:add_beschreibung
ALTER TABLE book ADD COLUMN description VARCHAR(255);