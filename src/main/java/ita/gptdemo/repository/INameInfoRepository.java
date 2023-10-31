package ita.gptdemo.repository;

import ita.gptdemo.entity.NameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INameInfoRepository extends JpaRepository<NameInfo, Long> {
    public Optional<NameInfo> findByName(String name);
}
