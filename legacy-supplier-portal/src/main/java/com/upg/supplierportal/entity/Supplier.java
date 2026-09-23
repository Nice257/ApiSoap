package com.upg.supplierportal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Table "supplier" : identite du fournisseur.
 */
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_code", unique = true)
    private String supplierCode;

    @Column(name = "company_name")
    private String companyName;

    /** Relation OneToMany : un fournisseur a plusieurs lignes de catalogue. */
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierCatalog> catalogs = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String supplierCode, String companyName) {
        this.supplierCode = supplierCode;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<SupplierCatalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<SupplierCatalog> catalogs) {
        this.catalogs = catalogs;
    }
}
