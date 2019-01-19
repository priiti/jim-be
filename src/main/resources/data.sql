INSERT INTO championship_type (NAME, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ('Raplamaa Meistrivõistlused', sysdate, 1, sysdate, 1);

INSERT INTO competition (NAME, START_DATE, END_DATE, DESCRIPTION, ADDRESS, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ('Järvakandi Igamehemaraton', PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'),
        'Jooksutrass Järvakandi alevis 4.2km ringil. Maraton 10 ringi. Sinu maraton 1, 2, 3, ... või 10 ringi',
        'Järvakandi, Tallinna mnt 43', sysdate, 1, sysdate, 1
);

INSERT INTO competition_distance_type (name, CREATED_AT, UPDATED_AT)
VALUES
  ('Marathon', sysdate, sysdate),
  ('Half-Marathon', sysdate, sysdate),
  ('10k', sysdate, sysdate),
  ('5k', sysdate, sysdate),
  ('None', sysdate, sysdate);

INSERT INTO organizer (NAME, EMAIL, PHONE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (
  'Järvakandi Wellod',
  'jarvakandi.wellod@jim.com',
  '56123456',
  sysdate, 1, sysdate, 1);

INSERT INTO organizer_competition (ORGANIZER_ID, COMPETITION_ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (
  SELECT id FROM organizer WHERE name = 'Järvakandi Wellod',
  SELECT id FROM competition WHERE name = 'Järvakandi Igamehemaraton',
  sysdate, 1, sysdate, 1);

INSERT INTO competition_distance (NAME, LENGTH, CHAMPIONSHIP_TYPE_ID, SPECIAL_NOTES, COMPETITION_ID, START_TIME, START_NUMBERING, DISTANCE_TYPE_ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ('Igamehejooks', NULL, NULL, 'Jookse palju jaksad', (SELECT id
                                                            FROM competition
                                                            WHERE name = 'Järvakandi Igamehemaraton'),
  PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'), 500,
  (SELECT id FROM competition_distance_type WHERE name LIKE 'Marathon')
  , sysdate, 1, sysdate, 1
);

INSERT INTO competition_distance (NAME, LENGTH, CHAMPIONSHIP_TYPE_ID, SPECIAL_NOTES, COMPETITION_ID, START_TIME, START_NUMBERING, DISTANCE_TYPE_ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ('Maraton', 42.2, (SELECT id
                          FROM championship_type
                          WHERE name = 'Raplamaa Meistrivõistlused'), 'Täispikk maraton',
  (SELECT id
   FROM competition
   WHERE name = 'Järvakandi Igamehemaraton'),
  PARSEDATETIME('25.08.2019 11:00:00', 'dd.MM.yyyy hh:mm:ss'), 1,
   (SELECT id FROM competition_distance_type WHERE name LIKE 'Marathon')
  , sysdate, 1, sysdate, 1
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Igamehejooks'),
        PARSEDATETIME('25.10.2018 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        10,
        sysdate, 1, sysdate, 1
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Igamehejooks'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        15,
        sysdate, 1, sysdate, 1
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Maraton'),
        PARSEDATETIME('25.10.2018 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        20,
        sysdate, 1, sysdate, 1
);

INSERT INTO competition_price (COMPETITION_DISTANCE_ID, START_DATE, END_DATE, PRICE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES ((SELECT id
         FROM competition_distance
         WHERE name = 'Maraton'),
        PARSEDATETIME('25.01.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        PARSEDATETIME('25.08.2019 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
        25,
        sysdate, 1, sysdate, 1
);

INSERT INTO competitor (first_name, last_name, email, phone, gender, date_of_birth, sports_club, newsletter_subscription, publish_data, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (
    'Mart', 'Random', 'mart.random@random.ee', '56123456', 'MALE', PARSEDATETIME('11.10.1975 00:00:00', 'dd.MM.yyyy hh:mm:ss'),
  'Raplakad', false, true, sysdate, 1, sysdate, 1
);

INSERT INTO competition_participant (competitor_id, competition_distance_id, participation_count, championship_participation, payment_fulfilled, number_printed, envelope_printed, competitor_number, chip_id, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY)
VALUES (
  (SELECT id FROM competitor WHERE email = 'mart.random@random.ee'),
  (SELECT id FROM competition_distance WHERE name = 'Igamehejooks'),
  1, false, false, false, false, null, null, sysdate, 1, sysdate, 1
);

INSERT INTO authorities (name)
    VALUES ('ROLE_ADMIN'), ('ROLE_ORGANIZER'), ('ROLE_COMPETITOR');
