package shoppingmall.domain.item;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
public class Item {

    private int hrt_idx;
    private String mem_id;
    private String mem_name;
    private String file_id;
    private long mem_idx;

    private int prd_idx;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
