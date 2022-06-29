package com.example.batdongsan.controller;

import com.example.batdongsan.entity.TinDangBan;
import com.example.batdongsan.entity.TinDangStatus;
import com.example.batdongsan.entity.TinDangThue;
import com.example.batdongsan.exception.NotFoundException;
import com.example.batdongsan.repository.DanhMucRepository;
import com.example.batdongsan.repository.LoaiNhaDatRepository;
import com.example.batdongsan.repository.TinDangBanRepository;
import com.example.batdongsan.repository.TinDangThueRepository;
import com.example.batdongsan.request.TimKiemRequest;
import com.example.batdongsan.service.TimKiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TinDangBanRepository tinDangBanRepository;

    @Autowired
    private TinDangThueRepository tinDangThueRepository;

    @Autowired
    private LoaiNhaDatRepository loaiNhaDatRepository;

    @Autowired
    private TimKiemService timKiemService;
    @GetMapping("/trang-chu")
    public String homePage(Model model){
        model.addAttribute("loaiNhaDat", loaiNhaDatRepository.findAll());
        model.addAttribute("timKiemRequest",new TimKiemRequest());
        return "home/index";
    }

    @GetMapping("/trang-chu/timkiem")
    public String timKiemPage(Model model ,TimKiemRequest timKiemRequest){
        model.addAttribute("listTinDangBan",timKiemService.timKiemTinDangBan(timKiemRequest));
        return "hello";
    }

    @GetMapping("/trang-chu/tindangban")
    public String tinDangBanPage(Model model){
        return tindangPage(1,model);
    }

    @GetMapping("/trang-chu/tindangban/{pageNumber}")
    public String tindangPage(@PathVariable("pageNumber") int pageNumber, Model model){
        int currentPage = pageNumber;
        Page<TinDangBan> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,4);
        page = tinDangBanRepository.findAllByTinDangStatus(TinDangStatus.PUBLIC,pageable);

        List<TinDangBan> tinDangBanList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangBan",tinDangBanList);
        return "home/product-tindangban";
    }

    @GetMapping("/trang-chu/tindangban/detail/{id}")
    public String detailTinDangBan(@PathVariable int id,Model model){
         TinDangBan tinDangBan = tinDangBanRepository.findById(id).orElseThrow(() ->  new NotFoundException("No data!"));
         model.addAttribute("tindangban",tinDangBan);
         return  "home/productitem-tindangban";
    }
    @GetMapping("/trang-chu/tindangban/filterGiaBan/{pageNumber}")
    public String filterTDBgiaBan(@PathVariable("pageNumber") int pageNumber,@RequestParam("from") double from,@RequestParam("to") double to, Model model){
        int currentPage = pageNumber;
        Page<TinDangBan> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,2);
        page = tinDangBanRepository.findAllByGiaBanBetweenAndTinDangStatus(from,to,TinDangStatus.PUBLIC,pageable);

        List<TinDangBan> tinDangBanList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangBan",tinDangBanList);
        return "home/product-tindangban";
    }

    @GetMapping("/trang-chu/tindangban/filterDienTich/{pageNumber}")
    public String filterTDBdienTich(@PathVariable("pageNumber") int pageNumber,@RequestParam("from") double from,@RequestParam("to") double to, Model model){
        int currentPage = pageNumber;
        Page<TinDangBan> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,2);
        page = tinDangBanRepository.findAllByDienTichBetweenAndTinDangStatus(from,to,TinDangStatus.PUBLIC,pageable);

        List<TinDangBan> tinDangBanList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangBan",tinDangBanList);
        return "home/product-tindangban";
    }

    // TIN DANG THUE
    @GetMapping("/trang-chu/tindangthue")
    public String tinDangThuePage(Model model){
        return tindangThuePage(1,model);
    }

    @GetMapping("/trang-chu/tindangthue/{pageNumber}")
    public String tindangThuePage(@PathVariable("pageNumber") int pageNumber, Model model){
        int currentPage = pageNumber;
        Page<TinDangThue> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,4);
        page = tinDangThueRepository.findAllByTinDangStatus(TinDangStatus.PUBLIC,pageable);

        List<TinDangThue> tinDangThueList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangThue",tinDangThueList);
        return "home/product-tindangthue";
    }

    @GetMapping("/trang-chu/tindangthue/filterGiaBan/{pageNumber}")
    public String filterTDTgiaBan(@PathVariable("pageNumber") int pageNumber,@RequestParam("from") double from,@RequestParam("to") double to, Model model){
        int currentPage = pageNumber;
        Page<TinDangThue> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,2);
        page = tinDangThueRepository.findAllByGiaBanBetweenAndTinDangStatus(from,to,TinDangStatus.PUBLIC,pageable);

        List<TinDangThue> tinDangBanList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangThue",tinDangBanList);
        return "home/product-tindangthue";
    }

    @GetMapping("/trang-chu/tindangthue/filterDienTich/{pageNumber}")
    public String filterTDTdienTich(@PathVariable("pageNumber") int pageNumber,@RequestParam("from") double from,@RequestParam("to") double to, Model model){
        int currentPage = pageNumber;
        Page<TinDangThue> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,2);
        page = tinDangThueRepository.findAllByGiaBanBetweenAndTinDangStatus(from,to,TinDangStatus.PUBLIC,pageable);

        List<TinDangThue> tinDangBanList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listTinDangThue",tinDangBanList);
        return "home/product-tindangthue";
    }


    @GetMapping("/trang-chu/tindangthue/detail/{id}")
    public String detailTinDangThue(@PathVariable int id,Model model){
        TinDangThue tinDangThue= tinDangThueRepository.findById(id).orElseThrow(() ->  new NotFoundException("No data!"));
        model.addAttribute("tindangthue",tinDangThue);
        return  "home/productitem-tindangthue";
    }
}
