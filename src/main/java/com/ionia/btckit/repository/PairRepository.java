package com.ionia.btckit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ionia.btckit.model.Pair;

@Repository("pairRepository")
public interface PairRepository extends JpaRepository<Pair, String> {

}
