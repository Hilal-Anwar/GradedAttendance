create TABLE IF not exists "%s"
(
    UID    VARCHAR(512) UNIQUE NOT NULL,
    NAME   VARCHAR(512),
    CLASS  VARCHAR(50),
    AMOUNT integer,
    MODE   TEXT,
    REC_BY TEXT
);