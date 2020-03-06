package org.appsec.securityrat.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingTreeNode {
    private Long id;
    private TrainingTreeNodeType node_type;
    private Integer sort_order;
    private Boolean active;
    private String content;
    private String name;
    private boolean opened;
    private List<TrainingTreeNode> children;
    private Integer anchor;
    private Long json_training_id;
    private Long json_universal_id;
}
