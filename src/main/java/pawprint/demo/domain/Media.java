package pawprint.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import pawprint.demo.domain.base.BaseEntity;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Media extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String filename;
    private String filePath;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;
    
}
