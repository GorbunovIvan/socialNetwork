# users
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(45) NOT NULL,
                                     login VARCHAR(25) NOT NULL,
                                     password VARCHAR(25) NOT NULL,
                                     registrationDate TIMESTAMP
);

# friends
CREATE TABLE IF NOT EXISTS friends (
                                    id SERIAL PRIMARY KEY,
                                    sender BIGINT UNSIGNED NOT NULL,
                                    receiver BIGINT UNSIGNED NOT NULL
);
ALTER TABLE `social_network`.`friends`
    ADD CONSTRAINT friends_uniqueIDs UNIQUE (sender, receiver),
    ADD CONSTRAINT `fk_id_sender`
        FOREIGN KEY (`sender`)
            REFERENCES `social_network`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_id_receiver`
        FOREIGN KEY (`receiver`)
            REFERENCES `social_network`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

# messages
CREATE TABLE IF NOT EXISTS messages (
                                        id SERIAL PRIMARY KEY,
                                        sender BIGINT UNSIGNED NOT NULL,
                                        receiver BIGINT UNSIGNED NOT NULL,
                                        message TEXT,
                                        creationDate TIMESTAMP
);
ALTER TABLE `social_network`.`messages`
    ADD CONSTRAINT `fk_id_sender_message`
        FOREIGN KEY (`sender`)
            REFERENCES `social_network`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_id_receiver_message`
        FOREIGN KEY (`receiver`)
            REFERENCES `social_network`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;