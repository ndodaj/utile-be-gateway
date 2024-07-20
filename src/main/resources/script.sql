-- Create necessary tables for Eureka Server if using MySQL (optional)
-- This is only required if you are persisting Eureka data in a MySQL database
CREATE TABLE eureka_server (
    id INT AUTO_INCREMENT PRIMARY KEY,
    instance_id VARCHAR(255) NOT NULL,
    app_name VARCHAR(255) NOT NULL,
    app_group_name VARCHAR(255),
    ip_addr VARCHAR(255) NOT NULL,
    vip_address VARCHAR(255),
    secure_vip_address VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    overridden_status VARCHAR(255),
    port INT,
    secure_port INT,
    country_id INT,
    data_center_info TEXT,
    host_name VARCHAR(255) NOT NULL,
    lease_info TEXT,
    metadata TEXT,
    last_updated TIMESTAMP,
    last_dirty_timestamp TIMESTAMP,
    action_type VARCHAR(255),
    asg_name VARCHAR(255)
);
