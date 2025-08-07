CREATE TABLE IF NOT EXISTS "STUDENT_CONTACT"
(
    ed_no      INTEGER UNIQUE NOT NULL,
    telegram_id VARCHAR(512)   NOT NULL,
    name      VARCHAR(512),
    class     VARCHAR(50)
);