package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static String m1;
    @BeforeAll
    public static void setUpData(@Autowired MemberRepository memberRepository){
        Member member1 = new Member("userxx", "test12", "a@b.dk", "Kurt", "Kurtsen",
                "Gadevej", "Helvede", "666", true, "Elite");

        memberRepository.save(member1);

        m1 = member1.getUsername();
    }

    @Test
    public void testFindByUsername(){
        Member found = memberRepository.findById(m1).get();
        assertEquals(m1, found.getUsername());
    }

}