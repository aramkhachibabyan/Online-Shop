package am.smartCode.shop.model;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long userId;
    private long productId;
    private int countOfProduct;
    private long totalPrice;
}
