# Train Ticket Booking Application using SpringBoot


### To get all bookings 
url = `http://localhost:8090/bookings/allBookings` method = `GET`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/5ca5a206-735b-4387-b649-dc1359f3fa15)


### To get booking details using booking id 
url = `http://localhost:8090/bookings/{bookingId}`  method = `GET`  

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/a3cdeb20-0c27-4ee9-96fa-9092ae65d551)


### To get bookings in a section 
url = `http://localhost:8090/bookings/sections/{sectionId}`  method = `GET`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/c9a3cb0f-8405-4739-a2f9-1f0d30232d87)


### To get the booking of a user
url = `http://localhost:8090/bookings/user/{userId}`  method = `GET`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/ad2e2031-1f67-40b1-9f05-1306c8afc57e)


### To get available seats status in a section  
url = `http://localhost:8090/bookings/section/{sectionId}/seats`  method = `GET`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/8e4ab73c-ec1a-46a7-b71d-284a1f12860d)


### To book a ticket
url = `http://localhost:8090/bookings/book`  method = `POST` contentType = `application/json`   
content/body = `{
    "userId":1,
   "fromId":1,
   "toId":2,
   "sectionId":1,
   "price":20,
   "seat":25
}`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/944f8588-bc5a-48c0-8a1c-ff4fd3a278d3)


### To cancel a booking  
url = `http://localhost:8090/bookings/cancel/{bookingId}` method = `DELETE`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/b4c94cd3-bb0b-4531-b951-b5ab9f8fe1fb)
![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/743c5a0b-34d2-49ed-a508-43f9f2440720)


### To update the seat of a user
url = `http://localhost:8090/bookings/updateSeat` method = `PUT`         
header values = `bookingId:1`, `sectionId:1`, `seat:1`

![image](https://github.com/ujwalapentela/TicketBooking/assets/69097559/c5b5b70f-0375-434f-b270-6bdb02b86a86)






