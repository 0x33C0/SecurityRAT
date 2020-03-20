package org.appsec.securityrat.api.dto;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptColumnContent implements IdentifiableDto<Long> {
    @Getter(AccessLevel.NONE)
    private Long id;
    private String content;
    private OptColumn optColumn;
    private RequirementSkeleton requirementSkeleton;

    @Override
    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }
}