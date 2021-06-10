CREATE TABLE IF NOT EXISTS user
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `firstname` varchar(64)  NOT NULL,
    `lastname`  varchar(64)  NOT NULL,
    `username`  varchar(500) NOT NULL,
    `email`     varchar(64)  NOT NULL,
    `date`      DATE         NOT NULL,
    `city`      varchar(64),
    `password`  varchar(64)  NOT NULL,
    `enabled`   bool,
    `role`      varchar(128) DEFAULT 'USER',
    `image`   varchar(128),
    PRIMARY KEY (`id`)
);
-- password 12345
INSERT INTO `user`(email, firstname, lastname, date, username, password, role, enabled)
VALUES ('admin@photo.ru', 'AdminName', 'AdminLastName', '2001-01-01', 'Admin',
        '$2a$10$oIgilrenfZgb7E5S0jZZ2urA1S/wHSJhXztj3M8PhtOn0e3UefuS.', 'ADMIN,MANAGER,USER', true);

CREATE TABLE IF NOT EXISTS album
(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(45) NOT NULL,
    `is_shared` bool,
    `user_id` INT NOT NULL,
        FOREIGN KEY (`user_id`)
            REFERENCES user (`id`)
            ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS comment
(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `text` VARCHAR(500) NOT NULL,
    `username` varchar(64) NOT NULL,
    `image_id` INT NOT NULL
);
CREATE TABLE IF NOT EXISTS image
(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(45) NOT NULL,
    `tags` VARCHAR(500) NULL,
    `file` VARCHAR(500) NOT NULL,
    `album_id` INT NULL
);



