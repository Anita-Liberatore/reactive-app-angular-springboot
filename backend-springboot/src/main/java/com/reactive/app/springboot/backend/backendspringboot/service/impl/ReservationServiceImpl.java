package com.reactive.app.springboot.backend.backendspringboot.service.impl;

import com.reactive.app.springboot.backend.backendspringboot.model.Reservation;
import com.reactive.app.springboot.backend.backendspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReactiveMongoOperations reactiveMongoOperations;

    @Autowired
    public ReservationServiceImpl(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Reservation> getReservation(String id) {
        return reactiveMongoOperations.findById(id, Reservation.class);
    }

    @Override
    public Mono<Reservation> createReservation(Mono<Reservation> reservationMono) {
        return reactiveMongoOperations.save(reservationMono);
    }

    @Override
    public Mono<Reservation> updateReservation(String id, Mono<Reservation> reservationMono) {
        return reservationMono
                .flatMap(res -> reactiveMongoOperations.
                        findAndModify(Query.query(Criteria.where("id").is(id)),
                                Update.update("price", res.getPrice()), Reservation.class)
                        .flatMap(result -> {
                            result.setPrice(res.getPrice());
                            return Mono.just(result);
                        }));
    }

    @Override
    public Mono<Boolean> deleteReservation(String id) {
        return null;
    }
}
