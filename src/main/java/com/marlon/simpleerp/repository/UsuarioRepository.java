package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca por email
    Optional<Usuario> findByEmail(String email);

    // Verifica se email já existe (exceto o próprio usuário)
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = :email AND u.id <> :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
}