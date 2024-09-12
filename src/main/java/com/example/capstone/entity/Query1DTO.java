package com.example.capstone.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query1DTO {

    private Priority priority;
    private long totalticket;

    // Constructor for JPQL Constructor Expression
    public Query1DTO(Priority priority, long totalticket) {
        this.priority = priority;
        this.totalticket = totalticket;
    }

    // Getters and setters
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public long getTotalticket() {
        return totalticket;
    }

    public void setTotalTicket(long totalticket) {
        this.totalticket = totalticket;
    }
}