package model;

import java.util.UUID;
import lombok.ToString;

@ToString
public class User {
    private UUID id;
    private String name;
    private int funds;

    public User(UUID id, String name, int funds) {
        this.id = id;
        this.name = name;
        this.funds = funds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }
}
