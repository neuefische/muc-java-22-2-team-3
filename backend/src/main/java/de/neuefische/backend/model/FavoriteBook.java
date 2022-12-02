package de.neuefische.backend.model;

import lombok.Data;

@Data
public class FavoriteBook extends Book{
    Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FavoriteBook that = (FavoriteBook) o;

        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public FavoriteBook(){
        super();
        this.status = Status.TOREAD;
    }
}
