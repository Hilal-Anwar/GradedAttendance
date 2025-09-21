create table if not exists LessonPlanner
(
    class    TEXT NOT NULL PRIMARY KEY ,
    subjects TEXT
);
INSERT OR IGNORE INTO LessonPlanner (class, subjects)
VALUES ('I', 'English,Math,Hindi,Computer,EVS,Gk/IQ'),
       ('II', 'English,Math,Hindi,Computer,EVS,Gk/IQ'),
       ('III', 'English,Math,Hindi,Computer,EVS,Gk/IQ'),
       ('IV', 'English,Math,Hindi,Computer,EVS,Gk/IQ,Social Studies'),
       ('V', 'English,Math,Hindi,Computer,EVS,Gk/IQ,Social Studies'),
       ('VI', 'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies'),
       ('VII', 'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies'),
       ('VIII', 'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies'),
       ('IX', 'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies,Economics,Commerce'),
       ('X', 'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies,Economics,Commerce,Account'),
       ('XI',
        'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies,Economics,Commerce,Account,Business Studies'),
       ('XII',
        'English,Math,Hindi,Computer,Physics,Chemistry,Biology,Gk/IQ,Social Studies,Economics,Commerce,Account,Business Studies');