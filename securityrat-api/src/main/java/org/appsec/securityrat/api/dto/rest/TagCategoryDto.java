package org.appsec.securityrat.api.dto.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.appsec.securityrat.api.dto.SimpleDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagCategoryDto extends SimpleDto<Long> {
    private String name;
    private String description;
    private Integer showOrder;
    private Boolean active;
    
    public TagCategoryDto() {
        super(Long.class);
    }
}
