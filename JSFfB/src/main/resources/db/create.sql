CREATE TABLE student(
                        id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                        first_name varchar DEFAULT NULL,
                        last_name  varchar DEFAULT NULL,
                        email      varchar DEFAULT NULL);