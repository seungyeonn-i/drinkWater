package drinking.water.repository;

import drinking.water.domain.User;
import drinking.water.domain.userweb.RegisterForm;
import drinking.water.domain.userweb.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public UserRes save(RegisterForm form) {
        User user = new User(form.getUserName(), form.getUserEmail(), form.getUserPhone(), form.getLoginId(), form.getLoginPw());
        em.persist(user);
        return null;
    }

    @Override
    public Optional<User> findById(Long userId) {

        System.out.println("!!"+em.find(User.class,userId).getUserId());
        return Optional.ofNullable(em.find(User.class, userId));
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
