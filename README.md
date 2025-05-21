
create database ecommerce_db;
use ecommerce_db;
create table users(
    user_id int auto_increment primary key,
    username varchar(50) not null unique,
    email varchar(100) not null unique,
    password varchar(255) not null,
    full_name varchar(100),
    phone_number varchar(20),
    address text
);
select * from users;
drop table products;
create table products (
    id int auto_increment primary key,
    name varchar(255) not null,
    brand varchar(100),
    model varchar(100),
    product_description text,
    price decimal(10,2) not null ,
    quantity int not null default 1 check(quantity >=0),
    is_available boolean default true
);


INSERT INTO products ( name, brand, model, product_description, price, quantity, is_available) VALUES
( 'iPhone 14', 'Apple', '14', 'Latest Apple iPhone with A15 Bionic chip', 999.99, 50, 1),
( 'Samsung Galaxy S21', 'Samsung', 'S21', 'Flagship Samsung Galaxy S21 with 5G support', 799.99, 60, 1),
( 'OnePlus 9', 'OnePlus', '9', 'OnePlus 9 with Snapdragon 888 chipset', 729.99, 55, 1),
( 'Xiaomi Mi 11', 'Xiaomi', 'Mi 11', 'Mi 11 with 108MP camera and Snapdragon 888', 749.99, 40, 1),
( 'Google Pixel 6', 'Google', 'Pixel 6', 'Google Pixel 6 with Google Tensor chip', 599.99, 70, 1),
( 'Sony Xperia 1 III', 'Sony', '1 III', 'Sony Xperia 1 III with 4K OLED display', 1299.99, 30, 1),
( 'Samsung Galaxy A52', 'Samsung', 'A52', 'Samsung Galaxy A52 with AMOLED display', 349.99, 100, 1),
( 'iPhone 13 Pro', 'Apple', '13 Pro', 'iPhone 13 Pro with 120Hz ProMotion display', 1099.99, 45, 1),
( 'Oppo Find X3 Pro', 'Oppo', 'Find X3 Pro', 'Oppo flagship with Snapdragon 888 and 120Hz AMOLED', 1149.99, 25, 1),
( 'Realme GT', 'Realme', 'GT', 'Realme GT with Snapdragon 870 and AMOLED display', 499.99, 80, 1),
( 'Motorola Edge 20', 'Motorola', 'Edge 20', 'Motorola Edge 20 with 108MP camera and 5G support', 649.99, 60, 1),
( 'Xiaomi Redmi Note 10 Pro', 'Xiaomi', 'Redmi Note 10 Pro', 'Redmi Note 10 Pro with 108MP camera', 249.99, 120, 1),
( 'Vivo V21', 'Vivo', 'V21', 'Vivo V21 with 44MP selfie camera', 329.99, 90, 1),
( 'Huawei P40 Pro', 'Huawei', 'P40 Pro', 'Huawei P40 Pro with Leica camera and 5G support', 999.99, 35, 1),
( 'Nokia 8.3 5G', 'Nokia', '8.3 5G', 'Nokia 8.3 5G with Snapdragon 765G and ZEISS camera', 699.99, 50, 1),
( 'Asus Zenfone 8', 'Asus', 'Zenfone 8', 'Asus Zenfone 8 with Snapdragon 888', 649.99, 45, 1),
( 'Motorola Moto G Power', 'Motorola', 'Moto G Power', 'Motorola Moto G Power with large battery', 249.99, 150, 1),
( 'Samsung Galaxy Z Fold 3', 'Samsung', 'Z Fold 3', 'Samsung Galaxy Z Fold 3 with foldable display', 1799.99, 15, 1),
( 'iPhone SE 2020', 'Apple', 'SE 2020', 'iPhone SE with A13 Bionic chip', 399.99, 200, 1),
( 'Xiaomi Poco X3 Pro', 'Xiaomi', 'Poco X3 Pro', 'Poco X3 Pro with Snapdragon 860 and 120Hz display', 249.99, 130, 1),
( 'Google Pixel 5', 'Google', 'Pixel 5', 'Google Pixel 5 with 5G and OLED display', 699.99, 50, 1),
( 'Honor 50', 'Honor', '50', 'Honor 50 with Snapdragon 778G and 108MP camera', 499.99, 60, 1),
( 'iPhone 12', 'Apple', '12', 'iPhone 12 with A14 Bionic chip and 5G support', 699.99, 85, 1),
( 'Samsung Galaxy A32', 'Samsung', 'A32', 'Samsung Galaxy A32 with 90Hz Super AMOLED', 249.99, 160, 1),
( 'Xiaomi Mi 10', 'Xiaomi', 'Mi 10', 'Xiaomi Mi 10 with 108MP camera and Snapdragon 865', 799.99, 45, 1),
( 'Realme 8 Pro', 'Realme', '8 Pro', 'Realme 8 Pro with 108MP quad camera', 299.99, 100, 1),
( 'Oppo Reno 6', 'Oppo', 'Reno 6', 'Oppo Reno 6 with MediaTek Dimensity 900', 499.99, 70, 1),
( 'Vivo Y20', 'Vivo', 'Y20', 'Vivo Y20 with Snapdragon 460 and 5000mAh battery', 149.99, 180, 1);


