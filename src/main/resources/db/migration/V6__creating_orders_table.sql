

CREATE TABLE addresses (

    address_id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL,

    full_name VARCHAR(100) NOT NULL,

    phone VARCHAR(10) NOT NULL,

    address_line VARCHAR(255) NOT NULL,

    city VARCHAR(100) NOT NULL,

    state VARCHAR(100) NOT NULL,

    pincode VARCHAR(6) NOT NULL,

    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
               ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id)

);