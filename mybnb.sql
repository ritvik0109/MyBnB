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
  price_per_night DECIMAL(10, 2),
  address VARCHAR(100),
  city VARCHAR(50),
  country VARCHAR(50),
  postal_code INT(10),
  unit_room_number VARCHAR(30),
  longitude DECIMAL(9, 6),
  latitude DECIMAL(9, 6),
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Availabilities (
  avail_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  start_date DATE, 
  end_date DATE,
  list_id INT NOT NULL,
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
  spool BOOLEAN, 
  free_parking BOOLEAN, 
  crib BOOLEAN, 
  bbq_grill BOOLEAN, 
  indoor_fireplace BOOLEAN, 
  smoking_allowed BOOLEAN, 
  breakfast BOOLEAN, 
  gym BOOLEAN, 
  ev_charger BOOLEAN, 
  hot_tub BOOLEAN,
  list_id INT NOT NULL UNIQUE,
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
  user_id INT NOT NULL,
  list_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (list_id) REFERENCES Listings(list_id),
  UNIQUE KEY user_list_unique (user_id, list_id)
);

-- Mock data for Users table
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
  ('guesthouse', 'Tranquil Hideaway', 'A tranquil guesthouse by the lake.', 135.00, '951 Lakeview Dr', 'Townville', 'Countryland', 456789, NULL, 67.890123, 23.456789, 7),
  ('hotel', 'City Luxury Hotel', 'A luxurious hotel in the heart of the city.', 189.99, '456 High Street', 'Cityville', 'Countryland', 654321, 'Suite 101', 78.901234, 34.567890, 1),
  ('apartment', 'Modern Studio', 'A sleek and modern studio apartment.', 80.00, '789 Urban Ave', 'Cityville', 'Countryland', 987654, 'Apt 7', 90.123456, 45.678901, 9),
  ('house', 'Spacious Family Home', 'A spacious house perfect for families.', 150.00, '753 Family Rd', 'Villageville', 'Countryland', 123456, NULL, 12.345678, 56.789012, 10);

-- Mock data for Availabilities table
INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
  ('2023-07-20', '2023-07-25', 1),
  ('2023-08-05', '2023-08-10', 1),
  ('2023-07-22', '2023-07-28', 2),
  ('2023-07-18', '2023-07-23', 2),
  ('2023-08-01', '2023-08-08', 3),
  ('2023-08-15', '2023-08-22', 3),
  ('2023-07-25', '2023-07-30', 4),
  ('2023-07-10', '2023-07-15', 4),
  ('2023-08-05', '2023-08-12', 5),
  ('2023-08-20', '2023-08-27', 5),
  ('2023-07-15', '2023-07-20', 6),
  ('2023-07-28', '2023-08-02', 6),
  ('2023-08-10', '2023-08-17', 7),
  ('2023-08-25', '2023-09-01', 7),
  ('2023-07-18', '2023-07-25', 8),
  ('2023-08-02', '2023-08-09', 8),
  ('2023-07-22', '2023-07-28', 9),
  ('2023-08-08', '2023-08-15', 9),
  ('2023-07-30', '2023-08-06', 10),
  ('2023-08-15', '2023-08-22', 10);

-- Mock data for Amenities table
INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, spool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
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
  (0, 0, 250.00, '2023-07-20', '2023-07-25', 4, 'Great place!', 5, 'Awesome host!', 4, 'Smooth stay.', 2, 1),
  (0, 0, 180.00, '2023-08-05', '2023-08-10', 3, 'Nice view!', 4, 'Very accommodating!', 5, 'Loved it.', 1, 2),
  (0, 0, 300.00, '2023-07-22', '2023-07-28', 5, 'Wonderful experience!', 4, 'Helpful host.', 5, 'Definitely recommend.', 7, 3),
  (0, 0, 225.00, '2023-07-18', '2023-07-23', 4, 'Comfortable stay.', 5, 'Friendly host!', 4, 'Would book again.', 4, 4),
  (0, 0, 160.00, '2023-08-01', '2023-08-08', 4, 'Cozy and clean.', 3, 'Good communication.', 5, 'Enjoyed my stay.', 3, 5),
  (0, 0, 175.00, '2023-08-15', '2023-08-22', 5, 'Beautiful property.', 4, 'Great service!', 5, 'Fantastic experience.', 2, 6),
  (0, 0, 195.00, '2023-07-25', '2023-07-30', 4, 'Lovely stay!', 5, 'Very welcoming.', 4, 'Had a great time.', 7, 7),
  (0, 0, 280.00, '2023-07-10', '2023-07-15', 5, 'Excellent host!', 4, 'Clean and spacious.', 5, 'Enjoyed every moment.', 7, 8),
  (0, 0, 190.00, '2023-08-05', '2023-08-12', 4, 'Nice neighborhood.', 5, 'Helpful suggestions.', 4, 'Would recommend.', 7, 9),
  (0, 0, 230.00, '2023-08-20', '2023-08-27', 5, 'Amazing property.', 4, 'Very responsive.', 5, 'Loved everything.', 10, 10);
