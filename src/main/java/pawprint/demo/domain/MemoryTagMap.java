package pawprint.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import pawprint.demo.domain.base.BaseEntity;

import static jakarta.persistence.FetchType.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MemoryTagMap extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
