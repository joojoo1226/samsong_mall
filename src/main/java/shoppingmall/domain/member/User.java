package shoppingmall.domain.member;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("user")
@ToString
public class User implements Serializable {

    private long mem_idx;
    private String mem_id;
    private String mem_name;
    private int mem_role;
    private String role_name;

    @Builder
    public User(long mem_idx, String mem_id, String mem_name, int mem_role, String role_name) {
        this.mem_idx = mem_idx;
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.mem_role = mem_role;
        this.role_name = role_name;
    }
}
