package com.example.proy_grupo4.Repository;

import com.example.proy_grupo4.Entity.UsuariosRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuariosRegistrado,String> {

    UsuariosRegistrado findByCorreo(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE usuarios_registrados SET telefono = ?1 WHERE (codigo = ?2)")
        void actualizarTelefono(String phone, String codigous);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE usuarios_registrados SET telefono = ?1, icono = 2 WHERE (codigo = ?2)")
    void actualizar(String phone, String codigo);

    @Query(nativeQuery = true, value = "select `rol` from `reportpucp`.`usuarios_registrados`  WHERE (`codigo` = ?1)")
    Integer rolporid(String id);

}
