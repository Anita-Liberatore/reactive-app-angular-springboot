package com.reactive.app.springboot.backend.backendspringboot.controller;

import com.reactive.app.springboot.backend.backendspringboot.model.Reservation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResource {

    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";


    @GetMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getReservationId(@PathVariable String roomId) {
        //reservationService.getReservation(roomId);

        Reservation reservation = new Reservation(1L, LocalDate.now(), LocalDate.now(), 45 , "ID56POV");
        return Mono.just(reservation);

    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {
        //reservationService.createReservation(reservation);

        Reservation reservationResp = new Reservation(1L, LocalDate.now(), LocalDate.now(), 46 , "ID56POV");
        return Mono.just(reservationResp);

    }
}
