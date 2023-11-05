package cz.inventi.kpj.KPJ_Homework.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface Database extends JpaRepository<MessageEntity, UUID> {
    Optional<MessageEntity> findByServiceName(String serviceName);
}
