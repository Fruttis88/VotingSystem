package ru.grad.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Vote;
import ru.grad.model.VoteResult;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends CrudRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT new ru.grad.model.VoteResult(v.restaurant.name, count(v.id), v.voteDate) FROM Vote v WHERE v.restaurant.id=:restaurantId GROUP BY v.restaurant.id, v.restaurant.name, v.voteDate")
    List<VoteResult> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=:date")
    Vote getUserVotesToday(@Param("userId") int userId, @Param("date")LocalDate date);

    @Override
    List<Vote> findAll();

}
