## Sample rest application for showing existing hotel reservations already booked. using H2 and Spring boot

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0


### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions with the database:
    http://localhost:8088/hotel/reservations/01-01-2018
    * `/hotel/reservations/[date]`: shows already reserved rooms by date (dd-MM-yyyy) Format.
    * `/hotel/reservations/phone/[phone]`: shows reservations by using customer phone number eg 9-(659)879-6466.
     * `/hotel/reservations/guest/[guestId]`: shows reservations by using guest Id
    
-Please see data.sql for valid values

- examples

    http://localhost:8088/hotel/reservations/01-01-2018
    
    http://localhost:8088/hotel/reservations/phone/9-(659)879-6466
    
    http://localhost:8088/hotel/reservations/guests
    
    http://localhost:8088/hotel/book?roomId=20&guestId=200&date=21-07-2018
   