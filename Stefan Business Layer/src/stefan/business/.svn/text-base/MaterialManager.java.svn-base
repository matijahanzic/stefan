/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import stefan.business.objects.CalculationForm;
import stefan.business.objects.Material;
import stefan.business.objects.MaterialDetails;
import stefan.data.Calculationform;
import stefan.data.Materialdetails;

/**
 *
 * @author Matija
 */
public class MaterialManager {

    private EntityManager entityManager;

    public MaterialManager() {
        entityManager = QueryManager.getEntityManagerInstance();
    }

    public void SaveMaterial(Material material) throws Exception {
        stefan.data.Material mat = new stefan.data.Material();
        stefan.data.Calculationform cform = new stefan.data.Calculationform();
        cform.setCalculationformname(material.getCalculationForm().getCalculationFormName());
        cform.setIdcalculationform(material.getCalculationForm().getIdCalculationForm());
        stefan.data.Materialdetails mdetails = new stefan.data.Materialdetails();
        mdetails.setDensity(material.getMaterialDetails().getDensity());
        mdetails.setIdMaterialDetails(material.getMaterialDetails().getIdMaterialDetails());
        mdetails.setMaterialName(material.getMaterialDetails().getMaterialName());
        mdetails.setNiklanje(material.getMaterialDetails().getNiklanje());
        mat.setIdShape(material.getIdShape());
        mat.setEuroPerKg(material.getEuroPerKg());
        mat.setName(material.getName());
        mat.setIdCalculationForm(cform);
        mat.setIdMaterialDetails(mdetails);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(mat);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public boolean SaveMaterialDetails(MaterialDetails details) {
        stefan.data.Materialdetails det = new stefan.data.Materialdetails();
        det.setDensity(details.getDensity());
        det.setMaterialName(details.getMaterialName());
        det.setNiklanje(details.getNiklanje());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(det);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public List<Material> GetAll() {
        List<stefan.data.Material> results = entityManager.createNamedQuery("Material.findAll").getResultList();
        return MapData(results);
    }

    public void DeleteMaterialDetails(int id) throws Exception {
        entityManager.getTransaction().begin();
        try {
            stefan.data.Materialdetails m = (stefan.data.Materialdetails) entityManager.createNamedQuery("Materialdetails.findByIdMaterialDetails").setParameter("idMaterialDetails", id).getSingleResult();
            entityManager.remove(m);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void DeleteMaterial(int materialId) throws Exception {
        entityManager.getTransaction().begin();
        try {
            stefan.data.Material m = (stefan.data.Material) entityManager.createNamedQuery("Material.findByIdShape").setParameter("idShape", materialId).getSingleResult();
            entityManager.remove(m);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public List<CalculationForm> GetAllCalculationForms() {
        List<stefan.data.Calculationform> results = entityManager.createNamedQuery("Calculationform.findAll").getResultList();

        return MapCalculationForms(results);

    }

    public List<MaterialDetails> GetAllMaterialDetails() {
        List<stefan.data.Materialdetails> results = entityManager.createNamedQuery("Materialdetails.findAll").getResultList();
        return MapResultList(results);
    }

    public stefan.business.objects.MaterialDetails GetMaterialDetailsById(int id) {
        Query q = entityManager.createNamedQuery("Materialdetails.findByIdMaterialDetails");
        q.setParameter("idMaterialDetails", id);
        stefan.data.Materialdetails materialDetails = (stefan.data.Materialdetails) q.getSingleResult();
        return MapSingleResult(materialDetails);
    }

    public stefan.business.objects.Material GetMaterialById(int id) {
        Query q = entityManager.createNamedQuery("Material.findByIdShape").setParameter("idShape", id);
        stefan.data.Material material = (stefan.data.Material) q.getSingleResult();
        return MapSingleResult(material);
    }
    
     public stefan.data.Material GetMaterialByIdDB (int id) {
        Query q = entityManager.createNamedQuery("Material.findByIdShape").setParameter("idShape", id);
        return (stefan.data.Material) q.getSingleResult();        
    }

    public List<Material> MapData(List<stefan.data.Material> materials) {
        List<Material> result = new ArrayList<Material>();

        for (stefan.data.Material m : materials) {
            result.add(MapData(m));
        }
        return result;
    }

    private List<MaterialDetails> MapResultList(List<Materialdetails> results) {
        List<MaterialDetails> result = new ArrayList<MaterialDetails>();
        for (stefan.data.Materialdetails m : results) {
            result.add(MapResultList(m));
        }
        return result;
    }

    private List<CalculationForm> MapCalculationForms(List<Calculationform> results) {
        List<CalculationForm> result = new ArrayList<CalculationForm>();
        for (stefan.data.Calculationform c : results) {
            result.add(MapCalculationFormsResult(c));
        }
        return result;
    }

    public Material MapData(stefan.data.Material material) {
        Material mat = new Material(material.getIdShape());
        mat.setEuroPerKg(material.getEuroPerKg());
        mat.setName(material.getName());
        MaterialDetails materialDetails = new MaterialDetails();
        materialDetails.setDensity(material.getIdMaterialDetails().getDensity());
        materialDetails.setMaterialName(material.getIdMaterialDetails().getMaterialName());
        materialDetails.setNiklanje(material.getIdMaterialDetails().getNiklanje());
        materialDetails.setIdMaterialDetails(material.getIdMaterialDetails().getIdMaterialDetails());
        mat.setMaterialDetails(materialDetails);
        CalculationForm calculationForm = new CalculationForm();
        calculationForm.setIdCalculationForm(material.getIdCalculationForm().getIdcalculationform());
        calculationForm.setCalculationFormName(material.getIdCalculationForm().getCalculationformname());
        mat.setCalculationForm(calculationForm);
        return mat;
    }

    private MaterialDetails MapResultList(Materialdetails m) {
        MaterialDetails md = new MaterialDetails(m.getIdMaterialDetails());
        md.setDensity(m.getDensity());
        md.setMaterialName(m.getMaterialName());
        md.setNiklanje(m.getNiklanje());
        return md;
    }

    private CalculationForm MapCalculationFormsResult(Calculationform c) {
        CalculationForm cf = new CalculationForm(c.getIdcalculationform());
        cf.setCalculationFormName(c.getCalculationformname());
        return cf;
    }

    private MaterialDetails MapSingleResult(Materialdetails materialDetails) {
        MaterialDetails m = new MaterialDetails(materialDetails.getIdMaterialDetails());
        m.setDensity(materialDetails.getDensity());
        m.setMaterialName(materialDetails.getMaterialName());
        m.setNiklanje(materialDetails.getNiklanje());
        return m;
    }

    public void UpdateMaterial(Integer idMaterialDetails, String name, BigDecimal density, Boolean niklanje) throws Exception {
        try {
            entityManager.getTransaction().begin();
            Materialdetails md = entityManager.find(Materialdetails.class, idMaterialDetails);
            md.setMaterialName(name);
            md.setDensity(density);
            md.setNiklanje(niklanje);
            entityManager.merge(md);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void UpdatePrice(Integer idShape, CalculationForm calculationForm, MaterialDetails materialDetails, String name, BigDecimal price) throws Exception {
        try {
            entityManager.getTransaction().begin();
            stefan.data.Material m = entityManager.find(stefan.data.Material.class, idShape);
            m.setEuroPerKg(price);
            m.setName(name);
            stefan.data.Materialdetails md = new stefan.data.Materialdetails(materialDetails.getIdMaterialDetails());
            md.setDensity(materialDetails.getDensity());
            md.setMaterialName(materialDetails.getMaterialName());
            md.setNiklanje(materialDetails.getNiklanje());
            m.setIdMaterialDetails(md);
            stefan.data.Calculationform cf = new stefan.data.Calculationform(calculationForm.getIdCalculationForm());
            cf.setCalculationformname(calculationForm.getCalculationFormName());
            m.setIdCalculationForm(cf);
            entityManager.merge(m);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    private Material MapSingleResult(stefan.data.Material material) {
        Material m = new Material(material.getIdShape());

        CalculationForm calculationForm = new CalculationForm();
        calculationForm.setIdCalculationForm(material.getIdCalculationForm().getIdcalculationform());
        calculationForm.setCalculationFormName(material.getIdCalculationForm().getCalculationformname());
        m.setCalculationForm(calculationForm);
        MaterialDetails materialDetails = new MaterialDetails();
        materialDetails.setDensity(material.getIdMaterialDetails().getDensity());
        materialDetails.setMaterialName(material.getIdMaterialDetails().getMaterialName());
        materialDetails.setNiklanje(material.getIdMaterialDetails().getNiklanje());
        materialDetails.setIdMaterialDetails(material.getIdMaterialDetails().getIdMaterialDetails());
        m.setMaterialDetails(materialDetails);
        m.setEuroPerKg(material.getEuroPerKg());
        m.setName(material.getName());
        return m;
    }

    
}
