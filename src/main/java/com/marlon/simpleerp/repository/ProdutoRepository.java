package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca por nome (ignorando maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Busca por código de barras
    Produto findByCodigoBarras(String codigoBarras);

    // Verifica se já existe produto com esse código de barras (exceto ele mesmo)
    @Query("SELECT COUNT(p) > 0 FROM Produto p WHERE p.codigoBarras = :codigoBarras AND p.id <> :id")
    boolean existsByCodigoBarrasAndIdNot(@Param("codigoBarras") String codigoBarras, @Param("id") Long id);
}