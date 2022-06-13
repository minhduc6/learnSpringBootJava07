package vn.techmaster.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.bank.model.*;
import vn.techmaster.bank.request.*;
import vn.techmaster.bank.service.BankService;

@RestController
@RequestMapping("/api")
public class CommandController {

    @Autowired
    private  BankService bankService;
    @PostMapping("/deposit")
    public ResponseEntity<AccountInfo> deposit(@RequestBody DepositRequest depositRequest){
        return  ResponseEntity.ok(bankService.deposit(depositRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountInfo> withdraw(@RequestBody WithDrawRequest withDrawRequest){
        return  ResponseEntity.ok(bankService.withDraw(withDrawRequest));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferInfo> transfer(@RequestBody TransferRequest transferRequest){
        return  ResponseEntity.ok(bankService.tranfer(transferRequest));
    }
    @PostMapping("/saving")
    public ResponseEntity<SavingInfo> saving(@RequestBody SavingRequest savingRequest){
        return  ResponseEntity.ok(bankService.saving(savingRequest));
    }

    @PostMapping("/rutLaiTheoThang")
    public ResponseEntity<RutLaiTheoThang> saving(@RequestBody RutLaiTheoThangRequest rutLaiTheoThangRequest){
        return  ResponseEntity.ok(bankService.rutLaiTheoThang(rutLaiTheoThangRequest));
    }

    @PostMapping("/savingTheoThang")
    public ResponseEntity<SavingInfo> savingTheoThang(@RequestBody SavingRequest savingRequest){
        return  ResponseEntity.ok(bankService.saving(savingRequest));
    }
    @PostMapping("/savingKhongKyHan")
    public ResponseEntity<KhongKyHan> saving(@RequestBody KhongKyHanRequest savingRequest){
        return  ResponseEntity.ok(bankService.savingKhongKyHan(savingRequest));
    }
    @PostMapping("/tatToan")
    public ResponseEntity<TatToanInfo> tatToan(@RequestBody TatToanRequest tatToanRequest){
        return  ResponseEntity.ok(bankService.tatToan(tatToanRequest));
    }
    @PostMapping("/hoanThanh")
    public ResponseEntity<SuccessTietKiem> hoanThanhTietKiem(@RequestBody HoanThanhTietKiemRequest hoanThanhTietKiemRequest){
        return  ResponseEntity.ok(bankService.successTietKiem(hoanThanhTietKiemRequest));
    }
}
