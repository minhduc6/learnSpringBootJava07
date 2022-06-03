package com.example.projecttest;

import com.example.projecttest.model.unidirection.Category;
import com.example.projecttest.model.unidirection.Product;
import com.example.projecttest.repository.CategoryRepository;
import com.example.projecttest.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class onetomany {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Test
    void testOrphanRemovalOneMany() {
        Category cate = new Category("Công Nghệ");
        Product pd1 = new Product("Laptop");
        Product pd2 = new Product("Mobile");
        cate.addProduct(pd1);
        cate.addProduct(pd2);
        categoryRepository.save(cate);
        productRepository.save(pd1);
        productRepository.save(pd2);
        assertThat(cate.getProducts()).hasSize(2);
        long id1 = pd1.getId();
        long id2 = pd2.getId();
        categoryRepository.deleteById(cate.getId());
        Optional<Product> product = Optional.of(productRepository.findById(id1).get());
        assertThat(product).isNotNull();
        System.out.println(product);
    }


}
