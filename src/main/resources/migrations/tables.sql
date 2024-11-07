DROP TABLE IF EXISTS teams_users CASCADE;

DROP TABLE IF EXISTS timeline CASCADE;

DROP TABLE IF EXISTS commissions CASCADE;

DROP TABLE IF EXISTS competitions_teams CASCADE;

DROP TABLE IF EXISTS competitions CASCADE;

DROP TABLE IF EXISTS points CASCADE;

DROP TABLE IF EXISTS campus CASCADE;

DROP TABLE IF EXISTS presentations CASCADE;

DROP TABLE IF EXISTS teams CASCADE;

DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    "order"               INTEGER   DEFAULT NULL,
    "date"				  TIMESTAMP NOT NULL,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   DEFAULT NULL,
    traditional_dance_3   VARCHAR   DEFAULT NULL,
    exit_choreography     VARCHAR   NULL,
    birivas_dances_1      VARCHAR   NULL,
    birivas_dances_2      VARCHAR   NULL,
    birivas_dances_3      VARCHAR   NULL,
    deleted               BOOLEAN   DEFAULT FALSE,
    created               TIMESTAMP DEFAULT NOW(),
    created_by            BIGINT    NULL,
    updated               TIMESTAMP NULL,
    updated_by            BIGINT    NULL
    );


CREATE TABLE IF NOT EXISTS campus
(
    id               BIGSERIAL PRIMARY KEY,
    institution      VARCHAR      NOT NULL,
    coordinator_name VARCHAR(100) NOT NULL,
    description      VARCHAR      NOT NULL,
    entity           VARCHAR      NOT NULL,
    dormitory        VARCHAR      NOT NULL,
    id_presentation  BIGINT       NOT NULL,
    deleted          BOOLEAN   DEFAULT FALSE,
    created          TIMESTAMP DEFAULT NOW(),
    created_by       BIGINT       NULL,
    updated          TIMESTAMP    NULL,
    updated_by       BIGINT       NULL,
    CONSTRAINT fk_presentation FOREIGN KEY (id_presentation) REFERENCES presentations (id)
    );


CREATE TABLE IF NOT EXISTS users
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR     NOT NULL,
    cpf          VARCHAR(11) NOT NULL,
    avatar		 VARCHAR DEFAULT NULL,
    registration VARCHAR     NULL,
    password     VARCHAR     NOT NULL,
    roles        VARCHAR     NOT NULL,
    is_evaluator BOOLEAN   DEFAULT FALSE,
    is_admin     BOOLEAN   DEFAULT FALSE,
    id_campus    BIGINT      NOT NULL,
    deleted      BOOLEAN   DEFAULT FALSE,
    created      TIMESTAMP DEFAULT NOW(),
    created_by   BIGINT      NULL,
    updated      TIMESTAMP   NULL,
    updated_by   BIGINT      NULL,
    CONSTRAINT fk_campus FOREIGN KEY (id_campus) REFERENCES campus (id)
    );


CREATE TABLE IF NOT EXISTS points
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR   NOT NULL,
    type       VARCHAR   NOT NULL,
    description  VARCHAR   NOT NULL,
    icon       VARCHAR   NOT NULL,
    size 	   INTEGER	 NOT NULL,
    latitude   VARCHAR   NOT NULL,
    longitude  VARCHAR   NOT NULL,
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL
    );

CREATE TABLE IF NOT EXISTS competitions
(
    id           BIGSERIAL 	  PRIMARY KEY,
    name         VARCHAR      NOT NULL,
    modality     VARCHAR      NOT NULL,
    description  VARCHAR      NOT NULL,
    fecult_event BOOLEAN      DEFAULT FALSE,
    initial_date TIMESTAMP(3) NULL,
    participants INTEGER      NOT NULL,
    commission   BOOLEAN      DEFAULT FALSE,
    id_point     BIGINT       NOT NULL,
    deleted      BOOLEAN   	  DEFAULT FALSE,
    created      TIMESTAMP 	  DEFAULT NOW(),
    created_by   BIGINT		  NULL,
    updated      TIMESTAMP 	  NULL,
    updated_by   BIGINT    	  NULL,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id)
    );

