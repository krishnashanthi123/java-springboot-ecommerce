package com.ecommerce.dtoRequest;

import java.util.List;

public class ProductTagMappingRequestDto {

    private Long productId;

    private List<Long> tagIds;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
