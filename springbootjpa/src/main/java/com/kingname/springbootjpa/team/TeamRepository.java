package com.kingname.springbootjpa.team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TeamRepository extends CrudRepository<Team, Long> {

}
