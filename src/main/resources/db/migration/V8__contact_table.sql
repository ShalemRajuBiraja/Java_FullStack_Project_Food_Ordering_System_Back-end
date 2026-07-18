CREATE TABLE contacts (

    contact_id BIGINT PRIMARY KEY AUTO_INCREMENT,

    user_id BIGINT NOT NULL,

    full_name VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL,

    phone VARCHAR(10) NOT NULL,

    subject VARCHAR(200) NOT NULL,

    issue_type VARCHAR(50) NOT NULL,

    order_id BIGINT NULL,

    message TEXT NOT NULL,

    status VARCHAR(20) DEFAULT 'OPEN',

    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

        FOREIGN KEY (user_id)
        REFERENCES users(user_id)

);