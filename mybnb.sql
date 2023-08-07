Drop TABLE IF EXISTS Users, Listings, Availabilities, Amenities, Bookings;

CREATE TABLE Users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  address VARCHAR(100),
  sin INT(9) UNIQUE,
  date_of_birth DATE, 
  occupation VARCHAR(50),
  credit_card VARCHAR(16)
);

CREATE TABLE Listings (
  list_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  property_type ENUM('house', 'apartment', 'guesthouse', 'hotel') NOT NULL, 
  title VARCHAR(100) NOT NULL,
  description TEXT,
  price_per_night DECIMAL(12, 2),
  address VARCHAR(100),
  city VARCHAR(50),
  country VARCHAR(50),
  postal_code INT(10),
  unit_room_number VARCHAR(30),
  longitude DOUBLE,
  latitude DOUBLE,
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Availabilities (
  avail_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  start_date DATE, 
  end_date DATE,
  list_id INT,
  FOREIGN KEY (list_id) REFERENCES Listings(list_id)
);

CREATE TABLE Amenities (
  amenity_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wifi BOOLEAN, 
  kitchen BOOLEAN, 
  washer BOOLEAN, 
  dryer BOOLEAN, 
  ac BOOLEAN, 
  heating BOOLEAN, 
  workspace BOOLEAN, 
  tv BOOLEAN, 
  hair_dryer BOOLEAN, 
  iron BOOLEAN, 
  smoke_alarm BOOLEAN, 
  carbon_monoxide_alarm BOOLEAN, 
  pool BOOLEAN, 
  free_parking BOOLEAN, 
  crib BOOLEAN, 
  bbq_grill BOOLEAN, 
  indoor_fireplace BOOLEAN, 
  smoking_allowed BOOLEAN, 
  breakfast BOOLEAN, 
  gym BOOLEAN, 
  ev_charger BOOLEAN, 
  hot_tub BOOLEAN,
  list_id INT UNIQUE,
  FOREIGN KEY (list_id) REFERENCES Listings(list_id)
);

CREATE TABLE Bookings (
  booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  is_cancelled BOOLEAN, 
  is_cancelled_by_host BOOLEAN, 
  total_cost DECIMAL(20, 2), 
  start_date DATE, 
  end_date DATE, 
  rate_listing TINYINT, 
  comment_on_listing TEXT, 
  rate_host TINYINT, 
  comment_on_host TEXT, 
  rate_renter TINYINT, 
  comment_on_renter TEXT,
  user_id INT,
  list_id INT,
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (list_id) REFERENCES Listings(list_id),
  KEY user_list_unique (user_id, list_id)
);

-- MOCK DATA: 

-- Set A:
-- RENTERS: (USERID) 7, 8
-- HOST: (USERID) 3, 5 

INSERT INTO Users (name, email, password, address, sin, date_of_birth, occupation, credit_card)
VALUES
  ('John Doe', 'john.doe@example.com', 'password1', '123 Main St', 123456789, '1990-01-15', 'Engineer', '1234567890123456'),
  ('Jane Smith', 'jane.smith@example.com', 'password2', '456 Oak Ave', NULL, '1985-07-20', 'Doctor', '9876543210987654'),
  ('Michael Johnson', 'michael.johnson@example.com', 'password3', '789 Elm Rd', 987654321, '1995-04-10', 'Teacher', '5555444433332222'),
  ('Emily Brown', 'emily.brown@example.com', 'password4', '321 Pine St', NULL, '2000-11-30', 'Student', '1111222233334444'),
  ('William Lee', 'william.lee@example.com', 'password5', '654 Birch Blvd', 111222333, '1978-09-25', 'Lawyer', '9999888877776666'),
  ('Olivia Wilson', 'olivia.wilson@example.com', 'password6', '987 Cedar Ln', NULL, '1992-06-05', 'Artist', '1234123412341234'),
  ('James Anderson', 'james.anderson@example.com', 'password7', '159 Maple Ave', 444333222, '1982-03-12', 'Accountant', '5678567856785678'),
  ('Sophia Martinez', 'sophia.martinez@example.com', 'password8', '852 Oakwood Dr', NULL, '1989-12-18', 'Nurse', '1010101010101010'),
  ('Alexander Taylor', 'alexander.taylor@example.com', 'password9', '753 Walnut St', 999888777, '1998-08-08', 'Chef', '2020202020202020'),
  ('Ella Thomas', 'ella.thomas@example.com', 'password10', '456 Cedar Ln', NULL, '1997-05-22', 'Entrepreneur', '3030303030303030');

-- Mock data for Listings table
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
  ('house', 'Cozy Cottage', 'A lovely cottage in the woods.', 120.50, '1234 Forest Ave', 'Cityville', 'Countryland', 123456, NULL, 12.345678, 34.567890, 1),
  ('apartment', 'City Apartment', 'A modern apartment in the heart of the city.', 89.99, '5678 Downtown St', 'Cityville', 'Countryland', 123456, 'Apt 5B', 23.456789, 45.678901, 2),
  ('guesthouse', 'Serenity Guesthouse', 'A peaceful guesthouse with a beautiful view.', 150.00, '789 Peaceful Rd', 'Villageville', 'Countryland', 654321, NULL, 45.678901, 67.890123, 1),
  ('hotel', 'Luxury Hotel', 'A luxurious hotel with top-notch amenities.', 199.99, '1595 Grand Blvd', 'Cityville', 'Countryland', 789012, 'Suite 202', 56.789012, 78.901234, 4),
  ('apartment', 'Cosy Studio', 'A comfortable studio apartment.', 75.00, '852 Sunny St', 'Townville', 'Countryland', 987654, 'Apt 3', 78.901234, 90.123456, 4),
  ('house', 'Rustic Retreat', 'A charming rustic house in the countryside.', 110.00, '753 Meadow Rd', 'Villageville', 'Countryland', 321654, NULL, 90.123456, 12.345678, 1),
  ('guesthouse', 'Tranquil Hideaway', 'A tranquil guesthouse by the lake.', 135.00, '951 Lakeview Dr', 'Townville', 'Countryland', 456789, NULL, 67.890123, 23.456789, 3),
  ('hotel', 'City Luxury Hotel', 'A luxurious hotel in the heart of the city.', 189.99, '456 High Street', 'Cityville', 'Countryland', 654321, 'Suite 101', 78.901234, 34.567890, 1),
  ('apartment', 'Modern Studio', 'A sleek and modern studio apartment.', 80.00, '789 Urban Ave', 'Cityville', 'Countryland', 987654, 'Apt 7', 90.123456, 45.678901, 9),
  ('house', 'Spacious Family Home', 'A spacious house perfect for families.', 150.00, '753 Family Rd', 'Villageville', 'Countryland', 123456, NULL, 12.345678, 56.789012, 10);

-- Mock data for Availabilities table
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-02-01', '2023-11-10', 1),
('2023-02-15', '2023-12-25', 2),
('2023-02-20', '2023-11-25', 3),
('2023-02-05', '2023-12-12', 4),
('2023-02-10', '2023-11-15', 5),
('2023-02-18', '2023-12-22', 6),
('2023-02-25', '2023-11-01', 7),
('2023-02-10', '2023-12-17', 8),
('2023-02-15', '2023-11-22', 9),
('2023-02-01', '2023-12-08', 10);

-- Mock data for Amenities table
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
  (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1),
  (1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2),
  (1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 3),
  (1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 4),
  (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 5),
  (1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 6),
  (1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 7),
  (1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 8),
  (1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 9),
  (1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 10);

-- Mock data for Bookings table
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 300.00, '2023-08-05', '2023-08-08', 4, 'Great stay!', 5, 'Wonderful host!', 4, 'Enjoyed the stay!', 1, 1),
(false, false, 400.00, '2023-09-18', '2023-09-22', 5, 'Fantastic place!', 4, 'Very helpful host!', 5, 'Highly recommended!', 2, 2),
(true, false, 240.00, '2023-08-25', '2023-08-29', 3, 'Good place!', 4, 'Friendly host!', 3, 'Had a nice time!', 4, 3),
(false, false, 900.00, '2023-09-10', '2023-09-17', 5, 'Amazing stay!', 5, 'Superb host!', 5, 'Would come again!', 4, 4),
(false, false, 360.00, '2023-08-15', '2023-08-19', 4, 'Nice place!', 4, 'Helpful host!', 4, 'Enjoyed the trip!', 7, 5),
(false, false, 500.00, '2023-09-20', '2023-09-27', 5, 'Lovely place!', 5, 'Great host!', 5, 'Had a wonderful time!', 6, 6),
(true, false, 120.00, '2023-08-30', '2023-09-02', 3, 'Decent place!', 4, 'Nice host!', 3, 'It was okay!', 7, 7),
(false, false, 450.00, '2023-09-15', '2023-09-19', 4, 'Comfortable stay!', 5, 'Helpful host!', 4, 'Enjoyed the trip!', 8, 8),
(false, false, 204.00, '2023-08-20', '2023-08-24', 3, 'Good experience!', 4, 'Friendly host!', 3, 'Had a nice stay!', 9, 9),
(false, false, 550.00, '2023-09-05', '2023-09-12', 5, 'Excellent place!', 5, 'Amazing host!', 5, 'Highly recommended!', 10, 10);

