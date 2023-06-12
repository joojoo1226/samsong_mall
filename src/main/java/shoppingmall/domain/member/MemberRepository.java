package shoppingmall.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import shoppingmall.domain.defaultDto.DefaultResultDto;
import shoppingmall.domain.item.Item;
import shoppingmall.global.utils.SessionUtil;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private final MemberMapper memberMapper;
    private static Map<String, Member> store = new HashMap<>();
    private static int sequance = 0;

    public MemberRepository(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public User getUser(LoginForm loginDto) {
        return memberMapper.getUser(loginDto);
    }

    public DefaultResultDto login(User user) {
        if (user == null) {
            return DefaultResultDto.builder()
                    .success(false)
                    .message("아이디가 존재하지 않거나, 틀린 비밀번호 입니다.")
                    .build();
        } else {
            SessionUtil.setUserAttribute(user);

            return DefaultResultDto.builder()
                    .success(true)
                    .message("로그인 성공!")
                    .build();
        }
    }

    public DefaultResultDto logout() {
        SessionUtil.invalidate();

        return DefaultResultDto.builder()
                .success(true)
                .message("로그아웃 성공!")
                .build();
    }


    public Member save(Member member) {
        member.setMem_idx(++sequance);

        store.put(member.getMem_id(), member);

        return member;
    }

    public Member findById(String mem_id) {
        return store.get(mem_id);
    }

    public Optional<Member> findByLoginId(String mem_id) {
        return findAll().stream().filter(m -> m.getMem_id().equals(mem_id)).findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    public DefaultResultDto addCartHeart(CartHeart cartHeart) {
        User user = SessionUtil.getUserAttribute();
        if (user == null) {
            return DefaultResultDto.builder()
                    .message("로그인이 필요한 기능입니다.")
                    .success(false)
                    .build();
        }

        cartHeart.setMem_idx(user.getMem_idx());
        if (cartHeart.getType().equals("cart")) {
            memberMapper.addCart(cartHeart);
            return DefaultResultDto.builder()
                    .message("장바구니에 추가되었습니다.")
                    .success(true)
                    .build();
        }

        int count = memberMapper.isHeart(cartHeart);
        if (count == 0) {
            memberMapper.addHeart(cartHeart);
            return DefaultResultDto.builder()
                    .success(true)
                    .message("찜목록에 추가되었습니다.")
                    .build();
        } else {
            heartRemove(cartHeart);
            return DefaultResultDto.builder()
                    .success(true)
                    .message("찜목록에서 삭제 되었습니다.")
                    .build();
        }

    }

    private void heartRemove(CartHeart cartHeart) {
        memberMapper.removeHeart(cartHeart);
    }

    public List<Cart> getCart() {
        User user = SessionUtil.getUserAttribute();
        return memberMapper.getCart(user);
    }

    public DefaultResultDto deleteCart(Cart cart) {
        memberMapper.deleteCart(cart);
        return DefaultResultDto.builder().message("삭제성공").success(true).build();
    }

    public List<Item> getHeart() {
        User user = SessionUtil.getUserAttribute();
        return memberMapper.getHeart(user);
    }
}
