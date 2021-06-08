CREATE TABLE IF NOT EXISTS user
(
    `id`      int         NOT NULL AUTO_INCREMENT,
    `firstname`   varchar(64) NOT NULL,
    `lastname`    varchar(64) NOT NULL,
    `username`    varchar(500) NOT NULL,
    `email`       varchar(64) NOT NULL,
    `date`        DATE        NOT NULL,
    `city`        varchar(64),
    `password`    varchar(64) NOT NULL,
    `enabled`     bool,
    `role`        varchar(128) DEFAULT 'USER',
    `imageid` int,
    PRIMARY KEY (`id`)
);
-- password 12345
INSERT INTO `user`(email, firstname, lastname, date, username, password, role, enabled) VALUES ('admin@photo.ru','AdminName','AdminLastName','2001-01-01','Admin','$2a$10$oIgilrenfZgb7E5S0jZZ2urA1S/wHSJhXztj3M8PhtOn0e3UefuS.', 'ADMIN,MANAGER,USER', true);

