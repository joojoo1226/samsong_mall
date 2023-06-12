package shoppingmall.domain.member;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import shoppingmall.domain.item.Item;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM member WHERE mem_id = #{mem_id} AND mem_pass = #{mem_pass}")
    public User getUser(LoginForm loginForm);

    @Insert("insert into member(mem_id, mem_pass, mem_name) values (#{mem_id}, #{mem_pass}, #{mem_name})")
    public void save(Member member);

    @Select("select * from member where member_id=#{mem_id}")
    public Member findOneById(Long memberId);

    @Select("select * from member")
    public List<Member> findAll();

    @Insert("INSERT INTO cart(mem_idx, prd_idx, crt_amount) values (#{mem_idx}, #{prd_idx}, #{crt_amount})")
    void addCart(CartHeart cartHeart);

    @Insert("INSERT INTO heart(mem_idx, prd_idx) values (#{mem_idx}, #{prd_idx})")
    void addHeart(CartHeart cartHeart);

    @Select("SELECT COUNT(*) FROM heart WHERE mem_idx = #{mem_idx} AND prd_idx = #{prd_idx}")
    int isHeart(CartHeart cartHeart);

    @Delete("DELETE FROM heart WHERE mem_idx = #{mem_idx} AND prd_idx = #{prd_idx}")
    void removeHeart(CartHeart cartHeart);

    @Select("SELECT product.prd_idx ,cart.crt_idx, cart.crt_amount * product.prd_price AS total_price, multimedia.file_id, product.prd_name, cart.crt_amount FROM cart JOIN product ON cart.prd_idx = product.prd_idx JOIN multimedia ON multimedia.prd_idx = product.prd_idx WHERE cart.mem_idx = #{mem_idx}")
    List<Cart> getCart(User user);

    @Delete("DELETE FROM cart WHERE crt_idx = #{crt_idx}")
    void deleteCart(Cart cart);

    @Select("SELECT hrt.prd_idx, hrt.hrt_idx as hrt_idx, m.file_id as file_id, p.prd_name as itemName, p.prd_stock as quantity, p.prd_price as price FROM heart hrt JOIN product p ON hrt.prd_idx = p.prd_idx JOIN MULTIMEDIA m ON m.prd_idx = p.prd_idx WHERE hrt.mem_idx = #{mem_idx};")
    List<Item> getHeart(User user);
}
