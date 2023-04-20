package hkmu.comps380f.dao;

import hkmu.comps380f.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, String> {
}
