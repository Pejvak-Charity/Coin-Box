package charity.pejvak.coinbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(generator = "seq_user_role", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_user_role", sequenceName = "seq_user_role", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
