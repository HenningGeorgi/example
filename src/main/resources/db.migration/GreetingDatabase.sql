CREATE TABLE greeting IF NOT EXISTS (
    id      UUID         PRIMARY KEY,
    name    TEXT,
    age     int,
    vegan   BOOL
);