-- SET A.2

INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('apartment', 'Downtown Loft', 'Modern loft in the heart of downtown', 180.00, '123 Elm St', 'New York', 'USA', 56789, 'Unit 7B', -74.123456, 40.678910, 3),
('house', 'Seaside Retreat', 'A cozy house near the beach', 130.00, '789 Oak St', 'Miami', 'USA', 56789, 'House 2', -80.123456, 25.678910, 4),
('apartment', 'City Center Studio', 'Charming studio in the city center', 120.00, '123 Pine St', 'Paris', 'France', 90123, 'Unit 4C', 2.123456, 48.678910, 6),
('guesthouse', 'Mountain Getaway', 'Escape to the mountains in this guesthouse', 110.00, '456 Oak St', 'Vancouver', 'Canada', 23456, 'Guesthouse 1', -123.123456, 49.678910, 3),
('hotel', 'Urban Oasis', 'Experience luxury living in the city', 280.00, '789 Pine St', 'Berlin', 'Germany', 23456, 'Suite 202', 13.123456, 52.678910, 9),
('house', 'Countryside Cabin', 'Rustic cabin surrounded by nature', 150.00, '456 Elm St', 'London', 'UK', 67890, 'Cabin 3', 0.987654, 51.345678, 10),
('apartment', 'Skyline View Condo', 'Enjoy stunning views from this condo', 220.00, '789 Maple St', 'New York', 'USA', 56789, 'Unit 10A', -74.123456, 40.678910, 1),
('guesthouse', 'Tranquil Haven', 'Find peace and tranquility in this guesthouse', 120.00, '123 Oak St', 'Tokyo', 'Japan', 89012, 'Guesthouse 2', 139.123456, 35.678910, 2),
('hotel', 'Historic Inn', 'Stay in a charming historic inn', 190.00, '456 Pine St', 'Paris', 'France', 90123, 'Room 203', 2.123456, 48.678910, 4),
('house', 'Rural Farmhouse', 'Experience the rural charm of this farmhouse', 160.00, '789 Elm St', 'Berlin', 'Germany', 23456, 'Farmhouse 5', 13.123456, 52.678910, 3);


INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-01-10', '2023-12-18', 11),
('2023-01-15', '2023-12-25', 12),
('2023-01-20', '2023-12-28', 13),
('2023-01-01', '2023-12-08', 14),
('2023-01-25', '2023-12-02', 15),
('2023-01-10', '2023-12-18', 16),
('2023-01-05', '2023-12-12', 17),
('2023-01-20', '2023-12-28', 18),
('2023-01-15', '2023-12-23', 19),
('2023-01-05', '2023-12-13', 20);

INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 270.00, '2023-01-10', '2023-02-15', 4, 'Enjoyed the stay!', 5, 'Great host!', 4, 'Highly recommended!', 4, 11),
(false, false, 390.00, '2023-01-25', '2023-02-28', 5, 'Fantastic place!', 4, 'Very accommodating host!', 5, 'Had a wonderful time!', 7, 12),
(false, false, 240.00, '2023-03-05', '2023-03-12', 3, 'Good experience!', 4, 'Friendly host!', 3, 'Had a nice stay!', 10, 13),
(false, false, 660.00, '2023-09-18', '2023-09-25', 5, 'Amazing stay!', 5, 'Superb host!', 5, 'Would come again!', 9, 14),
(false, false, 320.00, '2023-08-02', '2023-08-08', 4, 'Nice place!', 4, 'Helpful host!', 4, 'Enjoyed the trip!', 6, 15),
(true, false, 100.00, '2023-07-08', '2023-07-12', 3, 'Decent place!', 4, 'Nice host!', 3, 'It was okay!', 1, 16),
(false, false, 410.00, '2023-08-23', '2023-08-28', 4, 'Comfortable stay!', 5, 'Helpful host!', 4, 'Enjoyed the trip!', 2, 17),
(false, false, 220.00, '2023-07-30', '2023-08-05', 3, 'Good place!', 4, 'Friendly host!', 3, 'Had a nice time!', 8, 18),
(false, false, 360.00, '2023-07-17', '2023-07-25', 4, 'Nice place!', 4, 'Helpful host!', 4, 'Enjoyed the trip!', 6, 19),
(false, false, 420.00, '2023-08-10', '2023-08-18', 5, 'Excellent place!', 5, 'Amazing host!', 5, 'Highly recommended!', 4, 20);

INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 11), -- Downtown Loft
(true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, 12), -- Seaside Retreat
(true, true, false, false, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 13), -- City Center Studio
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 14), -- Mountain Getaway
(true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, true, false, false, false, 15), -- Urban Oasis
(true, false, true, true, true, true, false, true, false, true, true, true, false, true, false, false, false, false, true, false, false, false, 16), -- Countryside Cabin
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 17), -- Skyline View Condo
(true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, true, false, false, false, 18), -- Tranquil Haven
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 19), -- Historic Inn
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 20); -- Rural Farmhouse

-- SET FOR TESTING REPORTS:

-- Listings for Host with user_id 1 in the same country (different city and different postal code (but nearby))
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('apartment', 'City Skyline View', 'Stunning views of the city skyline', 180.00, '123 Elm St', 'New York', 'USA', 56781, 'Unit 7A', -122.123456, 47.678910, 1),
('house', 'Lakefront Retreat', 'Relax by the lake in this cozy house', 150.00, '789 Oak St', 'Seattle', 'USA', 56789, 'House 5', -122.123456, 47.678910, 1),
('apartment', 'Urban Oasis', 'Modern apartment with urban charm', 200.00, '456 Pine St', 'Bridgerton', 'USA', 56720, 'Unit 10B', -122.123456, 47.678910, 1),
('house', 'Mountain Cabin', 'Escape to the mountains in this cabin', 130.00, '789 Maple St', 'Sattleland', 'USA', 56710, 'Cabin 1', -122.123456, 47.678910, 1),
('apartment', 'Downtown Luxury', 'Luxurious apartment in downtown area', 220.00, '123 Oak St', 'Bordstorm', 'USA', 56760, 'Unit 3D', -122.123456, 47.678910, 1);

