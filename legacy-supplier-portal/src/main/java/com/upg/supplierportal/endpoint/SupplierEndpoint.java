package com.upg.supplierportal.endpoint;

import com.upg.supplierportal.entity.Supplier;
import com.upg.supplierportal.entity.SupplierCatalog;
import com.upg.supplierportal.generated.GetSupplierCatalogAndPricingRequest;
import com.upg.supplierportal.generated.GetSupplierCatalogAndPricingResponse;
import com.upg.supplierportal.generated.ProductType;
import com.upg.supplierportal.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Endpoint SOAP : operation getSupplierCatalogAndPricing.
 * Recupere le catalogue et les prix de gros d'un fournisseur identifie par son id.
 */
@Endpoint
public class SupplierEndpoint {

    private static final String NAMESPACE_URI = "http://upg.com/supplierportal";

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierEndpoint(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "getSupplierCatalogAndPricingRequest")
    @ResponsePayload
    public GetSupplierCatalogAndPricingResponse getSupplierCatalogAndPricing(
            @RequestPayload GetSupplierCatalogAndPricingRequest request) {

        Long supplierId = request.getSupplierId();

        // Recherche du fournisseur ; si absent, leve l'exception enveloppee en soap:Fault
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));

        GetSupplierCatalogAndPricingResponse response = new GetSupplierCatalogAndPricingResponse();
        response.setSupplierCode(supplier.getSupplierCode());
        response.setCompanyName(supplier.getCompanyName());

        for (SupplierCatalog catalog : supplier.getCatalogs()) {
            ProductType product = new ProductType();
            product.setSku(catalog.getSku());
            product.setProductName(catalog.getProductName());
            // BigDecimal -> xs:decimal, Integer -> xs:int (mappage SQL/WSDL correct)
            product.setWholesalePrice(catalog.getWholesalePrice());
            product.setLeadTimeDays(catalog.getLeadTimeDays());
            product.setSupplierStockLevel(catalog.getSupplierStockLevel());
            response.getProduct().add(product);
        }

        return response;
    }
}
