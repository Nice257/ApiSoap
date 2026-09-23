package com.upg.supplierportal.repository;

import com.upg.supplierportal.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Accès JPA à la table "supplier".
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
