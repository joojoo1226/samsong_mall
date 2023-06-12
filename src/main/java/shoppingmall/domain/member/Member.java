package shoppingmall.domain.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class Member {

    private int mem_idx;
    private String mem_name;
    private String mem_id;
    private String mem_pass;
    private String mem_email;
    private boolean mem_role;

    public Member(String mem_name, String mem_id, String mem_pass, String mem_email) {
        this.mem_name = mem_name;
        this.mem_id = mem_id;
        this.mem_pass = mem_pass;
        this.mem_email = mem_email;
    }
}
