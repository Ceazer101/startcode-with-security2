package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    String username;
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    int ranking;

    private List<ReservationResponse> listOfReservations = new ArrayList<>();

    public MemberResponse(Member m, boolean includeAll) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName =m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        if(includeAll){
            this.created = m.getCreated();
            this.edited = m.getEdited();
            this.ranking = m.getRanking();
        }

        if (m.getReservations().size() > 0) {
            listOfReservations = m.getReservations().stream().map(r
                    -> ReservationResponse.builder()
                    .id(r.getId())
                    .carId(r.getCar().getId())
                    .brand(r.getCar().getBrand())
                    .rentalDate(r.getRentalDate())
                    .build()
            ).collect(Collectors.toList());
        }
    }
}
