package com.example.batdongsan;

import com.example.batdongsan.entity.TinDangBan;
import com.example.batdongsan.repository.TinDangBanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TImKiemTest {
    @Autowired
    private TinDangBanRepository tinDangBanRepository;

    @Test
    void testSearch() {
        List<TinDangBan> list = tinDangBanRepository.timKiemTinDangBanZ(5,100,"Thanh Nhàn","Thành phố Hà Nội","Quận Hai Bà Trưng","Phường Thanh Nhàn",100,1000,2);
        assertThat(list).isNotEmpty();

    }
}
