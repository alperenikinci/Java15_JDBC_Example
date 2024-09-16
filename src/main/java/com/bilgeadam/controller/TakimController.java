package com.bilgeadam.controller;

import com.bilgeadam.entity.Takim;
import com.bilgeadam.service.TakimService;

import java.util.List;
import java.util.Optional;

public class TakimController {

    private final TakimService takimService;

    public TakimController() {
        this.takimService = new TakimService();
    }

    public Optional<Takim> save(Takim takim) {
        try {
            takimService.save(takim);
            System.out.println("Controller Takım başarıyla kaydedildi.");
        } catch (Exception e) {
            System.out.println("Controller Takım kaydedilirken hata oluştu: " + e.getMessage());
        }
        return Optional.ofNullable(takim);
    }

    public Optional<Takim> update(Takim takim) {
        try {
            takimService.update(takim);
            System.out.println("Controller Takım başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Controller Takım güncellenirken hata oluştu: " + e.getMessage());
        }
        return Optional.ofNullable(takim);
    }

    public void delete(Long id) {
        try {
            takimService.delete(id);
            System.out.println("Controller Takım başarıyla silindi.");
        } catch (Exception e) {
            System.out.println("Controller Takım silinirken hata oluştu: " + e.getMessage());
        }
    }

    public List<Takim> findAll() {
        List<Takim> takimlar = takimService.findAll();
        if (takimlar.isEmpty()) {
            System.out.println("Controller Veritabanında kayıtlı takım bulunmamaktadır.");
        }
        return takimlar;
    }

    public Optional<Takim> findById(Long id) {
        Optional<Takim> takim = takimService.findById(id);
        takim.ifPresentOrElse(
            t -> System.out.println("Controller Takım bulundu: " + t.getIsim()),
            () -> System.out.println("Controller Böyle bir takım bulunamadı.")
        );
        return takim;
    }
}