CREATE TABLE IF NOT EXISTS teams
(
    id         BIGSERIAL PRIMARY KEY,
    grade      NUMERIC(10, 2),
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL
    );

CREATE TABLE IF NOT EXISTS teams_users
(
    id         BIGSERIAL PRIMARY KEY,
    id_user    BIGINT    NOT NULL,
    id_team    BIGINT    NOT NULL,
    deleted    BOOLEAN   DEFAULT FALSE,
    created    TIMESTAMP DEFAULT NOW(),
    created_by BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id)
    );

CREATE TABLE IF NOT EXISTS competitions_teams
(
    id             BIGSERIAL PRIMARY KEY,
    id_team        BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
    );

CREATE TABLE IF NOT EXISTS commissions
(
    id                    BIGSERIAL PRIMARY KEY,
    id_competitions_teams BIGINT                     NOT NULL,
    id_user               BIGINT                     NULL,
    grade_1               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_2               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_3               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_4               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_5               NUMERIC(10, 2) DEFAULT 0.0 NULL,
    grade_name_1		  VARCHAR 					 NOT NULL,
    grade_name_2		  VARCHAR 					 NOT NULL,
    grade_name_3		  VARCHAR 					 NOT NULL,
    grade_name_4		  VARCHAR 					 NOT NULL,
    grade_name_5		  VARCHAR 					 NULL,
    deleted               BOOLEAN        DEFAULT FALSE,
    created               TIMESTAMP      DEFAULT NOW(),
    created_by            BIGINT                     NULL,
    updated               TIMESTAMP                  NULL,
    updated_by            BIGINT                     NULL,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_competitions_teams FOREIGN KEY (id_competitions_teams) REFERENCES competitions_teams (id)
    );

CREATE TABLE IF NOT EXISTS timeline
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR   NOT NULL,
    required       BOOLEAN   DEFAULT FALSE,
    date           TIMESTAMP NOT NULL,
    type           VARCHAR   NOT NULL,  -- Adicionada a nova coluna aqui
    id_point       BIGINT    NOT NULL,
    id_competition BIGINT    NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
    );


ALTER SEQUENCE public.campus_id_seq RESTART WITH 1;
ALTER SEQUENCE public.commissions_id_seq RESTART WITH 1;
ALTER SEQUENCE public.competitions_id_seq RESTART WITH 1;
ALTER SEQUENCE public.competitions_teams_id_seq RESTART WITH 1;
ALTER SEQUENCE public.points_id_seq RESTART WITH 1;
ALTER SEQUENCE public.presentations_id_seq RESTART WITH 1;
ALTER SEQUENCE public.teams_id_seq RESTART WITH 1;
ALTER SEQUENCE public.teams_users_id_seq RESTART WITH 1;
ALTER SEQUENCE public.timeline_id_seq RESTART WITH 1;
ALTER SEQUENCE public.users_id_seq RESTART WITH 1;


