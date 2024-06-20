package repository;

public interface Userrepository  extends JpaRepository<Usuario, Long>{
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
