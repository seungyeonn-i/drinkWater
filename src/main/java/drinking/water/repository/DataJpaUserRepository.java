package drinking.water.repository;

import drinking.water.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaUserRepository extends JpaRepository<User,Long> {
}
