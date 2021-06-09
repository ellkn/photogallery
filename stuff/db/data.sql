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
    `imageid`   int,
    PRIMARY KEY (`id`)
);
-- password 12345
INSERT INTO `user`(email, firstname, lastname, date, username, password, role, enabled)
VALUES ('admin@photo.ru', 'AdminName', 'AdminLastName', '2001-01-01', 'Admin',
        '$2a$10$oIgilrenfZgb7E5S0jZZ2urA1S/wHSJhXztj3M8PhtOn0e3UefuS.', 'ADMIN,MANAGER,USER', true);

CREATE TABLE IF NOT EXISTS album
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `isShared` TINYINT NULL DEFAULT 0,
    `userId` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `userId_idx` (`userId` ASC) VISIBLE,
    CONSTRAINT `userId`
        FOREIGN KEY (`userId`)
            REFERENCES user (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS comment
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `text` VARCHAR(500) NOT NULL,
    `isUser` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `idUser_idx` (`isUser` ASC) VISIBLE,
    CONSTRAINT `idUser`
        FOREIGN KEY (`isUser`)
            REFERENCES user (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS image
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `tags` VARCHAR(500) NULL,
    `file` VARCHAR(500) NOT NULL,
    `albumId` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `albumId_idx` (`albumId` ASC) VISIBLE,
    CONSTRAINT `albumId`
        FOREIGN KEY (`albumId`)
            REFERENCES album (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



