package charity.pejvak.coinbox.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(generator = "seq_role", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1)
    private Long id;

    private String name;

}
