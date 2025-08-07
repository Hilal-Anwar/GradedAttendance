create TABLE IF not exists "%s"
(
    ed_no    VARCHAR(512) UNIQUE NOT NULL,
    name   VARCHAR(512),
    class  VARCHAR(50),
    amount integer,
    mode   TEXT,
    rec_by TEXT
);