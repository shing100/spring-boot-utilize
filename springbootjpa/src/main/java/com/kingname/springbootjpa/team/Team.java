package com.kingname.springbootjpa.team;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kingname.springbootjpa.account.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<Account>();

}
