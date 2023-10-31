package cz.inventi.kpj.KPJ_Homework.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Database extends JpaRepository<MessageEntity, Long> {
    Optional<MessageEntity> findByServiceName(String serviceName);
}