-- Amenities for Listings (User ID: 1, Same Country)
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 21), -- City Skyline View
(true, false, true, true, true, true, true, true, false, true, true, true, false, true, true, false, false, false, true, false, false, false, 22), -- Lakefront Retreat
(true, true, true, true, false, true, true, false, true, true, false, true, false, true, false, true, false, false, true, false, false, false, 23), -- Urban Oasis
(true, true, false, true, true, true, false, true, true, true, true, true, false, true, false, true, false, false, true, false, false, false, 24), -- Mountain Cabin
(false, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 25); -- Downtown Luxury

-- Availabilities for Listings (User ID: 1, Same Country)
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-08-01', '2023-08-10', 21),
('2023-09-15', '2023-09-25', 22),
('2023-08-20', '2023-08-25', 23),
('2023-09-05', '2023-09-12', 24),
('2023-08-10', '2023-08-15', 25),
('2023-01-01', '2024-12-10', 21),
('2023-01-15', '2024-12-25', 22),
('2023-01-20', '2024-12-25', 23),
('2023-01-05', '2024-12-12', 24),
('2023-01-10', '2024-12-15', 25);

-- Bookings for Listings (User ID: 1, Same Country)
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 540.00, '2023-08-05', '2023-08-08', 4, 'Great stay!', 5, 'Wonderful host!', 4, 'Enjoyed the stay!', 2, 21),
(false, false, 450.00, '2023-09-18', '2023-09-22', 5, 'Fantastic place!', 4, 'Very helpful host!', 5, 'Highly recommended!', 2, 22),
(true, false, 600.00, '2023-08-25', '2023-08-29', 3, 'Good place!', 4, 'Friendly host!', 3, 'Had a nice time!', 7, 23),
(false, false, 520.00, '2023-09-10', '2023-09-17', 5, 'Amazing stay!', 5, 'Superb host!', 5, 'Would come again!', 7, 24),
(false, false, 1100.00, '2023-08-15', '2023-08-19', 4, 'Nice place!', 4, 'Helpful host!', 4, 'Enjoyed the trip!', 7, 25);

-- Listings for Host with user_id 2 in the same city and country
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('apartment', 'City Center Loft 2', 'Chic loft in the heart of New York', 250.00, '123 Pine St', 'New York', 'USA', 90125, 'Unit 5C', -74.123451, 40.678916, 2),
('house', 'Cozy Urban Home 2', 'Comfortable house in the heart of the city', 180.00, '456 Oak St', 'New York', 'USA', 90121, 'House 3', -74.123459, 40.678915, 2),
('apartment', 'Central Studio 2', 'Modern studio apartment in downtown', 190.00, '789 Maple St', 'New York', 'USA', 90128, 'Unit 8B', -74.123453, 40.678914, 2),
('apartment', 'Luxury Penthouse', 'Opulent penthouse in the city center', 450.00, '456 Pine St', 'New York', 'USA', 90129, 'Penthouse 2', -74.123451, 40.678912, 2);

-- Amenities for Listings (User ID: 2, Same City and Country)
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 26), -- City Center Loft
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 27), -- Cozy Urban Home
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false, false, false, true, false, false, false, 28), -- Central Studio
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, true, false, false, true, false, false, false, 29); -- Luxury Penthouse

-- Availabilities for Listings (User ID: 2, Same City and Country)
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-07-10', '2023-11-18', 26),
('2023-08-15', '2023-10-25', 27),
('2023-07-20', '2023-09-28', 28),
('2023-07-25', '2023-11-02', 29);

-- Bookings for Listings (User ID: 2, Same City and Country)
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 750.00, '2023-07-08', '2023-07-12', 4, 'Enjoyed the stay!', 5, 'Great host!', 4, 'Highly recommended!', 8, 26),
(false, false, 540.00, '2023-07-15', '2023-07-23', 5, 'Fantastic place!', 4, 'Helpful host!', 5, 'Would come again!', 7, 27),
(false, false, 570.00, '2023-07-25', '2023-08-01', 4, 'Nice stay!', 4, 'Friendly host!', 4, 'Had a good time!', 7, 28),
(false, false, 1800.00, '2023-07-28', '2023-08-04', 5, 'Lovely place!', 5, 'Wonderful host!', 5, 'Great communication!', 8, 29);

-- Listings for Host with user_id 3 in the same postal code in the USA
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('apartment', 'Cozy Urban Studio', 'Charming studio in the heart of the city', 110.00, '123 Oak St', 'Seattle', 'USA', 56789, 'Unit 2A', -122.123456, 47.678910, 3),
('house', 'Modern Townhouse', 'Contemporary townhouse with all amenities', 160.00, '123 Oak St', 'Seattle', 'USA', 56789, 'Townhouse 3', -122.123456, 47.678910, 3),
('apartment', 'Riverside Retreat', 'Relax by the river in this serene apartment', 140.00, '123 Oak St', 'Seattle', 'USA', 56789, 'Unit 6B', -122.123456, 47.678910, 3);

-- Amenities for Listings (User ID: 3, Same Postal Code in the USA)
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 30), -- Cozy Urban Studio
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 31), -- Modern Townhouse
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 32); -- Riverside Retreat

-- Availabilities for Listings (User ID: 3, Same Postal Code in the USA)
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-07-10', '2023-12-15', 30),
('2023-08-20', '2023-11-28', 31),
('2023-09-05', '2023-12-10', 32);

-- Bookings rented by User ID: 4
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 330.00, '2023-07-12', '2023-07-18', 4, 'Great place!', 5, 'Excellent host!', 4, 'Enjoyed the stay!', 4, 30),
(false, false, 960.00, '2023-08-22', '2023-08-27', 5, 'Amazing property!', 5, 'Superb host!', 5, 'Would recommend!', 4, 31),
(false, false, 420.00, '2023-09-08', '2023-09-12', 4, 'Nice stay!', 4, 'Friendly host!', 4, 'Had a good time!', 4, 32);

-- Additional Bookings for Listings with IDs 30, 31, and 32
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 440.00, '2023-07-20', '2023-07-25', 4, 'Cozy and comfortable', 5, 'Very accommodating host', 4, 'Had a great time!', 4, 30),
(true, true, 800.00, '2023-08-25', '2023-08-30', 5, 'Perfect getaway', 5, 'Friendly and helpful', 5, 'Definitely coming back!', 7, 31),
(true, false, 360.00, '2023-09-15', '2023-09-20', 4, 'Nice location', 4, 'Responsive host', 4, 'Enjoyed the stay!', 4, 32),
(false, false, 660.00, '2023-07-05', '2023-07-10', 4, 'Lovely apartment', 5, 'Great communication', 4, 'Highly recommend!', 6, 30),
(false, false, 780.00, '2023-08-15', '2023-08-22', 5, 'Wonderful experience', 5, 'Excellent service', 5, 'Absolutely loved it!', 4, 31),
(true, false, 300.00, '2023-09-25', '2023-09-30', 4, 'Pleasant stay', 4, 'Attentive host', 4, 'Had a good time!', 6, 32),
(false, false, 510.00, '2023-07-28', '2023-08-03', 4, 'Clean and cozy', 5, 'Helpful and friendly', 4, 'Would stay again!', 4, 30),
(false, true, 720.00, '2023-08-10', '2023-08-18', 5, 'Great property', 5, 'Superb hospitality', 5, 'Highly recommended!', 7, 31),
(true, true, 380.00, '2023-09-18', '2023-09-25', 4, 'Relaxing stay', 4, 'Responsive host', 4, 'Enjoyed every moment!', 4, 32),
(false, false, 600.00, '2023-07-15', '2023-07-22', 4, 'Good location', 5, 'Welcoming host', 4, 'Had a pleasant time!', 7, 30);

