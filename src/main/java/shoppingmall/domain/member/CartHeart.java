package shoppingmall.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CartHeart {

    private long mem_idx;
    private long prd_idx;
    private String type;        //찜,장바구니 구분
    private int crt_amount;
}
