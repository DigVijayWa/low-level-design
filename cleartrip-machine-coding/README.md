# Flight Inventory Management System

## Problem Statement:

Design an application for flight inventory and booking management systems. Where every day
multiple flights are flying on different sectors and flights can have multiple fares and for each
fare there will be a list of seats.

## Assumptions: 
- There is a supplier that is providing flight data.
- Same flight can fly for two different sectors for the same day but time cannot be the
same.
- Flights can have multiple fareType and for each fareType airline is providing an available
seat list.
- If a seat is already booked, other users can't book the same seat.
- Take today's date as 1.
- For departDate simple number is provided like - 1, 2, 3, 4, etc
- For time, assume its 24 hour format and will be a decimal number.
- User fund will be a decimal number only.

## Features:
- `AddUser(userId, name, funds)`
    Return user details
    Output:- <u1, name, funds>
- `SearchFlight(from, to, departDate, paxCount)`
    Return available flights for given request in below format. (for the same date and if all
    available seats are equal or more than provided pax count).
    Output:- <flightNumber, airline, from, to, departDate, departTime, price, fareType,
    List<seat>>
- `Book flight(userId, flightNumber, departDate, fareType, list<seat>)`
    Book flight if all seats are available with the same fareType and the user has enough
    funds in his/her wallet. Deduct funds from userAccount for booking and return bookingId.
    If it fails, then return a proper error message.
- `Cancel flight(userId, bookingId)`
    Cancel the booking by updating flight available seats and user funds and return success
- `GetUserBooking(userId)`
    Return booked and canceled flights detail in below format
    Output:- <bookingId, flightId, departDate, departTime, amount, FareType, List<seat>>

## Bonus Question:

- `Update flight(userId, bookingId, newFlightNumber, departDate)`
Book flight if seats are available and the user has enough funds in his/her wallet. Deduct
additional funds from userAccount.
If it fails, then return a proper error message.
- `SearchFlightByPreferedAirline(from, to, departDate, paxCount, preferredAirline, sortBy, SortType)`
Return available flights for given request in below format
Output:- <flightNumber, airline, from, to, departDate, departTime, price, List<seat>>
- Handle race conditions during multi-user interactions, specifically in booking flow