-- Listings for Host with user_id 3 in Japan (IDs 33 to 40)
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('apartment', 'Sakura Blossom Studio', 'Cozy studio with Sakura views', 90.00, '123 Sakura St', 'Tokyo', 'Japan', 12345, 'Unit 2C', 139.123456, 35.678910, 3),
('house', 'Kyoto Garden Retreat', 'Traditional house near Kyoto temples', 120.00, '456 Zen St', 'Kyoto', 'Japan', 56789, 'House 7', 135.123456, 35.678910, 3),
('apartment', 'Osaka Urban Oasis', 'Modern apartment in downtown Osaka', 110.00, '789 Umeda St', 'Osaka', 'Japan', 90123, 'Unit 15B', 135.123456, 34.678910, 3),
('house', 'Nara Serenity', 'Tranquil house in the heart of Nara', 100.00, '456 Deer St', 'Nara', 'Japan', 23456, 'House 2', 135.123456, 34.123456, 3),
('apartment', 'Hiroshima Skyline View', 'Stunning views of Hiroshima skyline', 130.00, '123 Peace St', 'Hiroshima', 'Japan', 67890, 'Unit 11A', 132.123456, 34.678910, 4),
('house', 'Fukuoka Coastal Getaway', 'Relax by the coast in Fukuoka', 140.00, '789 Beach St', 'Fukuoka', 'Japan', 34567, 'House 3', 130.123456, 33.678910, 4),
('apartment', 'Sapporo Snowy Retreat', 'Cozy apartment in snowy Sapporo', 100.00, '123 Snow St', 'Sapporo', 'Japan', 78901, 'Unit 5D', 141.123456, 43.678910, 4),
('house', 'Okinawa Beach House', 'Beachfront house in Okinawa paradise', 160.00, '456 Coral St', 'Okinawa', 'Japan', 89012, 'House 1', 127.123456, 26.123456, 5);

-- Amenities for Listings (User ID: 3, Japan)
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 33), -- Sakura Blossom Studio
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 34), -- Kyoto Garden Retreat
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 35), -- Osaka Urban Oasis
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 36), -- Nara Serenity
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 37), -- Hiroshima Skyline View
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 38), -- Fukuoka Coastal Getaway
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 39), -- Sapporo Snowy Retreat
(true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, false, false, false, true, false, false, false, 40); -- Okinawa Beach House

-- Availabilities for Listings (User ID: 3, Japan)
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2022-09-10', '2022-09-15', 33),
('2022-09-20', '2022-09-25', 33),
('2022-10-05', '2022-10-10', 33),
('2022-11-15', '2022-11-20', 33),
('2022-11-25', '2022-11-30', 33),
('2022-09-10', '2022-09-15', 34),
('2022-10-05', '2022-10-10', 34),
('2022-10-15', '2022-10-20', 34),
('2022-11-10', '2022-11-15', 34),
('2022-11-20', '2022-11-25', 34),
('2022-09-15', '2022-09-20', 35),
('2022-09-25', '2022-09-30', 35),
('2022-10-15', '2022-10-20', 35),
('2022-11-05', '2022-11-10', 35),
('2022-11-15', '2022-11-20', 35),
('2022-09-05', '2022-09-10', 36),
('2022-09-15', '2022-09-20', 36),
('2022-10-10', '2022-10-15', 36),
('2022-11-10', '2022-11-15', 36),
('2022-11-25', '2022-11-30', 36),
('2022-09-10', '2022-09-15', 37),
('2022-10-05', '2022-10-10', 37),
('2022-10-20', '2022-10-25', 37),
('2022-11-05', '2022-11-10', 37),
('2022-11-20', '2022-11-25', 37),
('2022-09-15', '2022-09-20', 38),
('2022-09-25', '2022-09-30', 38),
('2022-10-15', '2022-10-20', 38),
('2022-11-10', '2022-11-15', 38),
('2022-11-20', '2022-11-25', 38),
('2022-09-10', '2022-09-15', 39),
('2022-09-20', '2022-09-25', 39),
('2022-10-05', '2022-10-10', 39),
('2022-11-15', '2022-11-20', 39),
('2022-11-25', '2022-11-30', 39),
('2022-09-15', '2022-09-20', 40),
('2022-09-25', '2022-09-30', 40),
('2022-10-10', '2022-10-15', 40),
('2022-11-10', '2022-11-15', 40),
('2022-11-20', '2022-11-25', 40);

-- Bookings rented by User ID: 4 (within the given time range)
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 450.00, '2022-09-12', '2022-09-18', 4, 'Great place!', 5, 'Excellent host!', 4, 'Enjoyed the stay!', 4, 33),
(false, false, 720.00, '2022-10-05', '2022-10-12', 5, 'Amazing property!', 5, 'Superb host!', 5, 'Would recommend!', 4, 34),
(false, false, 330.00, '2022-11-08', '2022-11-12', 4, 'Nice stay!', 4, 'Friendly host!', 4, 'Had a good time!', 4, 35),
(false, false, 300.00, '2022-09-08', '2022-09-12', 4, 'Comfortable stay!', 5, 'Friendly host!', 4, 'Had a good time!', 4, 36),
(false, false, 420.00, '2022-10-18', '2022-10-25', 4, 'Nice stay!', 4, 'Friendly host!', 4, 'Had a good time!', 4, 37),
(false, false, 250.00, '2022-11-10', '2022-11-15', 3, 'Decent stay.', 4, 'Friendly host!', 4, 'Had a good time!', 4, 38),
(false, false, 380.00, '2022-09-15', '2022-09-20', 4, 'Good place.', 4, 'Friendly host!', 4, 'Had a good time!', 4, 39),
(false, false, 480.00, '2022-09-25', '2022-09-30', 5, 'Excellent stay!', 5, 'Great host!', 5, 'Would come back!', 4, 40);

-- Bookings rented by User IDs: 6, 7, 8, 9 (within the given time range)
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 420.00, '2022-09-15', '2022-09-20', 4, 'Nice stay!', 4, 'Friendly host!', 4, 'Had a good time!', 6, 33),
(false, false, 550.00, '2022-10-08', '2022-10-15', 4, 'Good property.', 4, 'Great host!', 4, 'Enjoyed the stay!', 6, 34),
(false, false, 290.00, '2022-11-12', '2022-11-18', 3, 'Decent stay.', 4, 'Friendly host!', 4, 'Had a good time!', 6, 35),
(false, false, 280.00, '2022-09-22', '2022-09-27', 3, 'Average property.', 3, 'Friendly host!', 3, 'Had a decent time!', 9, 36),
(false, false, 400.00, '2022-10-25', '2022-10-31', 4, 'Great stay!', 5, 'Excellent host!', 4, 'Enjoyed the stay!', 6, 37),
(false, false, 210.00, '2022-11-18', '2022-11-24', 3, 'Average experience.', 3, 'Friendly host!', 3, 'Had a decent time!', 6, 38),
(false, false, 340.00, '2022-09-20', '2022-09-25', 4, 'Nice place!', 4, 'Friendly host!', 4, 'Had a good time!', 6, 39),
(false, false, 450.00, '2022-09-28', '2022-10-05', 5, 'Excellent property!', 5, 'Superb host!', 5, 'Would recommend!', 9, 40);

