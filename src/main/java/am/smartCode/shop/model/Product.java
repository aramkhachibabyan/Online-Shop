package am.smartCode.shop.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    private long id;
    private String category;
    private String name;
    private String publishedDate;
    private long price;

}