CREATE TABLE IF NOT EXISTS "StudentData"
(
    ed_no TEXT PRIMARY KEY NOT NULL ,
    date_of_add TEXT,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    bloodGroup TEXT,
    guardian_phone TEXT,
    aadhaar_no TEXT,
    father_name TEXT,
    mother_name TEXT,
    class TEXT,
    gender TEXT,
    dob TEXT,
    address TEXT,
    father_occ TEXT,
    mother_occ TEXT,
    previous_ins_name TEXT,
    reason_leaving TEXT,
    school_n TEXT,
    suggestions TEXT,
    subjects TEXT,
    telegram_id TEXT
);
