package vn.techmaster.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.bank.exception.CommandException;
import vn.techmaster.bank.exception.NotFoundException;
import vn.techmaster.bank.exception.TradeException;
import vn.techmaster.bank.model.*;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.CommandRepo;
import vn.techmaster.bank.repository.UserRepo;
import vn.techmaster.bank.request.*;

import java.time.LocalDateTime;
import java.time.Period;

@Service
public class BankService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CommandRepo commandRepo;

    public AccountInfo deposit(DepositRequest depositRequest) {
        User user = userRepo.findById(depositRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(depositRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!account.getUser().getId().equals(depositRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(account.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        account.setBalance(account.getBalance() + depositRequest.getAmount());
        Deposit deposit = new Deposit(user, depositRequest.getAmount(), account);
        AccountInfo accountInfo;
        try {
            accountRepo.save(account);
            deposit.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(deposit);
            accountInfo = new AccountInfo(depositRequest.getAccount_id(), account.getBank().getName(), account.getBalance());
        } catch (Exception exception) {
            deposit.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(deposit);
            var commanEx = new CommandException("Deposit Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }

    public AccountInfo withDraw(WithDrawRequest withDrawRequest) {
        User user = userRepo.findById(withDrawRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(withDrawRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!account.getUser().getId().equals(withDrawRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(account.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        account.setBalance(account.getBalance() - withDrawRequest.getAmount());
        WithDraw withDraw = new WithDraw(user, withDrawRequest.getAmount(), account);
        AccountInfo accountInfo;
        try {
            accountRepo.save(account);
            withDraw.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(withDraw);
            accountInfo = new AccountInfo(withDrawRequest.getAccount_id(), account.getBank().getName(), account.getBalance());
        } catch (Exception exception) {
            withDraw.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(withDraw);
            var commanEx = new CommandException("Deposit Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }

    public TransferInfo tranfer(TransferRequest transferRequest) {
        User user = userRepo.findById(transferRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountSend = accountRepo.findById(transferRequest.getAccount_send()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountRecv = accountRepo.findById(transferRequest.getAccount_recv()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!accountSend.getUser().getId().equals(transferRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountSend.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if(accountRecv.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if(transferRequest.getAmount() > accountSend.getBalance()){
            throw new TradeException("Số dư của bạn không đủ");
        }
        if(transferRequest.getAmount() <= 0){
            throw new TradeException("Số tiền chuyển phải lớn hơn 0");
        }
        accountSend.setBalance(accountSend.getBalance() - transferRequest.getAmount());
        accountRecv.setBalance(accountRecv.getBalance() + transferRequest.getAmount());
        Transact transact = new Transact(user,accountSend,accountRecv,transferRequest.getAmount());
        TransferInfo accountInfo;
        try {
            accountRepo.save(accountSend);
            accountRepo.save(accountRecv);
            transact.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(transact);
            accountInfo = new TransferInfo("Chuyển tiền thành công" , accountSend.getBalance());
        } catch (Exception exception) {
            transact.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(transact);
            var commanEx = new CommandException("Transfer Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }

    public SavingInfo saving(SavingRequest savingRequest){
        User user = userRepo.findById(savingRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountSend = accountRepo.findById(savingRequest.getAccountSend_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountRecv = accountRepo.findById(savingRequest.getAccountRecv_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!accountSend.getUser().getId().equals(savingRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountSend.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if (!accountSend.getUser().getId().equals(savingRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountRecv.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if(savingRequest.getAmount() > accountSend.getBalance()){
            throw new TradeException("Số dư của bạn không đủ");
        }
        if(savingRequest.getAmount() <= 0){
            throw new TradeException("Số tiền tiết kiệm phải lớn hơn 0");
        }
        accountSend.setBalance(accountSend.getBalance()-savingRequest.getAmount());
        double laiSuat = tinhLaiSuot(savingRequest.getKyHan(),savingRequest.getAmount());
        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeEnd = tinhNgayDaoHan(savingRequest.getKyHan(),timeStart);
        SavingInfo savingInfo;

        Saving saving = new Saving(user,savingRequest.getAmount(),accountSend,accountRecv,savingRequest.getKyHan().label.toString(),laiSuat,StatusSaving.DANG_GUI.label.toString(),timeStart,timeEnd);
        try {
            accountRepo.save(accountSend);
            saving.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(saving);
            savingInfo = new SavingInfo(user.getId(),accountSend.getId(),savingRequest.getKyHan().label,laiSuat,savingRequest.getAmount(),laiSuat+savingRequest.getAmount(),timeStart,timeEnd);
        } catch (Exception exception) {
            saving.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(saving);
            var commanEx = new CommandException("Saving Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return savingInfo;
    }


    public KhongKyHan savingKhongKyHan(KhongKyHanRequest savingRequest){
        User user = userRepo.findById(savingRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountSend = accountRepo.findById(savingRequest.getAccountSend_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountRecv = accountRepo.findById(savingRequest.getAccountRecv_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!accountSend.getUser().getId().equals(savingRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountSend.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if(savingRequest.getAmount() > accountSend.getBalance()){
            throw new TradeException("Số dư của bạn không đủ");
        }
        if(savingRequest.getAmount() <= 0){
            throw new TradeException("Số tiền tiết kiệm phải lớn hơn 0");
        }
        accountSend.setBalance(accountSend.getBalance()-savingRequest.getAmount());
        double laiSuat = 0.01 * savingRequest.getAmount();
        LocalDateTime timeStart = LocalDateTime.now();
        KhongKyHan savingInfo;

        Saving saving = new Saving(user,savingRequest.getAmount(),accountSend,accountRecv,"Không kỳ hạn",laiSuat,StatusSaving.DANG_GUI.label.toString(),timeStart,null);
        try {
            accountRepo.save(accountSend);
            saving.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(saving);
            savingInfo = new KhongKyHan(user.getId(),accountSend.getId(),accountRecv.getId(),"Không thời hạn",laiSuat,savingRequest.getAmount(),laiSuat+savingRequest.getAmount(),timeStart);
        } catch (Exception exception) {
            saving.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(saving);
            var commanEx = new CommandException("Saving Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return savingInfo;
    }

    public TatToanInfo tatToan(TatToanRequest tatToanRequest){
        User user = userRepo.findById(tatToanRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(tatToanRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Saving saving = (Saving) commandRepo.findById(tatToanRequest.getSaving_id()).orElseThrow(() -> new NotFoundException("No data!"));

        account.setBalance(account.getBalance() + saving.getAmount());
        saving.setStatusSaving(StatusSaving.TAT_TOAN.label);

        accountRepo.save(account);
        commandRepo.save(saving);

        TatToanInfo tatToanInfo = new TatToanInfo("Tất toán thành công",account.getId(),account.getBalance());
        return tatToanInfo;
    }

    public SuccessTietKiem successTietKiem(HoanThanhTietKiemRequest hoanThanhTietKiemRequest){
        Saving saving = (Saving) commandRepo.findById(hoanThanhTietKiemRequest.getSaving_id()).orElseThrow(() -> new NotFoundException("No data!"));

        Account accountRecv = accountRepo.findById(saving.getAccountRecv().getId()).orElseThrow(() -> new NotFoundException("No data!"));
        accountRecv.setBalance((long) (accountRecv.getBalance()+saving.getAmount()+saving.getLaiSuat()));
        saving.setStatusSaving(StatusSaving.HOAN_THANH.label);

        accountRepo.save(accountRecv);
        commandRepo.save(saving);

        SuccessTietKiem successTietKiem = new SuccessTietKiem("Hoàn thành thành công",accountRecv.getId(),accountRecv.getBalance());
        return successTietKiem;
    }

    public SavingInfo savingTheoThang(SavingRequest savingRequest){
        User user = userRepo.findById(savingRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountSend = accountRepo.findById(savingRequest.getAccountSend_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountRecv = accountRepo.findById(savingRequest.getAccountRecv_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!accountSend.getUser().getId().equals(savingRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountSend.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if (!accountSend.getUser().getId().equals(savingRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(accountRecv.isStatus() == false){
            throw new CommandException("Tài khoản của bạn đã bị khoá");
        }
        if(savingRequest.getAmount() > accountSend.getBalance()){
            throw new TradeException("Số dư của bạn không đủ");
        }
        if(savingRequest.getAmount() <= 0){
            throw new TradeException("Số tiền tiết kiệm phải lớn hơn 0");
        }
        accountSend.setBalance(accountSend.getBalance()-savingRequest.getAmount());
        double laiSuat = tinhLaiSuotRutTheoThang(savingRequest.getKyHan(),savingRequest.getAmount());
        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeEnd = tinhNgayDaoHan(savingRequest.getKyHan(),timeStart);
        SavingInfo savingInfo;

        Saving saving = new Saving(user,savingRequest.getAmount(),accountSend,accountRecv,savingRequest.getKyHan().label.toString(),laiSuat,StatusSaving.DANG_GUI.label.toString(),timeStart,timeEnd);
        try {
            accountRepo.save(accountSend);
            saving.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(saving);
            savingInfo = new SavingInfo(user.getId(),accountSend.getId(),savingRequest.getKyHan().label,laiSuat,savingRequest.getAmount(),laiSuat+savingRequest.getAmount(),timeStart,timeEnd);
        } catch (Exception exception) {
            saving.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(saving);
            var commanEx = new CommandException("Saving Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return savingInfo;
    }
    public RutLaiTheoThang rutLaiTheoThang(RutLaiTheoThangRequest rutLaiTheoThangRequest){
        User user = userRepo.findById(rutLaiTheoThangRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(rutLaiTheoThangRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Saving saving = (Saving) commandRepo.findById(rutLaiTheoThangRequest.getSaving_id()).orElseThrow(() -> new NotFoundException("No data!"));
        double tienlai = 0;
        if(saving.getKyHan().equals("Một Tháng")){
            tienlai = saving.getLaiSuat()/1;
        } else if(saving.getKyHan().equals("Ba Tháng")){
            tienlai = saving.getLaiSuat()/3;
        } else if(saving.getKyHan().equals("Sáu Tháng")){
            tienlai = saving.getLaiSuat()/6;
        } else if(saving.getKyHan().equals("Mười Hai Tháng")){
            tienlai = saving.getLaiSuat()/12;
        }

        return  new RutLaiTheoThang(saving.getId(),tienlai,LocalDateTime.now());

    }

    public double tinhLaiSuot(KyHan kyHan, Long amount){
        double result = 0;
        if(kyHan.label.equals("Một Tháng")){
             result = 0.02 * amount;
        } else if(kyHan.label.equals("Ba Tháng")){
             result = 0.03 * amount;
        } else if (kyHan.label.equals("Sáu Tháng")) {
             result = 0.04 * amount;
        } else if (kyHan.label.equals("Mười Hai Tháng")) {
            result = 0.065 * amount;
        }
        return  result;
    }

    public double tinhLaiSuotRutTheoThang(KyHan kyHan, Long amount){
        double result = 0;
        if(kyHan.label.equals("Một Tháng")){
            result = 0.02 * amount * 0.8;
        } else if(kyHan.label.equals("Ba Tháng")){
            result = 0.03 * amount * 0.8;
        } else if (kyHan.label.equals("Sáu Tháng")) {
            result = 0.04 * amount * 0.8;
        } else if (kyHan.label.equals("Mười Hai Tháng")) {
            result = 0.065 * amount * 0.8;
        }
        return  result;
    }
    public  LocalDateTime tinhNgayDaoHan(KyHan kyHan , LocalDateTime timeStart){
        int value = 0;
        if(kyHan.label.equals("Một Tháng")){
            value = 30;
        } else if(kyHan.label.equals("Ba Tháng")){
            value = 90;
        } else if (kyHan.label.equals("Sáu Tháng")) {
            value = 30 * 3;
        } else if (kyHan.label.equals("Mười Hai Tháng")) {
            value = 365;
        }
        LocalDateTime timeEnd ;
        LocalDateTime ldt = timeStart;
        timeEnd = ldt.plus(Period.ofDays(value));
        return timeEnd;
    }
}
