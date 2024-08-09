package br.com.brinquedos.repository;

import br.com.brinquedos.entity.Brinquedo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}
