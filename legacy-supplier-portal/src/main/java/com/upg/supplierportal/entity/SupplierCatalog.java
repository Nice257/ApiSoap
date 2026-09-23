package com.upg.supplierportal.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Table "supplier_catalog" : ligne de catalogue (produit + prix de gros +
 * delai de livraison + stock) fournie par un fournisseur.
 */
@Entity
@Table(name = "supplier_catalog")
public class SupplierCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** FK supplier_id vers supplier.id */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private String sku;

    @Column(name = "product_name")
    private String productName;

    /** DECIMAL en base -> BigDecimal en Java (xs:decimal dans le XSD). */
    @Column(name = "wholesale_price")
    private BigDecimal wholesalePrice;

    /** INT en base -> Integer en Java (xs:int dans le XSD). */
    @Column(name = "lead_time_days")
    private Integer leadTimeDays;

    @Column(name = "supplier_stock_level")
    private Integer supplierStockLevel;

    public SupplierCatalog() {
    }

    public SupplierCatalog(String sku, String productName, BigDecimal wholesalePrice,
                           Integer leadTimeDays, Integer supplierStockLevel) {
        this.sku = sku;
        this.productName = productName;
        this.wholesalePrice = wholesalePrice;
        this.leadTimeDays = leadTimeDays;
        this.supplierStockLevel = supplierStockLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    public void setLeadTimeDays(Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    public Integer getSupplierStockLevel() {
        return supplierStockLevel;
    }

    public void setSupplierStockLevel(Integer supplierStockLevel) {
        this.supplierStockLevel = supplierStockLevel;
    }
}
