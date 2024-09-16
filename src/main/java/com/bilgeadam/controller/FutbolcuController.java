package com.bilgeadam.controller;

import com.bilgeadam.dto.request.FutbolcuSaveRequestDto;
import com.bilgeadam.dto.response.FutbolcuResponseDto;
import com.bilgeadam.entity.Futbolcu;
import com.bilgeadam.repository.FutbolcuRepository;
import com.bilgeadam.service.FutbolcuService;

import java.util.List;
import java.util.Optional;

public class FutbolcuController {

    private final FutbolcuService futbolcuService;

    public FutbolcuController() {
        this.futbolcuService = new FutbolcuService();
    }


    public Optional<FutbolcuResponseDto> save(FutbolcuSaveRequestDto dto) {
        try {
            return futbolcuService.save(dto);
        } catch (Exception e) {
            System.err.println("Controller: Futbolcu kaydedilirken hata oluştu: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Futbolcu> update(Futbolcu futbolcu) {
        try {
            return futbolcuService.update(futbolcu);
        } catch (Exception e) {
            System.err.println("Controller: Futbolcu güncellenirken hata oluştu: " + e.getMessage());
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        try {
            futbolcuService.delete(id);
        } catch (Exception e) {
            System.err.println("Controller: Futbolcu silinirken hata oluştu: " + e.getMessage());
        }
    }

    public List<Futbolcu> findAll() {
        return futbolcuService.findAll();
    }

    public Optional<Futbolcu> findById(Long id) {
        return futbolcuService.findById(id);
    }
}
