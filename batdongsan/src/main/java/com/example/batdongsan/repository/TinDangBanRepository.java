package com.example.batdongsan.repository;

import com.example.batdongsan.entity.TinDangBan;
import com.example.batdongsan.entity.TinDangStatus;
import com.example.batdongsan.request.TimKiemRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TinDangBanRepository extends CrudRepository<TinDangBan,Integer> {
    Page<TinDangBan> findAll(Pageable pageable);
    Page<TinDangBan> findAllByTitleContains(String keyword, Pageable pageable);

    Page<TinDangBan> findAllByTinDangStatus(TinDangStatus tinDangStatus, Pageable pageable);

    Page<TinDangBan> findAllByGiaBanBetweenAndTinDangStatus(double start, double end,TinDangStatus tinDangStatus, Pageable pageable);

    Page<TinDangBan> findAllByDienTichBetweenAndTinDangStatus(double start, double end,TinDangStatus tinDangStatus, Pageable pageable);

    @Modifying
    @Query(
            value = "SELECT * FROM tin_dang_ban t where t.gia_ban >= :minGiaBan and t.gia_ban <= :maxGiaBan and LOCATE(:keyword,t.mo_ta) > 0 " +
                    "and t.dien_tich >= :minDienTich and t.dien_tich <= :maxDienTich and t.thanh_pho = :thanhPho and t.quan_huyen = :quanHuyen and t.phuong = :phuongXa and t.loai_nha_dat_id = :loaiNhaDat_id",
            nativeQuery = true)
    Collection<TinDangBan> timKiemTinDangBan(@Param("minGiaBan")double minGiaBan,
                                             @Param("maxGiaBan")double maxGiaBan,
                                             @Param("keyword")String keyword,
                                             @Param("thanhPho") String thanhPho,
                                             @Param("quanHuyen")String quanHuyen,
                                             @Param("phuongXa") String phuongXa,
                                             @Param("minDienTich")double minDienTich,
                                             @Param("maxDienTich")double maxDienTich,
                                             @Param("loaiNhaDat_id")int loaiNhaDat_id);
}
