package de.neuefische.backend.model;

import lombok.Data;

@Data
public class FavoriteBook extends Book{
    Status status;

    public FavoriteBook(){
    }

    public FavoriteBook(Book book){

        this.setId(book.getId());
        this.setAuthor(book.getAuthor());
        this.setIsbn(book.getIsbn());
        this.setDescription(book.getDescription());
        this.status = Status.TOREAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteBook that)) return false;
        if (!super.equals(o)) return false;

        return getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
