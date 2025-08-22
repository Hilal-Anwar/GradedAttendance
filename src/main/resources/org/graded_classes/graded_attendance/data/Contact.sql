CREATE TABLE IF NOT EXISTS "STUDENT_CONTACT"
(
    ed_no       TEXT PRIMARY KEY NOT NULL,
    name        TEXT             NOT NULL,
    class       TEXT,
    telegram_id TEXT UNIQUE,
    FOREIGN KEY (ed_no) REFERENCES StudentData (ed_no) ON DELETE CASCADE
);
INSERT INTO "STUDENT_CONTACT" (ed_no, name, class)
SELECT ed_no, name, class
FROM StudentData
WHERE NOT EXISTS (SELECT 1
                  FROM "STUDENT_CONTACT"
                  WHERE "STUDENT_CONTACT".ed_no = StudentData.ed_no
                    AND "STUDENT_CONTACT".name = StudentData.name
                    AND "STUDENT_CONTACT".class = StudentData.class);


-- Trigger: auto-insert into student_profiles when a student is added
CREATE TRIGGER IF not exists insert_student_contact_profile
    AFTER INSERT
    ON StudentData
    FOR EACH ROW
BEGIN
    INSERT INTO "STUDENT_CONTACT" (ed_no, name, class)
    VALUES (NEW.ed_no, NEW.name, NEW.class);
END;
