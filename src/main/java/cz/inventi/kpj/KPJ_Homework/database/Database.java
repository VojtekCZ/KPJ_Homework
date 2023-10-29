package cz.inventi.kpj.KPJ_Homework.database;

public interface Database extends JpaRepository<Microservice, Long> {
    Optional<Microservice> findByServiceName(String serviceName);
}
