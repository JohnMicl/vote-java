package com.kangkang.vote.modle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepo extends CrudRepository<Vote, Integer>{
    
    @Query("SELECT SUM(v.spiderMan1) FROM Vote AS v")
    Integer Sum1Voted();

    @Query("SELECT SUM(v.spiderMan2) FROM Vote AS v")
    Integer Sum2Voted();

    @Query("SELECT SUM(v.spiderMan3) FROM Vote AS v")
    Integer Sum3Voted();
}
