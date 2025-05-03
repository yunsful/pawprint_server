package pawprint.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import pawprint.demo.domain.base.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Plan extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Boolean isChecked;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
