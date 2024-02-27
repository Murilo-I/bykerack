package br.gov.sp.cptm.bykerack.data.respository;

import br.gov.sp.cptm.bykerack.data.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
}
