todo:
[x] N: create listing
[ ] R: edit listing 
[ ] N: remove listing
[ ] R: book listing (create a booking + search)
[ ] R: cancel booking
[ ] R: delete account (email TA)
[ ] N: logout 
[ ] N: review your experience, comments
[ ] R: host toolkit
[ ] N: run a report 
 
to test:

N: Create Listing:
    - get data, and perform data validation
    - will need query to get user id based on email
    - sql query to insert into table, with the appropriate user_id
    - go back to user home

R: Edit Existing Listing:
    - display all user's listings
    - sql query to get all listings for user
    - choice to select which listing to update
    - display list of attributes to change, user chooses which one to change
    - for amenities
    - give a list of all amenities, and option to update one
    - then its a add or remove
    - loop until user chooses to not make any more edits
    - go back to user home

N: Remove Listing:
    - display all user's listings
    - sql query to get all listings for user (same sql query as edit listings)

    Select a listing to remove:
    1. x
    2. x

    Are you sure you want to remove this listing:
    3. Yes, ...
    4. No, ...

    Would you like to delete another listing?
    5. Yes, ...
    6. No, ...

    loop until user chooses to go back to main menu.

R: Book Listing (Search):
    Would you like to add filters to search?
    1. Yes
    2. No

    If 2 selected, display all listings (if no prev filters).
    If 1 selected:

    Select a Filter:
    3. by address (exact address)
    4. by postal code (exact and adjacent)
    5. by date range (start + end date)
    6. by amenities
    7. by price (range)
    8. by location (long and lat and distance)
    9. by location (country)
    10. by location (city)

    Would you like to sort your results?
    11. Yes
    12. No

    Select a way to sort:
    13. Ascending distance 
    14. Descending distance
    15. Ascending price
    16. Descending price

    After user makes first choice of how to search, ask if they would like to 
    add more filters to search:
    if yes, loop until they say no
    if no, list out listings

Cancel Booking
    - display all (active) bookings, user to choose which one to cancel, 
    - double check they want to cancel
    - loop until user chooses to go back to main menu
    Are you sure you want to cancel your booking?
    1. Yes, I want to cancel my booking.
    2. No, I want to keep my booking. 

Delete Account
    Are you sure you want to delete? 
    1. Yes, I want to delete my account.
    2. No, I want to keep my account. 
   
    - delete from every table - listings, bookings, etc  
    - maybe not delete from other tables, but rather have an `is_deleted` attribute in user table
    - delete user from users table
    - back to registration page

Logout:
    - clear all variables associated with user
    - go back to registration/login page. "are u sure"


Review Your Experience & Make Comments:
    - "Adding comments to previous listings/adding comments to previous renters"
    1. Comment as a renter
        1. Display list of previous bookings, and select one to comment on.
            1. Comment on listing 
            2. Comment on host 
            3. Rate a listing
            4. Rate a host
    2. Comment as a host
        1. Display list of previous bookings, but emphasis on renter, and select one to comment on.
            1. Comment on Renter
            2. Rate a Renter


Host Toolkit:
- Fake data to back up the recommendations
- Suggested price for amenity adding:
  - Queries to obtain the price increase/decrease for that specific amenity 
  - Modify create listing to add amenities, and showcase the suggested increase when adding amenity.

- For each added amenity:
    1. Amenity 1, New listing evaluation: $200, value added: $10
    2. Amenity 2, New listing evaluation: $200, value added: $10
    3. Amenity 3, New listing evaluation: $200, value added: $10
    4. Total estimated evaluation: $X

- Suggest price for listing
  - Taking average of that area
  - Take average with those amenity combo 
  - Average those 2 (weighted?) 
  
- When user creates a listing, then we suggest how to improve:
- List suggestions of amenities to have
- for each suggested amenity, the expected revenue increase

Run a Report:
  - Generate reports to main menu. Add to 3. Run a Report. (main menu)
  - Choose a report to run:
      1. Total number of bookings in a specific date range by city
      2. Total number of bookings in a specific date range by zip code, within a city
      3. Total number of listings in a country
      4. Total number of listings in a city
      5. Total number of listings in a postal code
      6. Total number of listings in a postal code, city and country
      7. Rank hosts by total num of listings in a country, refine this based on num of listings by city
      8. List the hosts that have > 10% of listings in a country
      9. List the hosts that have > 10% of listings in a city
      10. List the hosts that have > 10% of listings in a city, and country
      11. Rank renters by num of bookings in a specific time
      12. Rank renters by num of bookings in a specific time, in a city
          interested in renters with at least 2 bookings
      13. Report hosts and renters with the largest number of cancellations within a year
      14. (!) For each listing, report the most popular noun phrases in the comments for that listing
          - Get the word cloud, currently in the frontend. 