select * from products;








ALTER TABLE users ADD COLUMN user_type VARCHAR(20) NOT NULL;

create table categories (
    id int auto_increment primary key,
    name varchar(100) not null,
    type enum('new', 'old') not null,
    description text
);
create table products (
    id int auto_increment primary key,
    category_id int not null,
    name varchar(255) not null,
    brand varchar(100),
    model varchar(100),
    product_description text,
    price decimal(10,2) not null,
    quantity int not null default 1,
    is_available boolean default true,
    foreign key (category_id) references categories(id) on delete cascade
);
create table orders (
    id int auto_increment primary key,
    orders_user_id int not null,
    order_date timestamp default current_timestamp,
    total_amount decimal(10,2) not null,
    status enum('pending', 'confirmed', 'shipped', 'delivered', 'cancelled') default 'pending',
    payment_status enum('pending', 'paid', 'failed') default 'pending',
    foreign key (orders_user_id) references users(user_id) on delete cascade
);

CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1
);
SELECT * FROM categories;
INSERT INTO categories (id, name) VALUES
(1, 'Electronics'),
(2, 'Mobile Phones'),
(3, 'Laptops'),
(4, 'Accessories');

INSERT INTO products (category_id, name, brand, model, product_description, price, quantity, is_available) VALUES
(2, 'iPhone 14', 'Apple', '14', 'Latest Apple iPhone with A15 Bionic chip', 999.99, 50, 1),
(2, 'Samsung Galaxy S21', 'Samsung', 'S21', 'Flagship Samsung Galaxy S21 with 5G support', 799.99, 60, 1),
(2, 'OnePlus 9', 'OnePlus', '9', 'OnePlus 9 with Snapdragon 888 chipset', 729.99, 55, 1),
(2, 'Xiaomi Mi 11', 'Xiaomi', 'Mi 11', 'Mi 11 with 108MP camera and Snapdragon 888', 749.99, 40, 1),
(2, 'Google Pixel 6', 'Google', 'Pixel 6', 'Google Pixel 6 with Google Tensor chip', 599.99, 70, 1),
(2, 'Sony Xperia 1 III', 'Sony', '1 III', 'Sony Xperia 1 III with 4K OLED display', 1299.99, 30, 1),
(2, 'Samsung Galaxy A52', 'Samsung', 'A52', 'Samsung Galaxy A52 with AMOLED display', 349.99, 100, 1),
(2, 'iPhone 13 Pro', 'Apple', '13 Pro', 'iPhone 13 Pro with 120Hz ProMotion display', 1099.99, 45, 1),
(2, 'Oppo Find X3 Pro', 'Oppo', 'Find X3 Pro', 'Oppo flagship with Snapdragon 888 and 120Hz AMOLED', 1149.99, 25, 1),
(2, 'Realme GT', 'Realme', 'GT', 'Realme GT with Snapdragon 870 and AMOLED display', 499.99, 80, 1),
(2, 'Motorola Edge 20', 'Motorola', 'Edge 20', 'Motorola Edge 20 with 108MP camera and 5G support', 649.99, 60, 1),
(2, 'Xiaomi Redmi Note 10 Pro', 'Xiaomi', 'Redmi Note 10 Pro', 'Redmi Note 10 Pro with 108MP camera', 249.99, 120, 1),
(2, 'Vivo V21', 'Vivo', 'V21', 'Vivo V21 with 44MP selfie camera', 329.99, 90, 1),
(2, 'Huawei P40 Pro', 'Huawei', 'P40 Pro', 'Huawei P40 Pro with Leica camera and 5G support', 999.99, 35, 1),
(2, 'Nokia 8.3 5G', 'Nokia', '8.3 5G', 'Nokia 8.3 5G with Snapdragon 765G and ZEISS camera', 699.99, 50, 1),
(2, 'Asus Zenfone 8', 'Asus', 'Zenfone 8', 'Asus Zenfone 8 with Snapdragon 888', 649.99, 45, 1),
(2, 'Motorola Moto G Power', 'Motorola', 'Moto G Power', 'Motorola Moto G Power with large battery', 249.99, 150, 1),
(2, 'Samsung Galaxy Z Fold 3', 'Samsung', 'Z Fold 3', 'Samsung Galaxy Z Fold 3 with foldable display', 1799.99, 15, 1),
(2, 'iPhone SE 2020', 'Apple', 'SE 2020', 'iPhone SE with A13 Bionic chip', 399.99, 200, 1),
(2, 'Xiaomi Poco X3 Pro', 'Xiaomi', 'Poco X3 Pro', 'Poco X3 Pro with Snapdragon 860 and 120Hz display', 249.99, 130, 1),
(2, 'Google Pixel 5', 'Google', 'Pixel 5', 'Google Pixel 5 with 5G and OLED display', 699.99, 50, 1),
(2, 'Honor 50', 'Honor', '50', 'Honor 50 with Snapdragon 778G and 108MP camera', 499.99, 60, 1),
(2, 'iPhone 12', 'Apple', '12', 'iPhone 12 with A14 Bionic chip and 5G support', 699.99, 85, 1),
(2, 'Samsung Galaxy A32', 'Samsung', 'A32', 'Samsung Galaxy A32 with 90Hz Super AMOLED', 249.99, 160, 1),
(2, 'Xiaomi Mi 10', 'Xiaomi', 'Mi 10', 'Xiaomi Mi 10 with 108MP camera and Snapdragon 865', 799.99, 45, 1),
(2, 'Realme 8 Pro', 'Realme', '8 Pro', 'Realme 8 Pro with 108MP quad camera', 299.99, 100, 1),
(2, 'Oppo Reno 6', 'Oppo', 'Reno 6', 'Oppo Reno 6 with MediaTek Dimensity 900', 499.99, 70, 1),
(2, 'Vivo Y20', 'Vivo', 'Y20', 'Vivo Y20 with Snapdragon 460 and 5000mAh battery', 149.99, 180, 1);


