package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Rental;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.RentalRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;
    RentalRepository rentalRepository;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MemberRepository memberRepository,
                         CarRepository carRepository, ReservationRepository reservationRepository, RentalRepository rentalRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        passwordUsedByAll = "test12";
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();

        Car c1 = new Car("Toyota", "Corolla", 666, 50);
        carRepository.save(c1);

        Member m1 = new Member("userxx", passwordUsedByAll, "a@b.dk", "Kurt", "Kurtsen",
                "Gadevej", "Helvede", "666");
        memberRepository.save(m1);

        //LocalDate date = LocalDate.of(2022,9,6);
        LocalDate date = LocalDate.now();
        Reservation r1 = new Reservation(m1, c1, date);
        reservationRepository.save(r1);

        Rental rent1 = new Rental(m1, c1, date);
        rentalRepository.save(rent1);
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
