package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        memberService.editMember(body,username);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PatchMapping("/ranking/{username}/{value}")
    public void setRankingForUser(@PathVariable String username, @PathVariable int value){
        memberService.setRankingForUser(username,value);
    }

    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers();
    }

     /* JUST TO SHOW HOW NOT TO DO IT
  @Autowired
  MemberRepository memberRepository;
  @GetMapping("/bad")
  public List<Member> getMembersBad(){
    return memberRepository.findAll();
  }
  ***********/

    @GetMapping(path = "/{username}")
    public MemberResponse getMemberById(@PathVariable String username) throws Exception { //Obviously we need to be able to limit this in a system with thousands of members
        MemberResponse response = memberService.findMemberByUsername(username);
        return response;
    }

    @DeleteMapping("/{username}")
    public void deleteMemberByUsername(@PathVariable String username){
        memberService.deleteByUsername(username);
    }
}
