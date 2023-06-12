package shoppingmall.domain.member;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class LoginForm {

    private String mem_id;
    private String mem_pass;
}
