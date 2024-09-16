package com.bilgeadam.repository;

import com.bilgeadam.entity.Takim;
import com.bilgeadam.utility.ConnectionProvider;
import com.bilgeadam.utility.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TakimRepository implements ICrud<Takim> {
    private String sql = "";
    private final ConnectionProvider connectionProvider;

    public TakimRepository() {
        this.connectionProvider = ConnectionProvider.getInstance();
    }

    @Override
    public Optional<Takim> save(Takim takim) {
        sql = "INSERT INTO tbl_takim(isim, kurulus_yili) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
            ) {
            preparedStatement.setString(1, takim.getIsim());
            preparedStatement.setInt(2, takim.getKurulusYili());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(takim);
    }

    @Override
    public Optional<Takim> update(Takim takim) {
        sql = "UPDATE tbl_takim SET isim = ?, kurulus_yili = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, takim.getIsim());
            preparedStatement.setInt(2, takim.getKurulusYili());
            preparedStatement.setLong(3, takim.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Güncelleme Başarılı!");
            } else {
                System.out.println("Güncelleme Başarısız!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return Optional.ofNullable(takim);
    }

    @Override
    public void delete(Long id) {
        sql = "DELETE FROM tbl_takim WHERE id = ?";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Takim> findAll() {
        sql = "SELECT * FROM tbl_takim";
        List<Takim> takimlar = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String isim = resultSet.getString("isim");
                Integer kurulusYili = resultSet.getInt("kurulus_yili");
                Takim takim = new Takim(id, isim, kurulusYili);
                takimlar.add(takim);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return takimlar;
    }

    @Override
    public Optional<Takim> findById(Long id) {
        sql = "SELECT * FROM tbl_takim WHERE id = ?";
        Takim takim = null;
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String isim = resultSet.getString("isim");
                    Integer kurulusYili = resultSet.getInt("kurulus_yili");
                    takim = new Takim(id, isim, kurulusYili);
                    return Optional.of(takim);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<Takim> findByName(String takimIsmi) {

        sql = "SELECT * FROM tbl_takim WHERE isim = ?";
        Takim takim = null;
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, takimIsmi);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String isim = resultSet.getString("isim");
                    Integer kurulusYili = resultSet.getInt("kurulus_yili");
                    takim = new Takim(id, isim, kurulusYili);
                    return Optional.of(takim);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
