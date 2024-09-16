package com.bilgeadam.service;

import com.bilgeadam.dto.request.FutbolcuSaveRequestDto;
import com.bilgeadam.dto.response.FutbolcuResponseDto;
import com.bilgeadam.entity.Futbolcu;
import com.bilgeadam.entity.Takim;
import com.bilgeadam.repository.FutbolcuRepository;

import java.util.List;
import java.util.Optional;

public class FutbolcuService  {

    private final FutbolcuRepository futbolcuRepository;
    private final TakimService takimService;

    public FutbolcuService() {
        this.futbolcuRepository = new FutbolcuRepository();
        this.takimService = new TakimService();
    }


    public Optional<FutbolcuResponseDto> save(FutbolcuSaveRequestDto dto) {
        Futbolcu futbolcu;
        Optional<Futbolcu> futbolcuOptional;
        FutbolcuResponseDto responseDto=  new FutbolcuResponseDto();

        try {
           Optional<Takim> takimOptional = takimService.findByName(dto.getTakimIsmi());
            if(takimOptional.isPresent()){
                futbolcu = new Futbolcu();
                futbolcu.setDeger(dto.getDeger());
                futbolcu.setIsim(dto.getIsim());
                futbolcu.setMevki(dto.getMevki());
                futbolcu.setFormaNo(futbolcu.getFormaNo());
                futbolcu.setTakimId(takimOptional.get().getId());
                futbolcuOptional =  futbolcuRepository.save(futbolcu);

                responseDto.setIsim(futbolcuOptional.get().getIsim());
                responseDto.setTakimAdi(takimService.findById(futbolcuOptional.get().getTakimId()).get().getIsim());
                responseDto.setFormaNo(futbolcuOptional.get().getFormaNo());
                responseDto.setMevki(futbolcuOptional.get().getMevki());
                System.out.println(futbolcuOptional.get().getIsim() + " başarıyla kaydedildi.");
                return Optional.of(responseDto);
            } else {
                System.out.println("Takım bulunamadı. Lutfen Takım id'sini kontrol edin.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.err.println("Service: Futbolcu kaydedilirken hata oluştu: " + e.getMessage());
        }
        return Optional.ofNullable(responseDto);
    }

    public Optional<Futbolcu> update(Futbolcu futbolcu) {
        if (findById(futbolcu.getId()).isPresent()) {
            try {
                futbolcuRepository.update(futbolcu);
                System.out.println(futbolcu.getIsim() + " başarıyla güncellendi.");
            } catch (Exception e) {
                System.err.println("Service: Futbolcu güncellenirken hata oluştu: " + e.getMessage());
                return Optional.empty();
            }
        } else {
            System.err.println("Service: Güncellenmek istenen futbolcu bulunamadı.");
            return Optional.empty();
        }
        return Optional.of(futbolcu);
    }


    public void delete(Long id) {
        if (findById(id).isPresent()) {
            try {
                futbolcuRepository.delete(id);
            } catch (Exception e) {
                System.err.println("Service: Futbolcu silinirken hata oluştu: " + e.getMessage());
            }
        } else {
            System.err.println("Service: Silinmek istenen futbolcu bulunamadı.");
        }
    }


    public List<Futbolcu> findAll() {
        return futbolcuRepository.findAll();
    }


    public Optional<Futbolcu> findById(Long id) {
        return futbolcuRepository.findById(id);
    }
}