-- Tabela teams
INSERT INTO teams (
    "grade", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (95.5, FALSE, NOW(), 1, NULL, NULL),
      (88.0, FALSE, NOW(), 2, NULL, NULL),
      (92.3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela teams_users
INSERT INTO teams_users (
    "id_user", "id_team", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (1, 1, FALSE, NOW(), 1, NULL, NULL),
      (2, 1, FALSE, NOW(), 1, NULL, NULL),
      (3, 2, FALSE, NOW(), 1, NULL, NULL),
      (4, 2, FALSE, NOW(), 1, NULL, NULL);

-- Tabela competitions_teams
INSERT INTO competitions_teams (
    id_team,
    id_competition,
    created_by,
    updated_by
) VALUES
      (1, 1, NULL, NULL),
      (1, 2, NULL, NULL),
      (1, 3, NULL, NULL),
      (1, 4, NULL, NULL),
      (1, 5, NULL, NULL),
      (2, 8, NULL, NULL);

-- Tabela commission
INSERT INTO commissions (
    id_competitions_teams,
    id_user,
    grade_1,
    grade_2,
    grade_3,
    grade_4,
    grade_5,
    grade_name_1,
    grade_name_2,
    grade_name_3,
    grade_name_4,
    grade_name_5,
    created_by,
    updated_by
) VALUES
      (1, 1, null, null, null, null, NULL, 'Technical', 'Creativity', 'Execution', 'Originality', NULL, 7, 7),

      (2, NULL , null, null, null, null, NULL, 'adS', 'ASD', 'adsd', 'asd', 'asd', 7, NULL),

      (2, 3, 7.5, 8.5, 9.0, 9.5, 8.0, 'Technical', 'Creativity', 'Execution', 'Teamwork', 'Originality', 7, 7);

-- Tabela timeline
INSERT INTO timeline (
    "name", "required", "date", "id_point", "id_competition", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Abertura oficial do 30º Encontrão', TRUE, '2024-11-15 00:09:00', 1, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Timeline Event B', TRUE, '2024-07-25 00:00:00', 2, 2, FALSE, NOW(), 2, NULL, NULL),
      ('Timeline Event C', FALSE, '2024-07-26 00:00:00', 3, 3, FALSE, NOW(), 3, NULL, NULL);













SELECT * FROM users u WHERE u.id_campus = 2 ORDER BY name;

SELECT * FROM points p WHERE type = 'Local';







SELECT * FROM teams;


SELECT t.*
FROM timeline t
WHERE t.required = true;

SELECT DISTINCT t.*, c.name AS competition_name
FROM timeline t
         JOIN competitions c ON t.id_competition = c.id
         JOIN competitions_teams ct ON c.id = ct.id_competition
         JOIN teams_users tu ON ct.id_team = tu.id_team
WHERE tu.id_user = 1;

SELECT DISTINCT c.id, c.name
FROM competitions c
         JOIN competitions_teams ct ON c.id = ct.id_competition
         JOIN teams_users tu ON ct.id_team = tu.id_team
WHERE tu.id_user = 1;

SELECT
    t.name,
    t.type,
    t.date,
    NULL AS local  -- Adiciona uma coluna NULL para manter a mesma estrutura
FROM
    timeline t
WHERE
    t.required = true

UNION ALL

SELECT DISTINCT
    t.name,
    t.type,
    t.date,
    p.description AS local  -- Nome do ponto
FROM
    timeline t
    JOIN
    competitions c ON t.id_competition = c.id
    JOIN
    competitions_teams ct ON c.id = ct.id_competition
    JOIN
    teams_users tu ON ct.id_team = tu.id_team
    JOIN
    points p ON c.id_point = p.id   -- Juntando a tabela points para obter o local
WHERE
    tu.id_user = :userId;






SELECT
    c.institution AS institution_name,               -- Nome da instituição
    comp.name AS competition_name,                   -- Nome da competição
    array_agg(u.name) AS participants                -- Participantes (nomes dos usuários)
FROM
    campus c                                         -- Tabela das instituições
        JOIN
    users u ON u.id_campus = c.id                    -- Relaciona os usuários à instituição
        JOIN
    teams_users tu ON tu.id_user = u.id              -- Relaciona usuários às equipes
        JOIN
    teams t ON tu.id_team = t.id                     -- Relaciona equipes aos times
        JOIN
    competitions_teams ct ON ct.id_team = t.id       -- Relaciona times às competições
        JOIN
    competitions comp ON ct.id_competition = comp.id -- Relaciona competições
WHERE
    c.deleted = FALSE AND u.deleted = FALSE AND
    t.deleted = FALSE AND comp.deleted = FALSE
GROUP BY
    c.institution, comp.name                         -- Agrupa por instituição e competição
ORDER BY
    c.institution, comp.name;                        -- Ordena por instituição e competição



SELECT u.*, c.institution AS campus
FROM users u
         JOIN campus c ON u.id_campus = c.id
WHERE u.cpf = :cpf AND u.deleted = FALSE;


SELECT
    u.id AS id,
    u."name" AS name,
    u.cpf AS cpf,
    u.registration AS registration,
    u.avatar AS avatar,
    u.is_evaluator AS isEvaluator,
    u.is_admin AS isAdmin,
    u.roles AS roles,
    c.id AS campusId,
    c.institution AS campus
FROM users u
         JOIN campus c ON u.id_campus = c.id
WHERE u.cpf = :cpf AND u.deleted = FALSE;


-- Seleciona todas as competições que têm comissão
SELECT *
FROM competitions
WHERE commission = TRUE;

-- Seleciona informações dos estudantes, times e competições com base na comissão
SELECT
    array_agg(u.name) AS participants,
    t.id AS "ID time",
    c.id AS competition_id,
    cam.institution AS campus_name
FROM
    users u
        JOIN
    teams_users tu ON u.id = tu.id_user
        JOIN
    teams t ON tu.id_team = t.id
        JOIN
    competitions_teams ct ON t.id = ct.id_team
        JOIN
    competitions c ON ct.id_competition = c.id
        JOIN
    campus cam ON u.id_campus = cam.id
WHERE
    c.commission = TRUE
  AND c.id = 1
GROUP BY
    t.id, c.id, cam.institution;

-- Seleciona informações da comissão e grades relacionadas ao time e competição
SELECT DISTINCT
    co.id AS "id da comissão",
    t.id AS "ID do time",
    co.grade_name_1,
    co.grade_name_2,
    co.grade_name_3,
    co.grade_name_4
FROM
    users u
        JOIN
    teams_users tu ON u.id = tu.id_user
        JOIN
    teams t ON tu.id_team = t.id
        JOIN
    competitions_teams ct ON t.id = ct.id_team
        JOIN
    competitions c ON ct.id_competition = c.id
        JOIN
    commissions co ON ct.id = co.id_competitions_teams
WHERE
    u.deleted = FALSE
  AND t.deleted = FALSE
  AND c.deleted = FALSE
  AND co.id_user IS NOT NULL
  AND t.id = 2;


-- Seleciona o ID dos times associados a uma competição específica
SELECT t.id
FROM competitions_teams ct
         JOIN teams t ON t.id = ct.id_team
WHERE ct.id_competition = :competitionId;

-- Seleciona informações do usuário com base no ID do time
SELECT
    u.id AS "id",
    u.name AS "name",
    u.registration AS "registration",
    u.roles AS "roles"
FROM teams_users tu
         JOIN users u ON u.id = tu.id_user
WHERE tu.id = :teamId;

-- Seleciona informações de múltiplos usuários com base em IDs de times
SELECT
    u.id AS "id",
    u.name AS "name",
    u.registration AS "registration",
    u.roles AS "roles"
FROM teams_users tu
         JOIN users u ON u.id = tu.id_user
WHERE tu.id IN (:teamIds);

-- Seleciona o ID dos times associados a uma competição específica
SELECT t.id
FROM competitions_teams ct
         JOIN teams t ON t.id = ct.id_team
WHERE ct.id_competition = :competitionId;

-- Seleciona todas as competições
SELECT *
FROM competitions c;

SELECT
    t.id AS team_id,
    t.grade,
    t.created,
    t.created_by,
    t.updated,
    t.updated_by,
    array_agg(u.name) AS participants
FROM
    teams t
        JOIN
    teams_users tu ON tu.id_team = t.id
        JOIN
    users u ON u.id = tu.id_user
WHERE
    t.deleted = FALSE
  AND tu.deleted = FALSE
GROUP BY
    t.id;

