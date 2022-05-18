package vn.techmaster.hijpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.hijpa.model.Category;
import vn.techmaster.hijpa.model.Product;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestOnetoMany {
    @Autowired
    private EntityManager em;

    public void testProductCategory(){
        Category homeappliance = new Category("Home Appliance");
        Product fridge = new Product("Fridge", homeappliance);
        //em.persist(homeappliance);
        em.persist(fridge);
        assertThat(fridge.getCategory()).isEqualTo(homeappliance);
        em.flush();

        //var  homeappliance_id = homeappliance.getId();
        em.remove(fridge);
        em.flush();
    }

}
