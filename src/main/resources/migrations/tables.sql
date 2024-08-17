CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    "order"               INTEGER   DEFAULT NULL,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   NOT NULL,
    traditional_dance_3   VARCHAR   NOT NULL,
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
    descption  VARCHAR   NOT NULL,
    icon       VARCHAR   NOT NULL,
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
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR   NOT NULL,
    modality     VARCHAR   NOT NULL,
    descption    VARCHAR   NOT NULL,
    fecult_event BOOLEAN   DEFAULT FALSE,
    participants INTEGER   NOT NULL,
    commission   BOOLEAN   DEFAULT FALSE,
    id_point     BIGINT    NOT NULL,
    deleted      BOOLEAN   DEFAULT FALSE,
    created      TIMESTAMP DEFAULT NOW(),
    created_by   BIGINT    NULL,
    updated      TIMESTAMP NULL,
    updated_by   BIGINT    NULL,
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

CREATE TABLE IF NOT EXISTS commission
(
    id                    BIGSERIAL PRIMARY KEY,
    id_competitions_teams BIGINT                     NOT NULL,
    id_user               BIGINT                     NOT NULL,
    grade_1               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_2               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_3               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_4               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_5               NUMERIC(10, 2) DEFAULT 0.0,
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
    id_point       BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
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


-- Tabela presentations
INSERT INTO
    presentations ("order", entrance_choreography, traditional_dance_1, traditional_dance_2,
                   traditional_dance_3, exit_choreography, birivas_dances_1, birivas_dances_2,
                   birivas_dances_3, deleted, created, created_by, updated, updated_by)
VALUES
    (1, 'Entrance 1', 'Dance 1A', 'Dance 2A', 'Dance 3A', 'Exit 1A', 'Birivas Dance 1A', 'Birivas Dance 2A', 'Birivas Dance 3A', FALSE, NOW(), 1, NULL, NULL),
    (2, 'Entrance 2', 'Dance 1B', 'Dance 2B', 'Dance 3B', 'Exit 2B', 'Birivas Dance 1B', 'Birivas Dance 2B', 'Birivas Dance 3B', FALSE, NOW(), 2, NULL, NULL),
    (3, 'Entrance 3', 'Dance 1C', 'Dance 2C', 'Dance 3C', 'Exit 3C', 'Birivas Dance 1C', 'Birivas Dance 2C', 'Birivas Dance 3C', FALSE, NOW(), 3, NULL, NULL);

-- Tabela campus
INSERT INTO
    campus (institution, coordinator_name, description, entity, dormitory, id_presentation,
            deleted, created, created_by, updated, updated_by)
VALUES
    ('Institution A', 'Coordinator A', 'Description A', 'Entity A', 'Dormitory A', 1, FALSE, NOW(), 1, NULL, NULL),
    ('Institution B', 'Coordinator B', 'Description B', 'Entity B', 'Dormitory B', 2, FALSE, NOW(), 2, NULL, NULL),
    ('Institution C', 'Coordinator C', 'Description C', 'Entity C', 'Dormitory C', 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela users
INSERT INTO
    users (name, cpf, registration, password, roles, is_evaluator, is_admin,
           id_campus, deleted, created, created_by, updated, updated_by)
VALUES
    ('User A', '12345678901', 'Reg123', 'Password123', 'RoleA', FALSE, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
    ('User B', '23456789012', 'Reg456', 'Password456', 'RoleB', TRUE, FALSE, 2, FALSE, NOW(), 2, NULL, NULL),
    ('User C', '34567890123', 'Reg789', 'Password789', 'RoleC', FALSE, TRUE, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela points
INSERT INTO
    points (name, type, descption, icon, latitude, longitude,
            deleted, created, created_by, updated, updated_by)
VALUES
    ('Point A', 'Type A', 'Description A', 'Icon A', 'Lat A', 'Lon A', FALSE, NOW(), 1, NULL, NULL),
    ('Point B', 'Type B', 'Description B', 'Icon B', 'Lat B', 'Lon B', FALSE, NOW(), 2, NULL, NULL),
    ('Point C', 'Type C', 'Description C', 'Icon C', 'Lat C', 'Lon C', FALSE, NOW(), 3, NULL, NULL);

-- Tabela competitions
INSERT INTO
    competitions (name, modality, descption, fecult_event, participants, commission, id_point,
                  deleted, created, created_by, updated, updated_by)
VALUES
    ('Competition A', 'Modality A', 'Description A', FALSE, 10, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
    ('Competition B', 'Modality B', 'Description B', TRUE, 20, TRUE, 2, FALSE, NOW(), 2, NULL, NULL),
    ('Competition C', 'Modality C', 'Description C', FALSE, 30, FALSE, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela teams
INSERT INTO
    teams (grade, deleted, created, created_by, updated, updated_by)
VALUES
    (95.5, FALSE, NOW(), 1, NULL, NULL),
    (88.0, FALSE, NOW(), 2, NULL, NULL),
    (92.3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela teams_users
INSERT INTO
    teams_users (id_user, id_team, deleted, created, created_by, updated, updated_by)
VALUES
    (1, 1, FALSE, NOW(), 1, NULL, NULL),
    (2, 2, FALSE, NOW(), 2, NULL, NULL),
    (3, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela competitions_teams
INSERT INTO
    competitions_teams (id_team, id_competition, deleted, created, created_by, updated, updated_by)
VALUES
    (1, 1, FALSE, NOW(), 1, NULL, NULL),
    (2, 2, FALSE, NOW(), 2, NULL, NULL),
    (3, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela commission
INSERT INTO
    commission (id_competitions_teams, id_user, grade_1, grade_2, grade_3, grade_4, grade_5,
                deleted, created, created_by, updated, updated_by)
VALUES
    (1, 1, 90.0, 85.0, 80.0, 75.0, 70.0, FALSE, NOW(), 1, NULL, NULL),
    (2, 2, 85.0, 80.0, 75.0, 70.0, 65.0, FALSE, NOW(), 2, NULL, NULL),
    (3, 3, 80.0, 75.0, 70.0, 65.0, 60.0, FALSE, NOW(), 3, NULL, NULL);

-- Tabela timeline
INSERT INTO
    timeline (name, required, date, id_point, id_competition, deleted, created, created_by, updated, updated_by)
VALUES
    ('Timeline Event A', FALSE, '2024-07-24 00:00:00', 1, 1, FALSE, NOW(), 1, NULL, NULL),
    ('Timeline Event B', TRUE, '2024-07-25 00:00:00', 2, 2, FALSE, NOW(), 2, NULL, NULL),
    ('Timeline Event C', FALSE, '2024-07-26 00:00:00', 3, 3, FALSE, NOW(), 3, NULL, NULL);



CREATE TABLE IF NOT EXISTS presentations
(
    id                    BIGSERIAL PRIMARY KEY,
    "order"               INTEGER   DEFAULT NULL,
    entrance_choreography VARCHAR   NULL,
    traditional_dance_1   VARCHAR   NOT NULL,
    traditional_dance_2   VARCHAR   NOT NULL,
    traditional_dance_3   VARCHAR   NOT NULL,
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
    descption  VARCHAR   NOT NULL,
    icon       VARCHAR   NOT NULL,
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
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR   NOT NULL,
    modality     VARCHAR   NOT NULL,
    descption    VARCHAR   NOT NULL,
    fecult_event BOOLEAN   DEFAULT FALSE,
    participants INTEGER   NOT NULL,
    commission   BOOLEAN   DEFAULT FALSE,
    id_point     BIGINT    NOT NULL,
    deleted      BOOLEAN   DEFAULT FALSE,
    created      TIMESTAMP DEFAULT NOW(),
    created_by   BIGINT    NULL,
    updated      TIMESTAMP NULL,
    updated_by   BIGINT    NULL,
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

CREATE TABLE IF NOT EXISTS commission
(
    id                    BIGSERIAL PRIMARY KEY,
    id_competitions_teams BIGINT                     NOT NULL,
    id_user               BIGINT                     NOT NULL,
    grade_1               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_2               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_3               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_4               NUMERIC(10, 2) DEFAULT 0.0 NOT NULL,
    grade_5               NUMERIC(10, 2) DEFAULT 0.0,
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
    id_point       BIGINT    NOT NULL,
    id_competition BIGINT    NOT NULL,
    deleted        BOOLEAN   DEFAULT FALSE,
    created        TIMESTAMP DEFAULT NOW(),
    created_by     BIGINT    NULL,
    updated        TIMESTAMP NULL,
    updated_by     BIGINT    NULL,
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



-- Tabela presentations
INSERT INTO presentations (
    "order", "entrance_choreography", "traditional_dance_1", "traditional_dance_2",
    "traditional_dance_3", "exit_choreography", "birivas_dances_1", "birivas_dances_2",
    "birivas_dances_3", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    (0, 'GiraGira na vitrola', 'Anú', 'Tatu de castanholas', '', '', '', '', '', FALSE, NOW(), 3, NULL, NULL);

-- Tabela campus
INSERT INTO campus (
    "institution", "coordinator_name", "description", "entity", "dormitory", "id_presentation",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    ('IFFar - SVS', 'Eduardo Feitoza', 'Instituto Federal Farroupilha - Campus São Vicente do Sul', 'NTG Trempe da Saudade', 'Predio B', 1, FALSE, NOW(), 1, NULL, NULL);

-- Tabela users
INSERT INTO users (
    "name", "cpf", "registration", "password", "roles", "is_evaluator", "is_admin",
    "id_campus", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
    ('Mauricio Carvalho Cogo', '05415375040', '2023001236', '05415375040', 'Estudante', FALSE, TRUE, 1, FALSE, NOW(), 1, NULL, NULL);

-- Tabela points
INSERT INTO points (
    "name", "type", "description", "icon", "latitude", "longitude",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Dança de salão', 'Prova', 'Forme uma dupla, dance duas das seguintes danças: Chote, Milonga, Chamamé, Rancheira, Valsa, Marzuca, ou Bugio, em dois minutos cada, e conquiste a maior pontuação para vencer!', '---', '-29.70438062047002', '-54.69807524954617', FALSE, NOW(), 1, NULL, NULL),
      ('Revezamento do mate', 'Prova', 'Forme uma equipe de quatro (dois peões e duas prendas), ceve, tome e passe o mate em revezamento. A equipe que completar a prova no menor tempo vence!', '---', '-29.704254157352864', '-54.69765847554981', FALSE, NOW(), 1, NULL, NULL),
      ('Triatlo campeiro', 'Prova', 'Forme uma equipe de seis (três peões e três prendas) e complete as três etapas: nó de lenço, corrida do saco e debulhar milho. A equipe que finalizar todas as etapas primeiro vence!', '---', '-29.704254157352864', '-54.69765847554981', FALSE, NOW(), 1, NULL, NULL),
      ('Tetarfe', 'Prova', 'Participe em duplas (um peão e uma prenda) e acumule pontos em lançamentos seguindo o Regulamento de Esportes Campeiros do MTG. Vence a equipe com a maior pontuação total. Em caso de empate, será considerado o maior desempenho em argola, ferradura e tava, nessa ordem.', '---', '-29.702772406805014', '-54.69581553656609', FALSE, NOW(), 1, NULL, NULL),
      ('Tiro de laço vaca parada', 'Prova', 'Forme uma dupla, laçe as aspas da vaca parada a 8 metros (homens) ou 2 metros (mulheres) em três tentativas, e vença acertando mais laçadas válidas.', '---', '-29.704254157352864', '-54.69765847554981', FALSE, NOW(), 1, NULL, NULL),
      ('Pescaria gaúcha', 'Prova', 'Forme uma dupla com um peão e uma prenda, monte sua vara de pescar, busque sua isca e pesque o peixe mais pesado para vencer!', '---', '-29.705887946838324', '-54.696781999287786', FALSE, NOW(), 1, NULL, NULL),
      ('Truco', 'Prova', 'Forme uma equipe de três e participe das partidas eliminatórias de Truco Gaudério. A competição segue o Regulamento de Esportes Campeiros do MTG, com formato “melhor de três partidas”. A equipe vencedora será a campeã!', '---', '-29.70438062047002', '-54.69807524954617', FALSE, NOW(), 1, NULL, NULL),
      ('Arroz Carreteiro', 'Prova', 'Forme uma equipe (um peão e uma prenda) e prepare arroz carreteiro para 40 pessoas. Os ingredientes e utensílios serão fornecidos, mas os temperos são de sua escolha para destacar sua identidade regional.', '---', '-29.70438062047002', '-54.69807524954617', FALSE, NOW(), 1, NULL, NULL),
      ('Artilharia gaúcha', 'Prova', 'Forme uma equipe de quatro (dois peões e duas prendas) e arremesse pedras com um bodoque para atingir os alvos. Cada acerto vale um ponto e a equipe com mais pontos vence. Em caso de empate, cada participante terá mais um disparo. Traga seu bodoque tradicional!', '---', '-29.702380714802718', '-54.69506985330737', FALSE, NOW(), 1, NULL, NULL),
      ('Cabo de guerra', 'Prova', 'Forme uma equipe de sete (mínimo de duas prendas) e participe do cabo de guerra com uma corda de 14 metros. Puxe a corda para seu lado e faça com que pelo menos um adversário ultrapasse a marca no chão. A equipe vencedora será a campeã!', '---', '-29.702380714802718', '-54.69506985330737', FALSE, NOW(), 1, NULL, NULL),
      ('Bocha campeira', 'Prova', 'Forme uma equipe de três (dois peões e uma prenda) e jogue bocha campeira seguindo o Regulamento de Esportes Campeiros do MTG. As partidas são disputadas em duas passadas, com uma passada extra em caso de empate. A equipe com mais pontos vence!', '---', '-29.702380714802718', '-54.69506985330737', FALSE, NOW(), 1, NULL, NULL);
-- Tabela competitions
INSERT INTO competitions (
    "name", "modality", "descption", "fecult_event", "participants", "commission", "id_point",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Competition A', 'Modality A', 'Description A', FALSE, 10, FALSE, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Competition B', 'Modality B', 'Description B', TRUE, 20, TRUE, 2, FALSE, NOW(), 2, NULL, NULL),
      ('Competition C', 'Modality C', 'Description C', FALSE, 30, FALSE, 3, FALSE, NOW(), 3, NULL, NULL);

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
      (2, 2, FALSE, NOW(), 2, NULL, NULL),
      (3, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela competitions_teams
INSERT INTO competitions_teams (
    "id_team", "id_competition", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (1, 1, FALSE, NOW(), 1, NULL, NULL),
      (2, 2, FALSE, NOW(), 2, NULL, NULL),
      (3, 3, FALSE, NOW(), 3, NULL, NULL);

-- Tabela commission
INSERT INTO commission (
    "id_competitions_teams", "id_user", "grade_1", "grade_2", "grade_3", "grade_4", "grade_5",
    "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      (1, 1, 90.0, 85.0, 80.0, 75.0, 70.0, FALSE, NOW(), 1, NULL, NULL),
      (2, 2, 85.0, 80.0, 75.0, 70.0, 65.0, FALSE, NOW(), 2, NULL, NULL),
      (3, 3, 80.0, 75.0, 70.0, 65.0, 60.0, FALSE, NOW(), 3, NULL, NULL);

-- Tabela timeline
INSERT INTO timeline (
    "name", "required", "date", "id_point", "id_competition", "deleted", "created", "created_by", "updated", "updated_by"
) VALUES
      ('Timeline Event A', FALSE, '2024-07-24 00:00:00', 1, 1, FALSE, NOW(), 1, NULL, NULL),
      ('Timeline Event B', TRUE, '2024-07-25 00:00:00', 2, 2, FALSE, NOW(), 2, NULL, NULL),
      ('Timeline Event C', FALSE, '2024-07-26 00:00:00', 3, 3, FALSE, NOW(), 3, NULL, NULL);