INSERT INTO users (username, email, acc_password, user_type, full_name, phone_number, address) VALUES
('ahmed_siddiqui', 'ahmed.siddiqui@example.com', 'password123', 'buyer', 'Ahmed Siddiqui', '03011234567', '123 Main Street, Lahore, Pakistan'),
('fatima_khan', 'fatima.khan@example.com', 'password456', 'seller', 'Fatima Khan', '03123456789', '456 Faisal Town, Karachi, Pakistan'),
('ali_zaidi', 'ali.zaidi@example.com', 'password789', 'buyer', 'Ali Zaidi', '03211234567', '789 Gulberg, Islamabad, Pakistan'),
('hassan_abbas', 'hassan.abbas@example.com', 'password101', 'seller', 'Hassan Abbas', '03334567890', '101 Johar Town, Lahore, Pakistan'),
('layla_bashir', 'layla.bashir@example.com', 'password202', 'buyer', 'Layla Bashir', '03451234567', '202 Bahria Town, Rawalpindi, Pakistan'),
('muhammad_akhtar', 'muhammad.akhtar@example.com', 'password303', 'seller', 'Muhammad Akhtar', '03567890123', '303 Model Town, Karachi, Pakistan'),
('sara_shah', 'sara.shah@example.com', 'password404', 'buyer', 'Sara Shah', '03678901234', '404 Green Street, Quetta, Pakistan'),
('bilal_rahman', 'bilal.rahman@example.com', 'password505', 'seller', 'Bilal Rahman', '03789012345', '505 F-10, Islamabad, Pakistan'),
('zahra_jamil', 'zahra.jamil@example.com', 'password606', 'buyer', 'Zahra Jamil', '03890123456', '606 Peshawar Road, Lahore, Pakistan'),
('omar_mirza', 'omar.mirza@example.com', 'password707', 'seller', 'Omar Mirza', '03901234567', '707 Rawalpindi, Pakistan');
ALTER TABLE products ADD COLUMN seller_id INT;

CREATE TABLE sellers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100) -- Consider hashing for security
);
CREATE TABLE addresses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE payment_options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    card_number VARCHAR(20),
    card_holder VARCHAR(100),
    expiry_date VARCHAR(10),
    cvv VARCHAR(4),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

select * from users;
select * from products;
select * from orders;
select * from categories;
select * from cart;
select * from order_products;