-- SET B: (10 more users, listings, avail, bookings, amenities)

-- Users Table (10)
INSERT INTO Users (name, email, password, address, sin, date_of_birth, occupation, credit_card)
VALUES
('Mary Johnson', 'mary.johnson@example.com', 'passmary', '789 Elm Rd, City', 258369147, '1985-12-03', 'Accountant', '2583691458247856'),
('Robert Smith', 'robert.smith@example.com', 'smithpass', '456 Oak Ave, Town', 987654333, '1979-06-10', 'Engineer', '9876543298761482'),
('Karen Williams', 'karen.williams@example.com', 'williamspass', '123 Main St, Village', 789456122, '1990-09-20', 'Teacher', '7894561478523698'),
('Daniel Brown', 'daniel.brown@example.com', 'browndan', '654 Maple St, City', 369852147, '1988-07-18', 'Lawyer', '3698521436985214'),
('Laura Lee', 'laura.lee@example.com', 'leelaura', '789 Birch Rd, Town', 456123789, '1993-04-26', 'Doctor', '4561237845612398'),
('William Chen', 'william.chen@example.com', 'chenwill', '234 Oak Ave, Village', 789456123, '1995-11-12', 'Designer', '7894561278945612'),
('Grace Kim', 'grace.kim@example.com', 'kimgrace', '456 Elm Rd, City', 123455789, '1987-08-30', 'Engineer', '1234567878945612'),
('Joseph Liu', 'joseph.liu@example.com', 'liujoseph', '789 Birch Rd, Village', 147852369, '1992-05-08', 'Chef', '1478523614785236'),
('Olivia Wang', 'olivia.wang@example.com', 'wangolivia', '123 Oak Ave, Town', 258963147, '1989-03-14', 'Engineer', '2589631425896324'),
('Ethan Smith', 'ethan.smith@example.com', 'smithethan', '654 Maple St, Village', 321654987, '1991-02-22', 'Teacher', '3216549832165498');

-- Listings Table (40)
INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('house', 'Elegant Mansion with Pool', 'An elegant mansion with a private pool.', 350.00, '789 Elm Rd, City', 'City', 'Country', 74185, 'Mansion 789', -73.123456, 40.654321, 18),
('apartment', 'Sunny Apartment with Balcony', 'A sunny apartment with a cozy balcony.', 100.00, '456 Oak Ave, Town', 'Town', 'Country', 98765, 'Unit 201', -72.987654, 40.123456, 19),
('hotel', 'Luxury Hotel Suite with Spa', 'A luxurious suite with spa facilities.', 300.00, '123 Main St, Village', 'Village', 'Country', 12345, 'Suite 101', -71.246813, 41.135792, 20),
('apartment', 'Modern Studio Apartment', 'A modern studio apartment with all amenities.', 80.00, '321 Pine St, City', 'City', 'Country', 87654, 'Unit 203', -74.369258, 39.582461, 14),
('house', 'Cozy Cottage by the Lake', 'A cozy cottage with a beautiful lake view.', 120.00, '789 Birch Rd, Town', 'Town', 'Country', 76543, 'Cottage 789', -75.582461, 38.369258, 15),
('guesthouse', 'Tranquil Guesthouse', 'A tranquil guesthouse for a peaceful stay.', 90.00, '234 Oak Ave, City', 'City', 'Country', 45678, 'Guesthouse 234', -71.951753, 40.246813, 16),
('apartment', 'Charming Loft Apartment', 'A charming loft apartment with artistic vibes.', 70.00, '654 Maple St, Village', 'Village', 'Country', 24680, 'Unit 302', -73.456789, 41.369258, 17),
('house', 'Rustic Countryside Retreat', 'A rustic house nestled in the countryside.', 200.00, '456 Elm Rd, Town', 'Town', 'Country', 98374, 'House 456', -72.246813, 40.975310, 18),
('guesthouse', 'Cozy Garden Guesthouse', 'A cozy guesthouse surrounded by a lush garden.', 80.00, '789 Birch Rd, City', 'City', 'Country', 11111, 'Guesthouse 789', -74.123456, 39.654321, 19),
('apartment', 'Modern City Apartment', 'A modern apartment in the heart of the city.', 110.00, '123 Oak Ave, Village', 'Village', 'Country', 22222, 'Unit 405', -73.975310, 40.246813, 20),
('house', 'Cozy House with Garden', 'A beautiful house with a lovely garden.', 120.00, '123 Main St', 'New York', 'USA', 10001, 'Unit 1', -74.005941, 40.712776, 18),
('apartment', 'Modern Downtown Apartment', 'A stylish and modern apartment in the heart of the city.', 150.00, '456 Elm St', 'San Francisco', 'USA', 94105, 'Unit 2A', -122.419418, 37.774929, 19),
('guesthouse', 'Charming Guesthouse Retreat', 'A cozy guesthouse perfect for a relaxing getaway.', 80.00, '789 Oak St', 'Los Angeles', 'USA', 90001, 'Unit B', -118.243683, 34.052235, 20),
('hotel', 'Luxurious City Hotel', 'An elegant and luxurious hotel in the city center.', 250.00, '101 Maple Ave', 'Chicago', 'USA', 60601, 'Suite 500', -87.629798, 41.878114, 14),
('house', 'Spacious Family Home', 'A spacious house ideal for families and groups.', 180.00, '202 Pine St', 'Seattle', 'USA', 98101, 'Unit 3', -122.332071, 47.606209, 15),
('apartment', 'Modern Loft Apartment', 'A contemporary loft apartment with a city view.', 200.00, '303 Birch St', 'Boston', 'USA', 02108, 'Unit 5D', -71.058880, 42.360082, 16),
('guesthouse', 'Rustic Mountain Retreat', 'A rustic guesthouse nestled in the mountains.', 100.00, '404 Cedar St', 'Denver', 'USA', 80202, 'Cabin 2', -104.990251, 39.739236, 17),
('hotel', 'Historic Grand Hotel', 'A grand and historic hotel with classic charm.', 300.00, '505 Walnut St', 'New Orleans', 'USA', 70112, 'Suite 800', -90.071532, 29.951066, 18),
('house', 'Seaside Cottage', 'A charming cottage by the beach with ocean views.', 160.00, '606 Cherry St', 'Miami', 'USA', 33130, 'Unit 4', -80.191790, 25.761680, 19),
('apartment', 'Urban Studio Apartment', 'A cozy studio apartment in the heart of downtown.', 70.00, '707 Elm St', 'Austin', 'USA', 78701, 'Unit 7B', -97.743060, 30.267153, 20),
('house', 'Comfy Family House', 'A comfortable and welcoming house for families.', 140.00, '808 Cedar St', 'San Diego', 'USA', 92101, 'Unit 9', -117.161084, 32.715736, 18),
('apartment', 'City Center Penthouse', 'A luxurious penthouse with stunning city views.', 300.00, '909 Maple Ave', 'Portland', 'USA', 97205, 'Unit 12E', -122.676483, 45.523064, 19),
('guesthouse', 'Tranquil Garden Retreat', 'A serene and peaceful guesthouse surrounded by gardens.', 90.00, '1010 Oak St', 'Nashville', 'USA', 37201, 'Unit C', -86.781601, 36.162664, 20),
('hotel', 'Elegant Boutique Hotel', 'An elegant boutique hotel offering personalized service.', 220.00, '1111 Pine St', 'Las Vegas', 'USA', 89109, 'Suite 200', -115.139830, 36.169941, 14),
('house', 'Sunny Beach House', 'A sunny house located just steps away from the beach.', 190.00, '1212 Birch St', 'Myrtle Beach', 'USA', 29577, 'Unit 3', -78.886694, 33.689060, 15),
('apartment', 'Chic Urban Apartment', 'A chic and modern apartment in a lively neighborhood.', 180.00, '1313 Cedar St', 'Philadelphia', 'USA', 19106, 'Unit 6A', -75.165222, 39.952583, 16),
('guesthouse', 'Mountain View Retreat', 'A cozy guesthouse with stunning mountain views.', 80.00, '1414 Walnut St', 'Asheville', 'USA', 28801, 'Cabin 1', -82.554016, 35.595058, 17),
('hotel', 'Historic Downtown Inn', 'A historic inn located in the heart of the city.', 250.00, '1515 Elm St', 'Charleston', 'USA', 29401, 'Suite 300', -79.931051, 32.776475, 18),
('house', 'Riverside Cottage', 'A charming cottage by the river with a peaceful ambiance.', 170.00, '1616 Pine St', 'Savannah', 'USA', 31401, 'Unit 5', -81.080732, 32.083541, 19),
('apartment', 'Cozy Loft Studio', 'A cozy loft-style studio apartment with modern amenities.', 75.00, '1717 Oak St', 'Orlando', 'USA', 32801, 'Unit 8B', -81.379234, 28.538336, 20),
('house', 'Modern Lakeside Home', 'A modern house with beautiful lakeside views.', 200.00, '1818 Cedar St', 'Lake Tahoe', 'USA', 89449, 'Unit 10', -119.977186, 38.939926, 18),
('apartment', 'City Skyline View Apartment', 'An apartment with stunning views of the city skyline.', 220.00, '1919 Maple Ave', 'San Antonio', 'USA', 78205, 'Unit 15F', -98.493628, 29.424122, 19),
('guesthouse', 'Tranquil Forest Hideaway', 'A tranquil guesthouse tucked away in the forest.', 100.00, '2020 Oak St', 'Aspen', 'USA', 81611, 'Unit D', -106.822745, 39.191097, 20),
('hotel', 'Luxury Resort & Spa', 'A luxurious resort and spa with top-notch amenities.', 350.00, '2121 Pine St', 'Hawaii', 'USA', 96815, 'Suite 1000', -157.858333, 21.306944, 14),
('house', 'Secluded Mountain Cabin', 'A secluded cabin surrounded by mountains and nature.', 190.00, '2222 Cedar St', 'Boulder', 'USA', 80302, 'Cabin 3', -105.270546, 40.015000, 15),
('apartment', 'Downtown Luxury Suite', 'A luxurious suite in the heart of downtown.', 250.00, '2323 Elm St', 'Salt Lake City', 'USA', 84101, 'Unit 20D', -111.891047, 40.760779, 16),
('guesthouse', 'Garden Cottage Retreat', 'A charming garden cottage retreat with a serene atmosphere.', 120.00, '2424 Oak St', 'Portland', 'USA', 97209, 'Unit E', -122.676207, 45.520247, 17),
('hotel', 'Elegant Beachfront Resort', 'An elegant resort located right on the beachfront.', 320.00, '2525 Pine St', 'Miami Beach', 'USA', 33139, 'Suite 400', -80.130045, 25.790654, 18),
('house', 'Cozy Country Cabin', 'A cozy cabin in the countryside, perfect for a getaway.', 150.00, '2626 Birch St', 'Carmel', 'USA', 93923, 'Unit 6', -121.935791, 36.555239, 19),
('apartment', 'Urban Chic Loft', 'An urban chic loft with a trendy and artistic ambiance.', 180.00, '2727 Cedar St', 'Kansas City', 'USA', 64105, 'Unit 10C', -94.578566, 39.099724, 20);


