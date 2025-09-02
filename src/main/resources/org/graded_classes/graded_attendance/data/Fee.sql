create TABLE IF not exists "fee_2025"
(
    ed_no TEXT PRIMARY KEY NOT NULL,
    name  TEXT             NOT NULL,
    class TEXT,
    Jan   TEXT,
    Feb   TEXT,
    Mar   TEXT,
    Apr   TEXT,
    May   TEXT,
    Jun   TEXT,
    Jul   TEXT,
    Aug   TEXT,
    Sept  TEXT,
    Oct   TEXT,
    Nov   TEXT,
    Dec   TEXT,
    FOREIGN KEY (ed_no
        ) REFERENCES StudentData (ed_no) ON DELETE CASCADE
);
INSERT INTO "fee_2025" (ed_no, name, class)
SELECT ed_no, name, class
FROM StudentData
WHERE NOT EXISTS (SELECT 1
                  FROM "fee_2025"
                  WHERE "fee_2025".ed_no = StudentData.ed_no
                    AND "fee_2025".name = StudentData.name
                    AND "fee_2025".class = StudentData.class);
-- Trigger: auto-insert into student_profiles when a student is added
CREATE TRIGGER IF not exists insert_fee_profile
    AFTER INSERT
    ON StudentData
    FOR EACH ROW
BEGIN
    INSERT INTO "fee_2025" (ed_no, name, class)
    VALUES (NEW.ed_no, NEW.name, NEW.class);
END;
