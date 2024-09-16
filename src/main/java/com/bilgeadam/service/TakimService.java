package com.bilgeadam.service;

import com.bilgeadam.entity.Takim;
import com.bilgeadam.repository.TakimRepository;


import java.util.List;
import java.util.Optional;

public class TakimService{

    private final TakimRepository takimRepository;

    public TakimService() {
        this.takimRepository = new TakimRepository();
    }


    public Optional<Takim> save(Takim takim) {
        try {
            takimRepository.save(takim);
            System.out.println(takim.getIsim() + " başarıyla kaydedildi.");
        } catch (Exception e) {
            System.out.println("Service Takım kaydedilirken hata oluştu: " + e.getMessage());
        }

        return Optional.ofNullable(takim);
    }


    public Optional<Takim> update(Takim takim) {
        Optional<Takim> mevcutTakim = findById(takim.getId());
        if (mevcutTakim.isPresent()) {
            try {
                takimRepository.update(takim);
                System.out.println(takim.getIsim() + " başarıyla güncellendi.");
            } catch (Exception e) {
                System.out.println("Service Takım güncellenirken hata oluştu: " + e.getMessage());
            }
        } else {
            System.out.println("Service Güncellenmek istenen takım bulunamadı.");
        }
        return Optional.of(takim);
    }


    public void delete(Long id) {
        Optional<Takim> mevcutTakim = findById(id);
        if (mevcutTakim.isPresent()) {
            try {
                takimRepository.delete(id);
                System.out.println("Service Takım başarıyla silindi.");
            } catch (Exception e) {
                System.out.println("Service Takım silinirken hata oluştu: " + e.getMessage());
            }
        } else {
            System.out.println("Service Silinmek istenen takım bulunamadı.");
        }
    }


    public List<Takim> findAll() {
        List<Takim> takimlar = takimRepository.findAll();
        if (takimlar.isEmpty()) {
            System.out.println("Service Veritabanında kayıtlı takım bulunmamaktadır.");
        }
        return takimlar;
    }


    public Optional<Takim> findById(Long id) {
        Optional<Takim> takim = takimRepository.findById(id);
        takim.ifPresentOrElse(
            t -> System.out.println("Service Takım bulundu: " + t.getIsim()),
            () -> System.out.println("Service Böyle bir takım bulunamadı.")
        );
        return takim;
    }

    public Optional<Takim> findByName(String takimIsmi) {
        return takimRepository.findByName(takimIsmi);
    }


}
