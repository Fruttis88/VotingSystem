package ru.grad.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends CrudRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v")
    List<Vote> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.restaurant.id=:restaurantId AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("restaurantId")int restaurantId, @Param("userId") int userId);


    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.votedate=:date")
    Vote getUserVotesToday(@Param("userId") int userId, @Param("date")LocalDate date);

}
