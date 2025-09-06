CREATE TABLE IF NOT EXISTS "TimeTable%s"
(
    Day VARCHAR(512) PRIMARY KEY,
    [3:00 PM] VARCHAR(512),
    [4:00 PM] VARCHAR(512),
    [5:00 PM] VARCHAR(512),
    [6:00 PM] VARCHAR(512),
    [7:00 PM] VARCHAR(512),
    [8:00 PM] VARCHAR(512)
);
INSERT OR IGNORE INTO "TimeTable%s" (Day) VALUES
                                              ('Mon'),
                                              ('Tue'),
                                              ('Wed'),
                                              ('Thu'),
                                              ('Fri'),
                                              ('Sat'),
                                              ('Sun');


