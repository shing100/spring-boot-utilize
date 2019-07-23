package com.kingname.springbootjpa.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kingname.springbootjpa.team.Team;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String username;

    private String password;

    private boolean isActive;

    private String email;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    @JsonBackReference
    private Team team;

}
