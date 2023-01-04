package com.online_movie_ticket_reservation_system.omtrs.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_movie_ticket_reservation_system.omtrs.Enum.Roles;
import com.online_movie_ticket_reservation_system.omtrs.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by rohit  Tamang
 * on 11 Dec, 2022
 */

@Entity
@Table(name = "seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "seat_no")
    private int seatNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