-- Bookings Table (100)
INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(true, false, 150.00, '2023-08-05', '2023-08-10', 4, 'Nice place!', 5, 'Host was friendly.', 5, 'Great experience!', 11, 1),
(false, false, 220.00, '2023-08-15', '2023-08-20', 5, 'Amazing view!', 4, 'Host was helpful.', 5, 'Loved the stay!', 12, 2),
(false, true, 80.00, '2023-08-01', '2023-08-03', 3, 'Cozy place!', 4, 'Host was accommodating.', 4, 'Had a good time.', 13, 3),
(true, false, 300.00, '2023-08-10', '2023-08-15', 4, 'Beautiful house!', 4, 'Host was welcoming.', 4, 'Nice host!', 14, 4),
(false, false, 120.00, '2023-08-12', '2023-08-15', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 5),
(true, true, 90.00, '2023-08-07', '2023-08-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 6),
(false, false, 200.00, '2023-08-20', '2023-08-25', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 7),
(false, false, 110.00, '2023-08-22', '2023-08-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 8),
(true, false, 180.00, '2023-08-10', '2023-08-15', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 9),
(false, true, 70.00, '2023-08-25', '2023-08-28', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 10),
(true, false, 130.00, '2023-08-05', '2023-08-11', 4, 'Nice view!', 5, 'Host was friendly.', 5, 'Enjoyed the stay.', 11, 1),
(false, false, 250.00, '2023-08-15', '2023-08-21', 5, 'Beautiful place!', 4, 'Host was helpful.', 5, 'Had a great time.', 12, 2),
(false, true, 100.00, '2023-08-01', '2023-08-04', 3, 'Cozy and comfortable!', 4, 'Host was accommodating.', 4, 'Good stay!', 13, 3),
(true, false, 280.00, '2023-08-12', '2023-08-17', 4, 'Lovely house!', 4, 'Host was welcoming.', 4, 'Great experience!', 14, 4),
(false, false, 110.00, '2023-08-16', '2023-08-20', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 5),
(true, true, 80.00, '2023-08-07', '2023-08-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 6),
(false, false, 190.00, '2023-08-18', '2023-08-24', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 7),
(false, false, 100.00, '2023-08-21', '2023-08-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 8),
(true, false, 160.00, '2023-08-11', '2023-08-16', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 9),
(false, true, 65.00, '2023-08-26', '2023-08-30', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 10),
(false, false, 130.00, '2023-08-05', '2023-08-10', 4, 'Nice place!', 5, 'Host was friendly.', 5, 'Great experience!', 11, 20),
(false, false, 200.00, '2023-08-15', '2023-08-20', 5, 'Amazing view!', 4, 'Host was helpful.', 5, 'Loved the stay!', 12, 21),
(false, true, 80.00, '2023-08-01', '2023-08-03', 3, 'Cozy place!', 4, 'Host was accommodating.', 4, 'Had a good time.', 13, 22),
(true, false, 300.00, '2023-08-10', '2023-08-15', 4, 'Beautiful house!', 4, 'Host was welcoming.', 4, 'Nice host!', 14, 23),
(false, false, 120.00, '2023-08-12', '2023-08-15', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 24),
(true, true, 90.00, '2023-08-07', '2023-08-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 25),
(false, false, 200.00, '2023-08-20', '2023-08-25', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 26),
(false, false, 110.00, '2023-08-22', '2023-08-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 27),
(true, false, 180.00, '2023-08-10', '2023-08-15', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 28),
(false, true, 70.00, '2023-08-25', '2023-08-28', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 29),
(false, false, 130.00, '2023-08-05', '2023-08-11', 4, 'Nice view!', 5, 'Host was friendly.', 5, 'Enjoyed the stay.', 11, 30),
(false, false, 250.00, '2023-08-15', '2023-08-21', 5, 'Beautiful place!', 4, 'Host was helpful.', 5, 'Had a great time.', 12, 31),
(false, true, 100.00, '2023-08-01', '2023-08-04', 3, 'Cozy and comfortable!', 4, 'Host was accommodating.', 4, 'Good stay!', 13, 32),
(true, false, 280.00, '2023-08-12', '2023-08-17', 4, 'Lovely house!', 4, 'Host was welcoming.', 4, 'Great experience!', 14, 33),
(false, false, 110.00, '2023-08-16', '2023-08-20', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 34),
(true, true, 80.00, '2023-08-07', '2023-08-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 35),
(false, false, 190.00, '2023-08-18', '2023-08-24', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 36),
(false, false, 100.00, '2023-08-21', '2023-08-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 37),
(true, false, 160.00, '2023-08-11', '2023-08-16', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 38),
(false, true, 65.00, '2023-08-26', '2023-08-30', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 39),
(false, false, 150.00, '2023-09-05', '2023-09-10', 4, 'Nice place!', 5, 'Host was friendly.', 5, 'Great experience!', 11, 20),
(false, false, 220.00, '2023-09-15', '2023-09-20', 5, 'Amazing view!', 4, 'Host was helpful.', 5, 'Loved the stay!', 12, 21),
(false, true, 80.00, '2023-09-01', '2023-09-03', 3, 'Cozy place!', 4, 'Host was accommodating.', 4, 'Had a good time.', 13, 22),
(true, false, 300.00, '2023-09-10', '2023-09-15', 4, 'Beautiful house!', 4, 'Host was welcoming.', 4, 'Nice host!', 14, 23),
(false, false, 120.00, '2023-09-12', '2023-09-15', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 24),
(true, true, 90.00, '2023-09-07', '2023-09-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 25),
(false, false, 200.00, '2023-09-20', '2023-09-25', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 26),
(false, false, 110.00, '2023-09-22', '2023-09-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 27),
(true, false, 180.00, '2023-09-10', '2023-09-15', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 28),
(false, true, 70.00, '2023-09-25', '2023-09-28', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 29),
(false, false, 130.00, '2023-09-05', '2023-09-11', 4, 'Nice view!', 5, 'Host was friendly.', 5, 'Enjoyed the stay.', 11, 30),
(false, false, 250.00, '2023-09-15', '2023-09-21', 5, 'Beautiful place!', 4, 'Host was helpful.', 5, 'Had a great time.', 12, 31),
(false, true, 100.00, '2023-09-01', '2023-09-04', 3, 'Cozy and comfortable!', 4, 'Host was accommodating.', 4, 'Good stay!', 13, 32),
(true, false, 280.00, '2023-09-12', '2023-09-17', 4, 'Lovely house!', 4, 'Host was welcoming.', 4, 'Great experience!', 14, 33),
(false, false, 110.00, '2023-09-16', '2023-09-20', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 34),
(true, true, 80.00, '2023-09-07', '2023-09-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 35),
(false, false, 190.00, '2023-09-18', '2023-09-24', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 36),
(false, false, 100.00, '2023-09-21', '2023-09-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 37),
(true, false, 160.00, '2023-09-11', '2023-09-16', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 38),
(false, true, 65.00, '2023-09-26', '2023-09-30', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 39),
(true, false, 110.00, '2023-09-05', '2023-09-10', 4, 'Nice place!', 5, 'Host was friendly.', 5, 'Great experience!', 11, 41),
(false, false, 190.00, '2023-09-15', '2023-09-20', 5, 'Amazing view!', 4, 'Host was helpful.', 5, 'Loved the stay!', 12, 42),
(false, true, 70.00, '2023-09-01', '2023-09-03', 3, 'Cozy place!', 4, 'Host was accommodating.', 4, 'Had a good time.', 13, 43),
(true, false, 250.00, '2023-09-10', '2023-09-15', 4, 'Beautiful house!', 4, 'Host was welcoming.', 4, 'Nice host!', 14, 44),
(false, false, 100.00, '2023-09-12', '2023-09-15', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 45),
(true, true, 80.00, '2023-09-07', '2023-09-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 46),
(false, false, 200.00, '2023-09-20', '2023-09-25', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 47),
(false, false, 110.00, '2023-09-22', '2023-09-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 48),
(true, false, 180.00, '2023-09-10', '2023-09-15', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 49),
(false, true, 70.00, '2023-09-25', '2023-09-28', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 50),
(false, false, 130.00, '2023-10-05', '2023-10-10', 4, 'Nice place!', 5, 'Host was friendly.', 5, 'Great experience!', 11, 41),
(false, false, 200.00, '2023-10-15', '2023-10-20', 5, 'Amazing view!', 4, 'Host was helpful.', 5, 'Loved the stay!', 12, 42),
(false, true, 80.00, '2023-10-01', '2023-10-03', 3, 'Cozy place!', 4, 'Host was accommodating.', 4, 'Had a good time.', 13, 43),
(true, false, 300.00, '2023-10-10', '2023-10-15', 4, 'Beautiful house!', 4, 'Host was welcoming.', 4, 'Nice host!', 14, 44),
(false, false, 120.00, '2023-10-12', '2023-10-15', 5, 'Clean and tidy!', 5, 'Host was kind.', 5, 'Would come back!', 15, 45),
(true, true, 90.00, '2023-10-07', '2023-10-09', 3, 'Good location!', 4, 'Host was responsive.', 4, 'Enjoyed the stay.', 16, 46),
(false, false, 200.00, '2023-10-20', '2023-10-25', 5, 'Spacious room!', 5, 'Host was friendly.', 5, 'Great host!', 17, 47),
(false, false, 110.00, '2023-10-22', '2023-10-26', 4, 'Nice apartment!', 4, 'Host was helpful.', 5, 'Enjoyed the stay.', 11, 48),
(true, false, 180.00, '2023-10-10', '2023-10-15', 4, 'Cozy cottage!', 4, 'Host was welcoming.', 4, 'Good experience!', 12, 49),
(false, true, 70.00, '2023-10-25', '2023-10-28', 3, 'Simple and clean.', 3, 'Host was accommodating.', 4, 'Would recommend!', 13, 50),
(false, false, 250.00, '2023-08-10', '2023-08-15', 4, 'Great place to stay!', 5, 'Very friendly host.', 4, 'Enjoyed the stay!', 11, 51),
(false, false, 180.00, '2023-08-12', '2023-08-17', 5, 'Beautiful view from the balcony.', 4, 'Helpful and accommodating.', 5, 'Highly recommended!', 11, 52),
(true, false, 100.00, '2023-08-14', '2023-08-16', 3, 'Decent place for the price.', 3, 'Communication could be better.', 4, 'Average stay.', 11, 53),
(false, false, 320.00, '2023-08-20', '2023-08-25', 5, 'Exceeded expectations!', 5, 'Excellent service.', 5, 'Loved it!', 11, 54),
(true, true, 200.00, '2023-08-25', '2023-08-28', 4, 'Issues with cleanliness.', 3, 'Host was unresponsive.', 3, 'Not a great experience.', 11, 55),
(false, false, 180.00, '2023-08-30', '2023-09-05', 5, 'Absolutely stunning property.', 5, 'Host was super helpful.', 5, 'Wonderful stay!', 11, 56),
(false, false, 150.00, '2023-09-02', '2023-09-06', 4, 'Cozy and comfortable.', 4, 'Host was friendly.', 4, 'Enjoyed our time here.', 12, 57),
(false, false, 280.00, '2023-09-05', '2023-09-10', 5, 'Luxurious and spacious.', 5, 'Host was accommodating.', 5, 'Highly recommended!', 12, 58),
(true, false, 120.00, '2023-09-08', '2023-09-09', 3, 'Basic amenities provided.', 3, 'Host was okay.', 3, 'Not bad, not great.', 12, 59),
(false, false, 200.00, '2023-09-12', '2023-09-15', 4, 'Great location!', 4, 'Host was responsive.', 4, 'Had a nice time.', 12, 60),
(false, false, 190.00, '2023-09-15', '2023-09-20', 4, 'Beautifully decorated.', 5, 'Host was wonderful.', 4, 'Very enjoyable stay.', 12, 61),
(true, true, 90.00, '2023-09-18', '2023-09-19', 3, 'Average stay.', 3, 'Host was unresponsive.', 3, 'Wouldn`t recommend.', 13, 62),
(false, false, 350.00, '2023-09-22', '2023-09-27', 5, 'Absolutely amazing!', 5, 'Host was fantastic.', 5, 'Loved every moment.', 13, 63),
(false, false, 160.00, '2023-09-25', '2023-09-28', 4, 'Had everything we needed.', 4, 'Host was helpful.', 4, 'Nice place to stay.', 13, 64),
(false, false, 280.00, '2023-09-28', '2023-10-02', 5, 'Perfect for our family.', 5, 'Host was welcoming.', 5, 'Highly recommended!', 13, 65),
(true, true, 150.00, '2023-10-02', '2023-10-04', 3, 'Average stay.', 3, 'Host was unresponsive.', 3, 'Wouldn`t recommend.', 13, 66),
(false, false, 230.00, '2023-10-05', '2023-10-10', 4, 'Comfortable and clean.', 4, 'Host was friendly.', 4, 'Had a good time.', 13, 67),
(false, false, 190.00, '2023-10-08', '2023-10-12', 4, 'Lovely place!', 4, 'Host was accommodating.', 4, 'Enjoyed our stay.', 14, 68),
(false, false, 310.00, '2023-10-12', '2023-10-16', 5, 'Exceeded expectations!', 5, 'Excellent service.', 5, 'Loved it!', 14, 69),
(true, false, 120.00, '2023-10-15', '2023-10-17', 3, 'Decent place for the price.', 3, 'Communication could be better.', 4, 'Average stay.', 14, 70),
(false, false, 200.00, '2023-10-18', '2023-10-22', 4, 'Great location!', 4, 'Host was responsive.', 4, 'Had a nice time.', 14, 71),
(false, false, 220.00, '2023-10-22', '2023-10-27', 4, 'Clean and comfortable.', 5, 'Host was wonderful.', 4, 'Very enjoyable stay.', 14, 72),
(false, false, 170.00, '2023-10-25', '2023-10-30', 4, 'Beautifully decorated.', 5, 'Host was fantastic.', 4, 'Nice place to stay.', 15, 73),
(true, true, 80.00, '2023-10-28', '2023-10-29', 3, 'Basic amenities provided.', 3, 'Host was okay.', 3, 'Not bad, not great.', 15, 74),
(false, false, 300.00, '2023-10-30', '2023-11-03', 5, 'Absolutely amazing!', 5, 'Host was fantastic.', 5, 'Loved every moment.', 15, 75),
(false, false, 130.00, '2023-11-02', '2023-11-05', 4, 'Had everything we needed.', 4, 'Host was helpful.', 4, 'Nice place to stay.', 15, 76),
(false, false, 250.00, '2023-11-05', '2023-11-10', 4, 'Comfortable and clean.', 4, 'Host was friendly.', 4, 'Had a good time.', 15, 77),
(false, false, 190.00, '2023-11-08', '2023-11-12', 4, 'Lovely place!', 4, 'Host was accommodating.', 4, 'Enjoyed our stay.', 16, 78),
(false, false, 330.00, '2023-11-12', '2023-11-16', 5, 'Exceeded expectations!', 5, 'Excellent service.', 5, 'Loved it!', 16, 79),
(true, false, 140.00, '2023-11-15', '2023-11-17', 3, 'Decent place for the price.', 3, 'Communication could be better.', 4, 'Average stay.', 16, 80);


-- Availabilities Table (40)
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-01-05', '2023-12-10', 41),
('2023-01-12', '2023-12-17', 42),
('2023-01-20', '2023-12-23', 43),
('2023-01-25', '2023-12-30', 44),
('2023-01-02', '2023-12-06', 45),
('2023-01-08', '2023-12-12', 46),
('2023-01-15', '2023-12-20', 47),
('2023-01-22', '2023-12-27', 48),
('2023-01-30', '2023-12-05', 49),
('2023-01-08', '2023-12-12', 50),
('2023-01-15', '2023-12-20', 51),
('2023-01-22', '2023-12-26', 52),
('2023-01-28', '2023-12-02', 53),
('2023-01-05', '2023-12-10', 54),
('2023-01-12', '2023-12-17', 55),
('2023-01-20', '2023-12-25', 56),
('2023-01-28', '2023-12-03', 57),
('2023-01-05', '2023-12-10', 58),
('2023-01-12', '2023-12-17', 59),
('2023-01-20', '2023-12-25', 60),
('2023-01-05', '2023-12-10', 61),
('2023-01-12', '2023-12-17', 62),
('2023-01-20', '2023-12-23', 63),
('2023-01-25', '2023-12-30', 64),
('2023-01-02', '2023-12-06', 65),
('2023-01-08', '2023-12-12', 66),
('2023-01-15', '2023-12-20', 67),
('2023-01-22', '2023-12-27', 68),
('2023-01-30', '2023-12-05', 69),
('2023-01-08', '2023-12-12', 70),
('2023-01-15', '2023-12-20', 71),
('2023-01-22', '2023-12-26', 72),
('2023-01-28', '2023-12-02', 73),
('2023-01-05', '2023-12-10', 74),
('2023-01-12', '2023-12-17', 75),
('2023-01-20', '2023-12-25', 76),
('2023-01-28', '2023-12-03', 77),
('2023-01-05', '2023-12-10', 78),
('2023-01-12', '2023-12-17', 79),
('2023-01-20', '2023-12-25', 80);



-- Amenities Table (40)
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, pool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 41),
(true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, false, false, true, true, true, false, 42),
(true, true, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, true, true, false, true, false, 43),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 44),
(false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 45),
(true, true, true, false, true, true, true, true, false, false, true, true, false, true, false, true, true, false, true, false, true, false, 46),
(false, true, true, false, true, true, true, true, false, false, true, true, false, true, false, true, false, true, true, true, true, false, 47),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 48),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 49),
(true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 50),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 51),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 52),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 53),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 54),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 55),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 56),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 57),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 58),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 59),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 60),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 61),
(true, true, true, false, true, true, true, true, true, false, true, true, false, true, false, true, false, false, true, true, true, false, 62),
(true, true, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, true, true, false, true, false, 63),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 64),
(false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 65),
(true, true, true, false, true, true, true, true, false, false, true, true, false, true, false, true, true, false, true, false, true, false, 66),
(false, true, true, false, true, true, true, true, false, false, true, true, false, true, false, true, false, true, true, true, true, false, 67),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 68),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 69),
(true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, 70),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 71),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 72),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 73),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 74),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 75),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 76),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 77),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 78),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 79),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 80);

