Drop TABLE IF EXISTS Users, Listings, Availabilities, Amenities, Bookings;

CREATE TABLE Users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(100),
  sin INT(9) UNIQUE,
  date_of_birth DATE, 
  occupation VARCHAR(50) NOT NULL,
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

-- MOCK DATA:

INSERT INTO Users (name, address, sin, date_of_birth, occupation, credit_card)
VALUES
  ('John Smith', '123 Main St, Anytown', 123456789, '1985-05-15', 'Software Eng', 123456781234),
  ('Alice Lee', '456 Elm St, Cityville', 987654321, '1990-11-22', 'Marketing', 987654321987),
  ('Bob Johnson', '789 Oak Ave, Village', 246813579, '1988-09-07', 'Teacher', 111122223333);

SHOW COLUMNS FROM USERS;
SELECT * FROM USERS;

INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
  ('apartment', 'Cozy Studio Apartment', 'Modern studio apartment in the heart of the city', 100.00, '789 Elm St, Downtown', 'Cityville', 'USA', 12345, 'Unit 101', -79.383601, 43.653225, 1),
  ('house', 'Spacious Family Home', 'Beautiful house with a large backyard', 200.00, '456 Oak Ave, Suburb', 'Village', 'Canada', 56789, NULL, -79.399141, 43.651070, 2),
  ('apartment', 'Modern Loft', 'Stylish loft with great city views', 150.00, '123 Main St, Downtown', 'Anytown', 'USA', 98765, 'Unit 205', -79.383218, 43.653963, 3);

INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
  ('2023-07-20', '2023-07-25', 1),
  ('2023-08-01', '2023-08-10', 2),
  ('2023-07-15', '2023-07-30', 3);

INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, spool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
  (1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1),
  (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 2),
  (1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 3);

INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
  (0, 0, 300.00, '2023-07-20', '2023-07-25', 4, 'Great experience!', 5, 'Awesome host!', 5, 'Highly recommended', 1, 1),
  (1, 0, 800.00, '2023-08-01', '2023-08-10', 3, 'Not as described', 2, 'Unresponsive', 3, 'Average', 2, 2),
  (0, 1, 450.00, '2023-07-15', '2023-07-30', 5, 'Wonderful stay!', 4, 'Great guest!', 5, 'Highly recommended', 3, 3);