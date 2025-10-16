package hub.com.guesmonsterapi.repo;

import hub.com.guesmonsterapi.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonsterRepo extends JpaRepository<Monster, Long> {

    @Query(value = "SELECT * FROM monster ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Monster findRandom();

    @Query(value = "SELECT imagen_silueta FROM monster WHERE id = :id", nativeQuery = true)
    String findImagenSiluetaById(@Param("id") Long id);

    @Query(value = "SELECT imagen_real FROM monster WHERE id = :id", nativeQuery = true)
    String findImagenRealById(@Param("id") Long id);
}
