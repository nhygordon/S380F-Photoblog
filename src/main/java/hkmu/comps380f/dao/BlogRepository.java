package hkmu.comps380f.dao;

import hkmu.comps380f.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

