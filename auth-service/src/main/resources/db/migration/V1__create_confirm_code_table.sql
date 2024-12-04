CREATE TABLE registration_code (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    code VARCHAR(255),
    expire_date TIMESTAMP
);
