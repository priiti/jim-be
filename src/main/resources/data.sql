INSERT INTO championship_type (NAME, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ('Raplamaa Meistrivõistlused', sysdate, 'SYSTEM', sysdate, 'SYSTEM');

INSERT INTO competition (NAME, START_DATE, END_DATE, DESCRIPTION, ADDRESS, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ('Järvakandi Igamehemaraton', PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'),
        'Jooksutrass Järvakandi alevis 4.2km ringil. Maraton 10 ringi. Sinu maraton 1, 2, 3, ... vöi 10 ringi',
        'Järvakandi, Tallinna mnt 43', sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO organizer (NAME, EMAIL, PHONE, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES (
  'Järvakandi Wellod',
  'jarvakandi.wellod@jim.com',
  '56123456',
  sysdate, 'SYSTEM', sysdate, 'SYSTEM');

INSERT INTO organizer_competition (ORGANIZER_ID, COMPETITION_ID, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES (
  SELECT id FROM organizer WHERE name = 'Järvakandi Wellod',
  SELECT id FROM competition WHERE name = 'Järvakandi Igamehemaraton',
  sysdate, 'SYSTEM', sysdate, 'SYSTEM');

INSERT INTO competition_distance (NAME, LENGTH, CHAMPIONSHIP_TYPE_ID, SPECIAL_NOTES, COMPETITION_ID, START_TIME, START_NUMBERING, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ('Igamehejooks', NULL, NULL, 'Jookse palju jaksad', (SELECT id
                                                            FROM competition
                                                            WHERE name = 'Järvakandi Igamehemaraton'),
  PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'), 1, sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO competition_distance (NAME, LENGTH, CHAMPIONSHIP_TYPE_ID, SPECIAL_NOTES, COMPETITION_ID, START_TIME, START_NUMBERING, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ('Maraton', 42.2, (SELECT id
                          FROM championship_type
                          WHERE name = 'Raplamaa Meistrivõistlused'), 'Täispikk maraton',
  (SELECT id
   FROM competition
   WHERE name = 'Järvakandi Igamehemaraton'), PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'),
  500, sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Igamehejooks'),
        PARSEDATETIME('25.10.2018 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        10,
        sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Igamehejooks'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        15,
        sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Maraton'),
        PARSEDATETIME('25.10.2018 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        20,
        sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED, CREATED_BY, MODIFIED, MODIFIED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Maraton'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        25,
        sysdate, 'SYSTEM', sysdate, 'SYSTEM'
);
