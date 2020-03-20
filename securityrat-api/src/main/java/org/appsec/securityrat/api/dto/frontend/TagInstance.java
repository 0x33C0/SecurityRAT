package org.appsec.securityrat.api.dto.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagInstance {
    private Long id;
    private String name;
    private String description;
    private Integer showOrder;
}