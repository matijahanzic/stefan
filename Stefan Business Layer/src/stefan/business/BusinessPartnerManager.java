package stefan.business;

import stefan.business.objects.BusinessPartner;
import stefan.data.Businesspartner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BusinessPartnerManager {
    private final EntityManager entityManager;

    public BusinessPartnerManager() {
        this.entityManager = QueryManager.getEntityManagerInstance();
    }

    public List<BusinessPartner> getExternalBusinessPartners() {
        List<BusinessPartner> externalBps = new ArrayList<BusinessPartner>();

        try {
            Query q = entityManager.createNamedQuery("Businesspartner.findAllExternal");

            List<Businesspartner> storedExternalBps = q.getResultList();
            for (Businesspartner bp : storedExternalBps) {
                BusinessPartner extBp = translateBusinessPartner(bp);
                externalBps.add(extBp);
            }
        } catch (Exception ex) { /* Ignore */ }

        return externalBps;
    }

    public List<BusinessPartner> getInternalBusinessPartners() {
        List<BusinessPartner> externalBps = new ArrayList<BusinessPartner>();

        try {
            Query q = entityManager.createNamedQuery("Businesspartner.findAllInternal");

            List<Businesspartner> storedExternalBps = q.getResultList();
            for (Businesspartner bp : storedExternalBps) {
                BusinessPartner extBp = translateBusinessPartner(bp);
                externalBps.add(extBp);
            }
        } catch (Exception ex) { /* Ignore */ }

        return externalBps;
    }

    private static BusinessPartner translateBusinessPartner(Businesspartner bp) {
        BusinessPartner extBp = new BusinessPartner();
        extBp.setId(bp.getId());
        extBp.setName(bp.getName());
        extBp.setDisplayName(bp.getDisplayName());
        extBp.setPrintInd(bp.getPrintInd());
        extBp.setPrintRow1(bp.getPrintRow1());
        extBp.setPrintRow2(bp.getPrintRow2());
        extBp.setPrintRow3(bp.getPrintRow3());
        extBp.setCity(bp.getCity());
        extBp.setRequireShippingDate(bp.getRequireShippingDate());
        extBp.setIsExternalSource(bp.getIsExternalSource());
        return extBp;
    }

    public boolean saveBusinessPartner(BusinessPartner bussPartner) {
        try {
            Businesspartner newBp = new Businesspartner();
            if (bussPartner.getId() != null && bussPartner.getId() != 0) {
                entityManager.getTransaction().begin();
                Query q = entityManager.createNamedQuery("Businesspartner.findById");
                q.setParameter("id", bussPartner.getId());
                Object qResult = q.getSingleResult();
                if (qResult instanceof Businesspartner)
                    newBp = (Businesspartner)qResult;
            }

            newBp.setName(bussPartner.getName());
            newBp.setDisplayName(bussPartner.getDisplayName());
            newBp.setPrintInd(bussPartner.getPrintInd());
            newBp.setPrintRow1(bussPartner.getPrintRow1());
            newBp.setPrintRow2(bussPartner.getPrintRow2());
            newBp.setPrintRow3(bussPartner.getPrintRow3());
            newBp.setCity(bussPartner.getCity());
            newBp.setRequireShippingDate(bussPartner.getRequireShippingDate());
            newBp.setIsExternalSource(bussPartner.getIsExternalSource());

            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();

            entityManager.persist(newBp);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//mislim da ovo ne dela
            return false;
        }
    }

    public boolean deleteExternalBusinessPartner(BusinessPartner bp) {
        try {
            Query q = entityManager.createNamedQuery("Businesspartner.findById");
            q.setParameter("id", bp.getId());
            Object qRes = q.getSingleResult();
            if (!(qRes instanceof Businesspartner))
                return false;

            entityManager.getTransaction().begin();
            entityManager.remove(qRes);
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }
}
