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
public class Mission extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private Integer order;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
}
