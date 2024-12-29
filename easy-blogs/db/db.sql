-- Schema for auth.accounts
CREATE TABLE auth.accounts (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255),
    email VARCHAR(255),
    status BOOLEAN,
    is_verified BOOLEAN,
    roles VARCHAR(255),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for auth.authentication
CREATE TABLE auth.authentication (
    id VARCHAR(50) PRIMARY KEY,
    account_id VARCHAR(50) REFERENCES auth.accounts(id),
    usercase_id VARCHAR(50) REFERENCES auth.usercases(id),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for auth.usercases
CREATE TABLE auth.usercases (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(20),
    description VARCHAR(255),
    roles VARCHAR(255),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for auth.tokens
CREATE TABLE auth.tokens (
    id VARCHAR(50) PRIMARY KEY,
    token VARCHAR(255),
    username VARCHAR(50),
    expired_at TIMESTAMP,
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for profile.profiles
CREATE TABLE profile.profiles (
    id VARCHAR(50) PRIMARY KEY,
    account_id VARCHAR(50) REFERENCES auth.accounts(id),
    full_name VARCHAR(255),
    phone_number VARCHAR(50),
    address VARCHAR(255),
    bio TEXT,
    cover_photo VARCHAR(255),
    avatar VARCHAR(255),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for blog.blogs
CREATE TABLE blog.blogs (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(500),
    content TEXT,
    number_of_heart INT,
    profile_id VARCHAR(50) REFERENCES profile.profiles(id),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Schema for blog.comments
CREATE TABLE blog.comments (
    id VARCHAR(50) PRIMARY KEY,
    blog_id VARCHAR(50) REFERENCES blog.blogs(id),
    content TEXT,
    is_anonymous BOOLEAN,
    account_id VARCHAR(50),
    is_deleted BOOLEAN,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);
