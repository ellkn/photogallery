CREATE TABLE IF NOT EXISTS user
(
    `userId`      int         NOT NULL AUTO_INCREMENT,
    `firstName`   varchar(64) NOT NULL,
    `lastName`    varchar(64) NOT NULL,
    `userName`    varchar(500) NOT NULL,
    `email`       varchar(64) NOT NULL,
    `dateOfBirth` DATE        NOT NULL,
    `city`        varchar(64),
    `password`    varchar(64) NOT NULL,
    `enabled`     bool,
    `role`        varchar(128) DEFAULT 'USER',
    `imageId` int,
    PRIMARY KEY (`UserId`)
);
-- password 12345
INSERT INTO `user`(email, firstName, lastName, dateOfBirth, username, password, role, enabled) VALUES ('admin@photo.ru','AdminName','AdminLastName','2001-01-01','Admin','$2a$10$oIgilrenfZgb7E5S0jZZ2urA1S/wHSJhXztj3M8PhtOn0e3UefuS.', 'ADMIN,MANAGER,USER', true);

