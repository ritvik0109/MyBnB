Create Listing
get data, and perform data validation
will need querty to get user id based on email
sql query to insert into table, with the appropriate user_id

    go back to user home

Edit existing listing
display all user's listings
sql query to get all listings for user

    choice to select which listing to update

    display list of attributes to change
    user chooses which one to change
        they

    for amentities
        give a list of all amentities, and option to update one
        then its a add or remove

    loop until user chooses to not make any more edits

    go back to user home

Remove listing
display all user's listings
sql query to get all listings for user (same sql query as edit listings)

    user chooses which one to delete

    loop until user chooses to go back to main menu

Book listing (search)
list ways to search
by address (exact address)
by postal code (exact and adjacent)
by date range (start + end date)
by amentities
by price (range)
range
ascending/descdign
by location (long and lat?)
by location (country)
by location (city)

    After user makes first choice of how to search, ask if they would like to add more filters to search

    if yes, loop until they say no

    if no, list out listings



Cancel booking
display all (active) bookings

    user to choose which one to cancel
        double check they want to cancel

    loop until user chooses to go back to main menu

Delete account
Are you sure you want to delete? Y/N?
delete from every table - listings, bookings, etc  
 maybe not delete from other tables, but rather have an `is_deleted` attribute in user table
THIS IS A FEATURE!!

        delete user from users table



    Back to registration page

Logout
clear all variables associated with user

    go back to registration/login page

---

Adding comments to previous listings/adding comments to previous renters

Host toolkit
When user creates a listing, then we suggest how to improve:
List suggestions of amenities to have
for each suggested amenity, the expected revenue increase

Admin account
Need to setup an admin account for reports to submit

    Reports to submit:
        Total number of bookings in a specific date range by city
        Total number of bookings in a specific date range by zip code, within a city

        Total number of listings in a country
        Total number of listings in a city
        Total number of listings in a postal code
        Total number of listings in a postal code, city and country

        Rank hosts by total num of listings in a country
            refine this based on num of listings by city

        List the hosts that have > 10% of listings in a country
        List the hosts that have > 10% of listings in a city
        List the hosts that have > 10% of listings in a city, and country

        Rank renters by num of bookings in a specific time
        Rank renters by num of bookings in a specific time, in a city
            interested in renters with at least 2 bookings

        Report hosts and renters with the largest number of cancellations within a year

        For each listing, report the most popular noun phrases in the comments for that listing

Need to setup a SHITTON of fake data
