package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvinceResponse {
    private Integer id;
    private String name;
    private final Set<City> cities = new HashSet<>();
}
