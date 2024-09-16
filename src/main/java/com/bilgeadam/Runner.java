package com.bilgeadam;

import com.bilgeadam.controller.FutbolcuController;
import com.bilgeadam.controller.TakimController;
import com.bilgeadam.dto.request.FutbolcuSaveRequestDto;
import com.bilgeadam.dto.response.FutbolcuResponseDto;
import com.bilgeadam.entity.Futbolcu;
import com.bilgeadam.entity.Takim;
import com.bilgeadam.utility.ConnectionProvider;
import com.bilgeadam.utility.DatabaseConnection;
import com.bilgeadam.utility.DatabaseSchema;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Runner {

    public static void main(String[] args) throws SQLException {
        try(ConnectionProvider connectionProvider = ConnectionProvider.getInstance()) {
//            // Veritabanı şemasını oluştur
//            DatabaseSchema.createTables();
//
//            // Kontrolcüler
//            TakimController takimController = new TakimController();
//            FutbolcuController futbolcuController = new FutbolcuController();
//
//            // Takım oluştur
//            Takim takim1 = new Takim("Fenerbahce", 1907);
//            Takim takim2 = new Takim("Galatasaray", 1905);
//            takimController.save(takim1);
//            takimController.save(takim2);
//
//            // Takım id'lerini al
//            Long takimId1 = takimController.findAll().get(0).getId();
//            Long takimId2 = takimController.findAll().get(1).getId();
//
//            // Futbolcu oluştur ve ekle
//            for (int i = 1; i <= 5; i++) {
//                FutbolcuSaveRequestDto futbolcu1 = new FutbolcuSaveRequestDto("George Hagi" + i, "Orta Saha", "Galatasaray", 1000000L + (i * 10000L),10);
//                FutbolcuSaveRequestDto futbolcu2 = new FutbolcuSaveRequestDto("Alex De Souza " + i, "Orta Saha", "Fenerbahce", 2000000L + (i * 20000L), 10);
//
//                futbolcuController.save(futbolcu1);
//                futbolcuController.save(futbolcu2);
//            }
//
//            // CRUD işlemlerini test et
//
//            // Takımları listele
//            System.out.println("Takımlar:");
//            takimController.findAll().forEach(System.out::println);
//
//            // Futbolcuları listele
//            System.out.println("Futbolcular:");
//            futbolcuController.findAll().forEach(System.out::println);
//
//            // Güncelleme işlemi: Bir futbolcunun bilgilerini güncelle
//            Futbolcu futbolcuToUpdate = futbolcuController.findAll().get(0);
//            futbolcuToUpdate.setMevki("Defans");
//            futbolcuController.update(futbolcuToUpdate);
//
//            System.out.println("Güncellenmiş Futbolcu:");
//            futbolcuController.findById(futbolcuToUpdate.getId()).ifPresent(System.out::println);
//
//            // Silme işlemi: Bir futbolcuyu sil
//            Futbolcu futbolcuToDelete = futbolcuController.findAll().get(1);
//            futbolcuController.delete(futbolcuToDelete.getId());
//
//            System.out.println("Futbolcular (Silme sonrası):");
//            futbolcuController.findAll().forEach(System.out::println);
//
//            // Silme işlemi: Bir takımı sil
//            Takim takimToDelete = takimController.findAll().get(0);
//            takimController.delete(takimToDelete.getId());
//
//            System.out.println("Takımlar (Silme sonrası):");
//            takimController.findAll().forEach(System.out::println);

        FutbolcuController futbolcuController = new FutbolcuController();

       Optional<FutbolcuResponseDto> dto =  futbolcuController.save(new FutbolcuSaveRequestDto("Alperen","Ortasaha","Galatasaray",50000L,20));
        System.out.println(dto);
/*
    private String mevki;
    private String takimIsmi;
    private Long deger;
    private Integer formaNo;

 */
        }

//        ConnectionProvider.getInstance().close();
        Connection connection = ConnectionProvider.getInstance().getConnection();
        boolean isClosed = connection.isClosed();
        System.out.println("Connection closed: " + isClosed);
    }
}
