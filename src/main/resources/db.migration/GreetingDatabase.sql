CREATE TABLE greeting (
    id      UUID         PRIMARY KEY,
    name    TEXT,
    age     int4,
    vegan   BOOL,
    version int4         NOT NULL
);