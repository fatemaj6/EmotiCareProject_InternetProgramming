CREATE DATABASE IF NOT EXISTS emoticare_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE emoticare_db;

CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    is_admin BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS resources (
    resource_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    resource_type VARCHAR(50),
    category VARCHAR(100),
    content LONGTEXT,
    duration INT DEFAULT 0,
    progress INT DEFAULT 0,
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS screenings (
    screening_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    assessment_type VARCHAR(100),
    score INT,
    level VARCHAR(50),
    responses LONGTEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS forums (
    forum_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content LONGTEXT,
    category VARCHAR(100),
    likes INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS forum_comments (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    forum_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT,
    likes INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (forum_id) REFERENCES forums(forum_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_forum (forum_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS badges (
    badge_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    badge_name VARCHAR(100),
    badge_icon VARCHAR(255),
    earned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS chat_messages (
    message_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    message TEXT,
    response LONGTEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users (full_name, email, password, is_admin) VALUES
('John Student', 'student@test.com', 'XJ+DPrCvY8jhNwQfyUbpHh76pIZ5DYvOT4Qt6VAU90c=', FALSE),
('Admin User', 'admin@test.com', 'XJ+DPrCvY8jhNwQfyUbpHh76pIZ5DYvOT4Qt6VAU90c=', TRUE);

INSERT INTO resources (title, description, resource_type, category, duration) VALUES
('Understanding Anxiety', 'Learn about anxiety disorders', 'Course', 'Anxiety', 45),
('Mindfulness Meditation', 'Practice mindfulness techniques', 'Video', 'Stress', 30),
('Sleep Hygiene Guide', 'Improve sleep quality', 'Article', 'Sleep', 15),
('Depression Management', 'Understand depression recovery', 'Course', 'Depression', 50);