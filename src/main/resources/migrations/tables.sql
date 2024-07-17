CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   NOT NULL,
    traditional_dance_3   VARCHAR   NOT NULL,
    exit_choreography     VARCHAR   NULL,
    birivas_dances_1      VARCHAR   NULL,
    birivas_dances_2      VARCHAR   NULL,
    birivas_dances_3      VARCHAR   NULL,
    created               TIMESTAMP NULL,
    create_by             BIGINT    NULL,
    updated               TIMESTAMP NULL,
    updated_by            BIGINT    NULL,
    deleted               BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS campus
(
    id               BIGSERIAL PRIMARY KEY,
    institution      VARCHAR      NOT NULL,
    coordinator_name VARCHAR(100) NOT NULL,
    registration     VARCHAR      NOT NULL,
    entity           VARCHAR      NOT NULL,
    dormitory        VARCHAR      NOT NULL,
    id_presentation  BIGINT       NOT NULL,
    created          TIMESTAMP    NULL,
    create_by        BIGINT       NULL,
    updated          TIMESTAMP    NULL,
    updated_by       BIGINT       NULL,
    deleted          BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_presentation FOREIGN KEY (id_presentation) REFERENCES presentations (id)
);


CREATE TABLE IF NOT EXISTS users
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR     NOT NULL,
    cpf          VARCHAR(11) NOT NULL,
    registration VARCHAR     NULL,
    password     VARCHAR     NOT NULL,
    roles        VARCHAR     NOT NULL,
    is_evaluator BOOLEAN DEFAULT FALSE,
    is_admin     BOOLEAN DEFAULT FALSE,
    id_campus    BIGINT      NOT NULL,
    created      TIMESTAMP   NULL,
    create_by    BIGINT      NULL,
    updated      TIMESTAMP   NULL,
    updated_by   BIGINT      NULL,
    deleted      BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_campus FOREIGN KEY (id_campus) REFERENCES campus (id)
);


CREATE TABLE IF NOT EXISTS points
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR   NOT NULL,
    type       VARCHAR   NOT NULL,
    descption  VARCHAR   NOT NULL,
    icon       VARCHAR   NOT NULL,
    latitude   VARCHAR   NOT NULL,
    longitude  VARCHAR   NOT NULL,
    created    TIMESTAMP NULL,
    create_by  BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL,
    deleted    BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS competitions
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR   NOT NULL,
    modality     VARCHAR   NOT NULL,
    descption    VARCHAR   NOT NULL,
    fecult_event BOOLEAN DEFAULT FALSE,
    participants INTEGER   NOT NULL,
    commission   BOOLEAN DEFAULT FALSE,
    id_point     BIGINT    NOT NULL,
    created      TIMESTAMP NULL,
    create_by    BIGINT    NULL,
    updated      TIMESTAMP NULL,
    updated_by   BIGINT    NULL,
    deleted      BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id)
);

CREATE TABLE IF NOT EXISTS teams
(
    id         BIGSERIAL PRIMARY KEY,
    grade      NUMERIC(10, 2),
    created    TIMESTAMP NULL,
    create_by  BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL,
    deleted    BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS teams_users
(
    id         BIGSERIAL PRIMARY KEY,
    id_user    BIGINT    NOT NULL,
    id_team    BIGINT    NOT NULL,
    created    TIMESTAMP NULL,
    create_by  BIGINT    NULL,
    updated    TIMESTAMP NULL,
    updated_by BIGINT    NULL,
    deleted    BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id)
);

CREATE TABLE IF NOT EXISTS competitions_teams
(
    id             BIGSERIAL PRIMARY KEY,
    id_team        BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    created        TIMESTAMP NULL,
    create_by      BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    deleted        BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES teams (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
);

CREATE TABLE IF NOT EXISTS commission
(
    id                    BIGSERIAL PRIMARY KEY,
    id_competitions_teams BIGINT    NOT NULL,
    id_user               BIGINT    NOT NULL,
    grade_1               NUMERIC(10, 2) DEFAULT 0.0,
    grade_2               NUMERIC(10, 2) DEFAULT 0.0,
    grade_3               NUMERIC(10, 2) DEFAULT 0.0,
    grade_4               NUMERIC(10, 2) DEFAULT 0.0,
    grade_5               NUMERIC(10, 2) DEFAULT 0.0,
    created               TIMESTAMP NULL,
    create_by             BIGINT    NULL,
    updated               TIMESTAMP NULL,
    updated_by            BIGINT    NULL,
    deleted               BOOLEAN        DEFAULT FALSE,
    CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_competitions_teams FOREIGN KEY (id_competitions_teams) REFERENCES competitions_teams (id)
);

CREATE TABLE IF NOT EXISTS timeline
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR   NOT NULL,
    required       BOOLEAN DEFAULT FALSE,
    date           TIMESTAMP NOT NULL,
    id_point       BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    created        TIMESTAMP NULL,
    create_by      BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
    deleted        BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_point FOREIGN KEY (id_point) REFERENCES points (id),
    CONSTRAINT fk_competition FOREIGN KEY (id_competition) REFERENCES competitions (id)
);

