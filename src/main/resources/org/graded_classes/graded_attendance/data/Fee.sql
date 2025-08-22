create TABLE IF not exists "%s"
(
    ed_no TEXT PRIMARY KEY NOT NULL ,
    name TEXT NOT NULL,
    class TEXT,
    amount  integer,
    due_amt integer,
    mode    TEXT,
    rec_by  TEXT,
    FOREIGN KEY (ed_no) REFERENCES StudentData (ed_no) ON DELETE CASCADE
);
INSERT INTO "%s" (ed_no, name, class)
SELECT ed_no, name, class
FROM StudentData
WHERE NOT EXISTS (
    SELECT 1 FROM "%s"
    WHERE "%s".ed_no = StudentData.ed_no
      AND "%s".name = StudentData.name
      AND "%s".class = StudentData.class
);
-- Trigger: auto-insert into student_profiles when a student is added
CREATE TRIGGER IF not exists insert_fee_profile
    AFTER INSERT
    ON StudentData
    FOR EACH ROW
BEGIN
    INSERT INTO "%s" (ed_no, name, class)
    VALUES (NEW.ed_no, NEW.name, NEW.class);
END;
