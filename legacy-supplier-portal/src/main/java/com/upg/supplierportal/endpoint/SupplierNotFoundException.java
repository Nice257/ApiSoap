package com.upg.supplierportal.endpoint;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Exception métier : fournisseur absent de la base.
 * L'annotation @SoapFault demande à Spring-WS d'envelopper cette exception
 * dans un <soap:Fault> avec le code CLIENT et la raison "Fournisseur introuvable".
 */
@SoapFault(faultCode = FaultCode.CLIENT, faultStringOrReason = "Fournisseur introuvable")
public class SupplierNotFoundException extends RuntimeException {

    private final Long supplierId;

    public SupplierNotFoundException(Long supplierId) {
        super("Fournisseur introuvable : " + supplierId);
        this.supplierId = supplierId;
    }

    public Long getSupplierId() {
        return supplierId;
    }
}
