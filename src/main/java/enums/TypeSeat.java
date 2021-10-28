package enums;

import lombok.Data;

public enum TypeSeat {
    HARD("Ghế cứng"), SOFT("Ghế mềm"), BED("Giường nằm");

    private String description;

    TypeSeat(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
