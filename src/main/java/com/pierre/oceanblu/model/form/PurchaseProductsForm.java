package com.pierre.oceanblu.model.form;

import com.pierre.oceanblu.model.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseProductsForm {

    private Status status;
    private Long existingProductID;
    private Integer quantity;
    private Product newProduct;
    private MultipartFile newProductImage;

    public enum Status {

        EXISTING_PRODUCT("existingProduct"),
        NEW_PRODUCT("newProduct"),
        TEST_CASE_VALUE("testCaseValue");

        private final String value;

        Status(String value) {

            this.value = value;
        }

        @Override
        public String toString() {

            return value;
        }
    }
}
