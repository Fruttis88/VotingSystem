package ru.grad.model;

import java.time.LocalDate;

public class VoteResult {
    private String restaurantName;
    private Integer voteCount;
    private LocalDate voteDate;

    public VoteResult(){
    }

    public VoteResult(String restaurantName, Long voteCount, LocalDate voteDate) {
        this.restaurantName = restaurantName;
        this.voteCount = voteCount.intValue();
        this.voteDate = voteDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    @Override
    public String toString() {
        return "VoteResult{" +
                "restaurantName='" + restaurantName + '\'' +
                ", voteCount=" + voteCount +
                ", voteDate=" + voteDate +
                '}';
    }
}
