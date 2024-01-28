# Train Ticket Booking Application using SpringBoot


**To get all bookings**  
url = `http://localhost:8090/bookings/allBookings` method = `GET`

**To get booking details using booking id**  
url = `http://localhost:8090/bookings/{bookingId}`  method = `GET`

**To get bookings in a section**  
url = `http://localhost:8090/bookings/sections/{sectionId}`  method = `GET`

**To get the booking of a user**  
url = `http://localhost:8090/bookings/user/{userId}`  method = `GET`

**To get available seats status in a section**   
url = `http://localhost:8090/bookings/section/{sectionId}/seats`  method = `GET`

**To book a ticket**   
url = `http://localhost:8090/bookings/book`  method = `POST` contentType = `application/json`   
content/body = `{
    "userId":1,
   "fromId":1,
   "toId":2,
   "sectionId":1,
   "price":20,
   "seat":25
}`

**To cancel a booking**   
url = `http://localhost:8090/bookings/cancel/{bookingId}` method = `DELETE`

**To update the seat of a user**   
url = `http://localhost:8090/bookings/updateSeat` method = `PUT`         
header values = `bookingId:1`, `sectionId:1`, `seat:1`




