package namvn.repository;

import namvn.model.Cay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiDao extends JpaRepository<Cay,Long> {
}
