CREATE TABLE  IF NOT EXISTS "%s"
(
    UID      VARCHAR(512) UNIQUE NOT NULL,
    NAME     VARCHAR(512),
    CLASS    VARCHAR(50),
    CHECK_IN VARCHAR(120),
    CHECK_OUT VARCHAR(120)
);
create TABLE IF not exists "%s"
(
    UID      VARCHAR(512),
    NAME     VARCHAR(512),
    CLASS    VARCHAR(50)
);