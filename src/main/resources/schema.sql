create table tasks (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        description VARCHAR(50) NOT NULL,
                        status ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
                        priority ENUM('LOW',  'MEDIUM', 'HIGH') NOT NULL


);