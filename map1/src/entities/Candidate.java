package entities;

import java.util.Objects;

public class Candidate {
    private String name;
    private int qtdVotes;

    public Candidate(String name, int qtdVotes) {
        this.name = name;
        this.qtdVotes = qtdVotes;
    }

    public int getQtdVotes() {
        return qtdVotes;
    }

    public void setQtdVotes(int qtdVotes) {
        this.qtdVotes = qtdVotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
