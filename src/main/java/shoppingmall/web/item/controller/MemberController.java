package shoppingmall.web.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shoppingmall.domain.defaultDto.DefaultResultDto;
import shoppingmall.domain.item.Item;
import shoppingmall.domain.member.*;
import shoppingmall.global.config.model.ApiResponse;
import shoppingmall.global.utils.SessionUtil;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "member/join";
    }

    @PostMapping("/join")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "member/join";
        }

        memberMapper.save(member);

        return "redirect:/";

    }

    @GetMapping("/home")
    public String loginForm() {
        return "member/home";
    }

    @GetMapping("/loginForm")
    public String loginForm(@ModelAttribute("login") LoginForm form) {
        return "member/loginForm";
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<DefaultResultDto>> login(@RequestBody LoginForm loginForm) {
        User user = memberRepository.getUser(loginForm);
        System.out.println(user);
        return ResponseEntity.ok(new ApiResponse<DefaultResultDto>(memberRepository.login(user)));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<DefaultResultDto>> login() {
        return ResponseEntity.ok(new ApiResponse<>(memberRepository.logout()));
    }

    @PostMapping("/cartHeart/add")
    public ResponseEntity<ApiResponse<DefaultResultDto>> cartAdd(@RequestBody CartHeart cartHeart) {
        return ResponseEntity.ok(new ApiResponse<DefaultResultDto>(memberRepository.addCartHeart(cartHeart)));
    }

    @GetMapping("/myInfo")
    public String myiInfo() {
        User user = SessionUtil.getUserAttribute();
        if (user == null) {
            return "redirect:/member/loginForm";
        }

        return "member/myinfo";
    }

    @GetMapping("/cart/get")
    public ResponseEntity<ApiResponse<List<Cart>>> getCart() {
        return ResponseEntity.ok(new ApiResponse<List<Cart>>(memberRepository.getCart()));
    }

    @PostMapping("/cart/delete")
    public ResponseEntity<ApiResponse<DefaultResultDto>> deleteCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(new ApiResponse<DefaultResultDto>(memberRepository.deleteCart(cart)));
    }

    @GetMapping("/heart/get")
    public ResponseEntity<ApiResponse<List<Item>>> getHeart() {
        return ResponseEntity.ok(new ApiResponse<List<Item>>(memberRepository.getHeart()));
    }

}
