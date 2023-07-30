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

INSERT INTO Users (name, email, password, address, sin, date_of_birth, occupation, credit_card)
VALUES
('John Doe', 'john.doe@example.com', 'pass123', '123 Main St', 123456789, '1990-05-15', 'Engineer', '1234567890123456'),
('Jane Smith', 'jane.smith@example.com', 'password', '456 Elm St', 987654321, '1985-10-20', 'Teacher', '5678901234567890'),
('Michael Johnson', 'michael.johnson@example.com', 'securepass', '789 Oak St', 234567890, '1988-12-03', 'Doctor', '2345678901234567'),
('Emily Brown', 'emily.brown@example.com', 'secret123', '456 Pine St', 543210987, '1995-09-25', 'Artist', '3456789012345678'),
('William Wilson', 'william.wilson@example.com', 'myp@ss', '789 Elm St', 876543211, '1992-07-10', 'Lawyer', '4567890123456789'),
('Olivia Lee', 'olivia.lee@example.com', 'passcode', '456 Oak St', 678901234, '1993-04-18', 'Architect', '5678901234567890'),
('James Davis', 'james.davis@example.com', 'secretword', '789 Pine St', 345678901, '1991-01-30', 'Writer', '6789012345678901'),
('Emma Johnson', 'emma.johnson@example.com', 'mypassword', '123 Elm St', 456789012, '1994-03-12', 'Teacher', '7890123456789012'),
('Liam Smith', 'liam.smith@example.com', '12345678', '789 Maple St', 987654322, '1989-11-08', 'Engineer', '8901234567890123'),
('Sophia Wilson', 'sophia.wilson@example.com', 'password123', '456 Maple St', 876543210, '1996-06-22', 'Doctor', '9012345678901234');


INSERT INTO Listings (property_type, title, description, price_per_night, address, city, country, postal_code, unit_room_number, longitude, latitude, user_id)
VALUES
('house', 'Cozy Cottage', 'A beautiful cottage near the lake', 150.00, '789 Oak St', 'Seattle', 'USA', 12345, 'Unit 1', -122.123456, 47.678910, 1),
('apartment', 'Modern Apartment', 'Spacious apartment in the city center', 200.00, '456 Pine St', 'New York', 'USA', 56789, 'Unit 3B', -74.123456, 40.678910, 2),
('guesthouse', 'Serenity Retreat', 'A peaceful retreat in the countryside', 120.00, '123 Maple St', 'London', 'UK', 67890, 'Room 2', 0.987654, 51.345678, 3),
('hotel', 'Luxury Resort', 'Experience the ultimate luxury in this resort', 300.00, '789 Elm St', 'Paris', 'France', 90123, 'Suite 101', 2.123456, 48.678910, 4),
('house', 'Charming Villa', 'A charming villa with stunning views', 180.00, '456 Oak St', 'Sydney', 'Australia', 45678, 'Villa 3', 151.123456, -33.678910, 5),
('apartment', 'City View Condo', 'Enjoy the city skyline from this condo', 250.00, '789 Pine St', 'Tokyo', 'Japan', 89012, 'Unit 5A', 139.123456, 35.678910, 6),
('guesthouse', 'Rustic Cabin', 'Escape to nature in this cozy cabin', 100.00, '123 Elm St', 'Vancouver', 'Canada', 23456, 'Cabin 2', -123.123456, 49.678910, 7),
('hotel', 'Seaside Resort', 'Relax by the beach at this seaside resort', 280.00, '456 Maple St', 'Miami', 'USA', 56789, 'Room 102', -80.123456, 25.678910, 8),
('house', 'Country Farmhouse', 'Experience country living in this farmhouse', 170.00, '789 Oak St', 'Rome', 'Italy', 90123, 'Farmhouse 4', 12.123456, 41.678910, 9),
('apartment', 'Urban Loft', 'Stylish loft in the heart of the city', 220.00, '456 Pine St', 'Berlin', 'Germany', 23456, 'Loft 7', 13.123456, 52.678910, 10);


INSERT INTO Availabilities (start_date, end_date, list_id)
VALUES
('2023-08-01', '2023-08-10', 1),
('2023-09-15', '2023-09-25', 2),
('2023-08-20', '2023-08-25', 3),
('2023-09-05', '2023-09-12', 4),
('2023-08-10', '2023-08-15', 5),
('2023-09-18', '2023-09-22', 6),
('2023-08-25', '2023-09-01', 7),
('2023-09-10', '2023-09-17', 8),
('2023-08-15', '2023-08-22', 9),
('2023-09-01', '2023-09-08', 10);


INSERT INTO Amenities (wifi, kitchen, washer, dryer, ac, heating, workspace, tv, hair_dryer, iron, smoke_alarm, carbon_monoxide_alarm, spool, free_parking, crib, bbq_grill, indoor_fireplace, smoking_allowed, breakfast, gym, ev_charger, hot_tub, list_id)
VALUES
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 1),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 2),
(true, false, true, false, true, true, false, true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, 3),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 4),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 5),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 6),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 7),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 8),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 9),
(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 10);


INSERT INTO Bookings (is_cancelled, is_cancelled_by_host, total_cost, start_date, end_date, rate_listing, comment_on_listing, rate_host, comment_on_host, rate_renter, comment_on_renter, user_id, list_id)
VALUES
(false, false, 300.00, '2023-08-05', '2023-08-08', 4, 'Great stay!', 5, 'Wonderful host!', 4, 'Enjoyed the stay!', 1, 1),
(false, false, 400.00, '2023-09-18', '2023-09-22', 5, 'Fantastic place!', 4, 'Very helpful host!', 5, 'Highly recommended!', 2, 2),
(true, false, 240.00, '2023-08-25', '2023-08-29', 3, 'Good place!', 4, 'Friendly host!', 3, 'Had a nice time!', 3, 3),
(false, false, 900.00, '2023-09-10', '2023-09-17', 5, 'Amazing stay!', 5, 'Superb host!', 5, 'Would come again!', 4, 4),
(false, false, 360.00, '2023-08-15', '2023-08-19', 4, 'Nice place!', 4, 'Helpful host!', 4, 'Enjoyed the trip!', 5, 5),
(false, false, 500.00, '2023-09-20', '2023-09-27', 5, 'Lovely place!', 5, 'Great host!', 5, 'Had a wonderful time!', 6, 6),
(true, false, 120.00, '2023-08-30', '2023-09-02', 3, 'Decent place!', 4, 'Nice host!', 3, 'It was okay!', 7, 7),
(false, false, 450.00, '2023-09-15', '2023-09-19', 4, 'Comfortable stay!', 5, 'Helpful host!', 4, 'Enjoyed the trip!', 8, 8),
(false, false, 204.00, '2023-08-20', '2023-08-24', 3, 'Good experience!', 4, 'Friendly host!', 3, 'Had a nice stay!', 9, 9),
(false, false, 550.00, '2023-09-05', '2023-09-12', 5, 'Excellent place!', 5, 'Amazing host!', 5, 'Highly recommended!', 10, 10);

