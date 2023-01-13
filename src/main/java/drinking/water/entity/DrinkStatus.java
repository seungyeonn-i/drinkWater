package drinking.water.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DrinkStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @ManyToOne
    @JoinColumn(name = "DRINK_STATUS")
    private Water water;

    private LocalDateTime drinkDate;
    private int how;


    public LocalDateTime getDrinkDate() {
        return drinkDate;
    }

    public void setDrinkDate(LocalDateTime drinkDate) {
        this.drinkDate = drinkDate;
    }

    public int getHow() {
        return how;
    }

    public void setHow(int how) {
        this.how = how;
    }
}
