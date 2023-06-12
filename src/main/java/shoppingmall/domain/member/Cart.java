package shoppingmall.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cart {

    private int prd_idx;
    private int crt_idx;
    private String prd_name;
    private String file_id;
    private int crt_amount;
    private int total_price;
}