DROP TABLE IF EXISTS teams_users CASCADE;

DROP TABLE IF EXISTS timeline CASCADE;

DROP TABLE IF EXISTS commission CASCADE;

DROP TABLE IF EXISTS competitions_teams CASCADE;

DROP TABLE IF EXISTS competitions CASCADE;

DROP TABLE IF EXISTS points CASCADE;

DROP TABLE IF EXISTS campus CASCADE;

DROP TABLE IF EXISTS presentations CASCADE;

DROP TABLE IF EXISTS teams CASCADE;

DROP TABLE IF EXISTS users CASCADE;


-- Dados fictícios para a tabela presentations
INSERT INTO
    presentations (id, entrance_choreography, traditional_dance_1, traditional_dance_2, traditional_dance_3, exit_choreography, birivas_dances_1, birivas_dances_2, birivas_dances_3)
VALUES
    (1, 'Entrada 1', 'Dança 1A', 'Dança 1B', 'Dança 1C', 'Saída 1', 'Biriva 1A', 'Biriva 1B', 'Biriva 1C'),
    (2, 'Entrada 2', 'Dança 2A', 'Dança 2B', 'Dança 2C', 'Saída 2', 'Biriva 2A', 'Biriva 2B', 'Biriva 2C');

-- Dados fictícios para a tabela campus
INSERT INTO
    campus (id, institution, coordinator_name, registration, entity, dormitory, id_presentation)
VALUES
    (1, 'Instituição A', 'Coordenador A', 'Registro A', 'Entidade A', 'Dormitório A', 1),
    (2, 'Instituição B', 'Coordenador B', 'Registro B', 'Entidade B', 'Dormitório B', 2);

-- Dados fictícios para a tabela points
INSERT INTO
    points (id, name, type, descption, icon, latitude, longitude)
VALUES
    (1, 'Ponto A', 'Tipo A', 'Descrição A', 'Icone A', -23.5505, -46.6333),
    (2, 'Ponto B', 'Tipo B', 'Descrição B', 'Icone B', -22.9068, -43.1729);

-- Dados fictícios para a tabela competitions
INSERT INTO
    competitions (id, name, modality, descption, fecult_event, participants, commission, id_point)
VALUES
    (1, 'Competição A', 'Modalidade A', 'Descrição A', TRUE, 10, TRUE, 1),
    (2, 'Competição B', 'Modalidade B', 'Descrição B', FALSE, 8, FALSE, 2);

-- Dados fictícios para a tabela teams
INSERT INTO
    teams (id, grade)
VALUES
    (1, 8.75),
    (2, 9.25);

-- Dados fictícios para a tabela competitions_teams
INSERT INTO
    competitions_teams (id, id_team, id_competition)
VALUES
    (1, 1, 1),
    (2, 2, 2);

-- Dados fictícios para a tabela users
INSERT INTO
    users (id, name, cpf, registration, password, roles, is_evaluator, is_admin, id_campus)
VALUES
    (1, 'Usuário A', '12345678901', 'Registro A', 'senha123', 'ROLE_USER', FALSE, FALSE, 1),
    (2, 'Usuário B', '23456789012', 'Registro B', 'senha456', 'ROLE_USER', TRUE, FALSE, 2);

-- Dados fictícios para a tabela commission
INSERT INTO
    commission (id, id_competitions_teams, id_user, grade_1, grade_2, grade_3, grade_4, grade_5)
VALUES
    (1, 1, 1, 8.5, 9.0, 8.0, 7.5, 9.5),
    (2, 2, 2, 7.5, 8.0, 7.0, 8.5, 8.0);

-- Dados fictícios para a tabela timeline
INSERT INTO
    timeline (id, name, required, date, id_point, id_competition)
VALUES
    (1, 'Evento A', TRUE, '2024-07-15 10:00:00', 1, 1),
    (2, 'Evento B', FALSE, '2024-07-16 14:00:00', 2, 2);

-- Dados fictícios para a tabela teams_users
INSERT INTO
    teams_users (id, id_user, id_team)
VALUES
    (1, 1, 1),
    (2, 2, 2);
