package com.bilgeadam.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Veritabanı şemasını oluşturmak için kullanılan sınıftır.
 * Bu sınıf, gerekli tabloları veritabanında oluşturur.
 */
public class DatabaseSchema {

    /**
     * Tabloları oluşturur.
     */
    public static void createTables() {
        // "takim" tablosu için SQL sorgusu
        String createTakimTable = "CREATE TABLE IF NOT EXISTS tbl_takim (" +
                "id BIGSERIAL PRIMARY KEY, " + // PostgreSQL'de BIGSERIAL kullanılır
                "isim VARCHAR(255) NOT NULL, " +
                "kurulus_yili INT NOT NULL" +
                ");";

        // "futbolcu" tablosu için SQL sorgusu
        String createFutbolcuTable = "CREATE TABLE IF NOT EXISTS tbl_futbolcu (" +
                "id BIGSERIAL PRIMARY KEY, " + // PostgreSQL'de BIGSERIAL kullanılır
                "isim VARCHAR(255) NOT NULL, " +
                "mevki VARCHAR(255) NOT NULL, " +
                "forma_no INT NOT NULL, " +
                "deger BIGINT, " +
                "takim_id BIGINT, " +
                "FOREIGN KEY (takim_id) REFERENCES tbl_takim(id) ON DELETE SET NULL" + // ON DELETE SET NULL, PostgreSQL'de referanslı kayıt silindiğinde ilişkili alanı NULL yapar.
                ");";

        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            Connection connection = connectionProvider.getConnection(); // getConnection() metodu eklenmiş
            Statement statement = connection.createStatement();
            // Tablo oluşturma sorgularını çalıştırıyoruz
            statement.execute(createTakimTable);
            statement.execute(createFutbolcuTable);
            System.out.println("Tables created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
