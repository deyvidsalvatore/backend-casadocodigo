package com.deyvidsantos.shoppingapi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deyvidsantos.shoppingapi.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository{
    List<Shop> findAllByUserIdentifier(String userIdentifier);
    List<Shop> findAllByTotalGreaterThan(Float total);
    List<Shop> findAllByDateGreaterThan(Date date);
}
