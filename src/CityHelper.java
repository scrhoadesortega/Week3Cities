import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Sofia Rhoades Ortega - scrhoadesortega
 * CIS175 - Fall 2021
 * Feb 8, 2022
 */

public class CityHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Cities");
	
	public void insertItem(City c) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<City> showAllItems( ){
		EntityManager em = emfactory.createEntityManager();
		List<City> allItems = em.createQuery("SELECT i FROM City i").getResultList();
		return allItems;
	}
	
	public void deleteItem(City toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<City> typedQuery = em.createQuery("select c from City c where c.name = :selectedName and "
				+ "c.zip = :selectedZip and c.population = :selectedPopulation", City.class);
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedZip", toDelete.getZip());
		typedQuery.setParameter("selectedPopulation", toDelete.getPopulation());
		typedQuery.setMaxResults(1);
		City result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<City> searchForPopulationByName(String cityName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<City> typedQuery = em.createQuery("select c from City c where c.name = :selectedName", City.class);
		typedQuery.setParameter("selectedName", cityName);
		List<City> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<City> searchForPopulationByZip(String cityZip){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<City> typedQuery = em.createQuery("select c from City c where c.zip = :selectedZip", City.class);
		typedQuery.setParameter("selectedZip", cityZip);
		List<City> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<City> searchForPopulationByPopulation(String cityPop){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<City> typedQuery = em.createQuery("select c from City c where c.population = :selectedPopulation", City.class);
		typedQuery.setParameter("selectedNPopulation", cityPop);
		List<City> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public City searchForPopulationById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		City found = em.find(City.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateItem(City toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	public void cleanUp(){
		emfactory.close();
	}
}
