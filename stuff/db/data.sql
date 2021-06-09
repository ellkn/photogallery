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
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `userId`   INT         NOT NULL,
    `title`    VARCHAR(64) NOT NULL,
    `isShared` TINYINT     NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    INDEX `userid_idx` (`userId` ASC) VISIBLE,
    CONSTRAINT `userid`
        FOREIGN KEY (`userId`) REFERENCES `photogallery`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS comment
(
    `id`     INT          NOT NULL AUTO_INCREMENT,
    `text`   VARCHAR(500) NOT NULL,
    `userId` INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `userId_idx` (`userId` ASC) VISIBLE,
    CONSTRAINT `iduser`
        FOREIGN KEY (`userId`)
            REFERENCES `photogallery`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS image
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `title`   VARCHAR(64)  NOT NULL,
    `tags`    VARCHAR(500) NULL,
    `albumId` INT          NOT NULL,
    `file`    VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `albumId_idx` (`albumId` ASC) VISIBLE,
    CONSTRAINT `albumId`
        FOREIGN KEY (`albumId`)
            REFERENCES `photogallery`.`album` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS commentimage
(
    `id`        INT NOT NULL AUTO_INCREMENT,
    `imageId`   INT NOT NULL,
    `commentId` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `commentId_idx` (`commentId` ASC) VISIBLE,
    INDEX `imageId_idx` (`imageId` ASC) VISIBLE,
    CONSTRAINT `commentId`
        FOREIGN KEY (`commentId`)
            REFERENCES `photogallery`.`comment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `imageId`
        FOREIGN KEY (`imageId`)
            REFERENCES `photogallery`.`image` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


