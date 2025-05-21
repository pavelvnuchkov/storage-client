-- changeSet pavel:1
CREATE TABLE contact (
id BIGSERIAL PRIMARY KEY,
phone VARCHAR(255),
email VARCHAR(255));

CREATE TABLE client (
client_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255),
last_name varchar(255),
contact_id BIGINT,
FOREIGN KEY (contact_id) REFERENCES contact(id));
