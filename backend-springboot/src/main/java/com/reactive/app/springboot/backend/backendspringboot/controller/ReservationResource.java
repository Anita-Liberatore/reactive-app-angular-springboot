package com.reactive.app.springboot.backend.backendspringboot.controller;

import com.reactive.app.springboot.backend.backendspringboot.model.Reservation;
import com.reactive.app.springboot.backend.backendspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResource {

    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    @Autowired
    private ReservationService reservationService;


    @GetMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getReservationId(@PathVariable String roomId) {
        return reservationService.getReservation(roomId);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {
        return reservationService.createReservation(reservation);

    }

    @PutMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> updatePrice(@PathVariable String roomId, @RequestBody Mono<Reservation> reservation) {
        return reservationService.updateReservation(roomId,reservation);

    }

    @DeleteMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Boolean> deleteReservation(@PathVariable String roomId) {
        //reservationService.deleteReservation(roomId);

        return Mono.just(true);

    }
}
