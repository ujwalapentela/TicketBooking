package com.cloudbees.assessment.TicketBooking;

import com.cloudbees.assessment.TicketBooking.Tables.*;
import com.cloudbees.assessment.TicketBooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class TicketBookingApplication implements CommandLineRunner {
	@Autowired
	private StationsService stationsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PricesService pricesService;

	@Autowired
	private TrainsService trainsService;

	@Autowired
	private SectionsService sectionsService;

	@Autowired
	private BookingsService bookingsService;

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		insertDefaultStations();
		insertDefaultUsers();
		insertDefaultPrices();
		insertDefaultTrains();
		insertDefaultSections();
		insertDefaultBookings();
	}

	public void insertDefaultStations(){
		Stations s1 = new Stations("London","LDN");
		Stations s2 = new Stations("France","FR");
		stationsService.insertStation(s1);
		stationsService.insertStation(s2);
		Iterable<Stations> stationsList = stationsService.getStations();
		stationsList.forEach(new Consumer<Stations>() {
			@Override
			public void accept(Stations stations) {
				System.out.println(stations);
			}
		});
	}

	public void insertDefaultUsers(){
		Users u = null;
		for(int i=1;i<=20;i++){
			u = new Users("FN "+i,"LN "+i,"email"+i+"@gmail.com","pwd@"+i);
			usersService.insertUser(u);
		}
		Users u1 = new Users("Ujwala","Pentela","ujwala@gmail.com","123");
		Users u2 = new Users("Praveen","Varkikuppala","praveen@gmail.com","123");
		usersService.insertUser(u1);
		usersService.insertUser(u2);
		Iterable<Users> usersList = usersService.getAllUsers();
		for (Users users : usersList) {
			System.out.println(users);
		}
	}

	public void insertDefaultPrices(){
		Stations ldn = stationsService.getById(1).get();
		Stations fr = stationsService.getById(2).get();
		Prices p1 = new Prices(ldn,fr,20);
		Prices p2 = new Prices(fr,ldn,20);
		pricesService.insertPrices(p1);
		pricesService.insertPrices(p2);

		Iterable<Prices> pricesList = pricesService.getAllPrices();

		for(Prices prices : pricesList){
			System.out.println(prices);
		}
	}

	public void insertDefaultTrains(){
		Stations ldn = stationsService.getById(1).get();
		Stations fr = stationsService.getById(2).get();
		LocalDateTime startTime = LocalDateTime.now().plusDays(1).plusHours(1).plusMinutes(1);
		LocalDateTime endTime = LocalDateTime.now().plusDays(2).plusHours(2);
		Trains t1 = new Trains("Ldn-Fr-SP-Exp",ldn,fr,startTime,endTime);

		trainsService.insertTrains(t1);
		Iterable<Trains> trainsList = trainsService.getAllTrains();

		for(Trains trains: trainsList){
			System.out.println(trains);
		}

	}

	public void insertDefaultSections(){
		Trains t1 = trainsService.getById(1).get();
		Sections s1 = new Sections(t1,"A",40);
		Sections s2 = new Sections(t1,"B",40);

		sectionsService.insertSection(s1);
		sectionsService.insertSection(s2);

		Iterable<Sections> sectionsList =sectionsService.getAllSections();

		for (Sections sections: sectionsList){
			System.out.println(sections);
		}

	}

	public void insertDefaultBookings(){
		Trains t1= trainsService.getById(1).get();

		List<Sections> sectionsList = sectionsService.getSectionsInTrain(t1);
		Bookings bookings = null;
		for(int i=1;i<=10;i++){
			bookings = new Bookings(sectionsList.get(0),usersService.getById(i).get(),t1.getSource(),t1.getDestination(),20,i);
			bookingsService.insertBooking(bookings);
		}
		for(int i=1;i<=10;i++){
			bookings = new Bookings(sectionsList.get(1),usersService.getById(10+i).get(),t1.getSource(),t1.getDestination(),20,i);
			bookingsService.insertBooking(bookings);
		}

		Iterable<Bookings> bookingsIterable = bookingsService.getAllBookings();
		for(Bookings b: bookingsIterable){
			System.out.println(b);
		}
	}

}
