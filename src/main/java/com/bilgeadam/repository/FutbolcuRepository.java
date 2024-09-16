package com.bilgeadam.repository;

import com.bilgeadam.entity.Futbolcu;
import com.bilgeadam.utility.ConnectionProvider;
import com.bilgeadam.utility.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FutbolcuRepository implements ICrud<Futbolcu> {
    private String sql = "";
    private final ConnectionProvider connectionProvider;

    public FutbolcuRepository() {
        this.connectionProvider = ConnectionProvider.getInstance();
    }

    @Override
    public Optional<Futbolcu> save(Futbolcu futbolcu) {
        sql = "INSERT INTO tbl_futbolcu (isim, mevki, forma_no, deger, takim_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, futbolcu.getIsim());
            preparedStatement.setString(2, futbolcu.getMevki());
            preparedStatement.setInt(3, futbolcu.getFormaNo());
            preparedStatement.setLong(4, futbolcu.getDeger());
            preparedStatement.setLong(5, futbolcu.getTakimId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Repository: Futbolcu kaydedilirken hata oluştu: " + e.getMessage());
        }

        return Optional.ofNullable(futbolcu);
    }

    @Override
    public Optional<Futbolcu> update(Futbolcu futbolcu) {
        sql = "UPDATE tbl_futbolcu SET isim = ?, mevki = ?, forma_no = ?, deger = ?, takim_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setString(1, futbolcu.getIsim());
            preparedStatement.setString(2, futbolcu.getMevki());
            preparedStatement.setInt(3, futbolcu.getFormaNo());
            preparedStatement.setLong(4, futbolcu.getDeger());
            preparedStatement.setLong(5, futbolcu.getTakimId());
            preparedStatement.setLong(6, futbolcu.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                System.err.println("Repository: Futbolcu güncellenirken hata oluştu: Güncelleme başarısız.");
            }
        } catch (SQLException e) {
            System.err.println("Repository: Futbolcu güncellenirken hata oluştu: " + e.getMessage());
        }
        return Optional.ofNullable(futbolcu);
    }

    @Override
    public void delete(Long id) {
        sql = "DELETE FROM tbl_futbolcu WHERE id = ?";
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Repository: Futbolcu silinirken hata oluştu: " + e.getMessage());
        }
    }

    @Override
    public List<Futbolcu> findAll() {
        sql = "SELECT * FROM tbl_futbolcu";
        List<Futbolcu> futbolcular = new ArrayList<>();
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String isim = resultSet.getString("isim");
                String mevki = resultSet.getString("mevki");
                int formaNo = resultSet.getInt("forma_no");
                Long deger = resultSet.getLong("deger");
                Long takimId = resultSet.getLong("takim_id");
                Futbolcu futbolcu = new Futbolcu(id, isim, mevki, formaNo, deger, takimId);
                futbolcular.add(futbolcu);
            }
        } catch (SQLException e) {
            System.err.println("Repository: Futbolcu verileri alınırken hata oluştu: " + e.getMessage());
        }
        return futbolcular;
    }

    @Override
    public Optional<Futbolcu> findById(Long id) {
        sql = "SELECT * FROM tbl_futbolcu WHERE id = ?";
        Futbolcu futbolcu = null;
        try (PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String isim = resultSet.getString("isim");
                    String mevki = resultSet.getString("mevki");
                    int formaNo = resultSet.getInt("forma_no");
                    Long deger = resultSet.getLong("deger");
                    Long takimId = resultSet.getLong("takim_id");
                    futbolcu = new Futbolcu(id, isim, mevki, formaNo, deger, takimId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Repository: Futbolcu bulunurken hata oluştu: " + e.getMessage());
        }
        return Optional.ofNullable(futbolcu);
    }
}
