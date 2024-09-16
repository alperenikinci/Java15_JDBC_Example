package com.bilgeadam.entity;

public class Takim {

    private Long id;
    private String isim;
    private Integer kurulusYili;

    public Takim() {
    }

    public Takim(String isim, Integer kurulusYili) {
        this.isim = isim;
        this.kurulusYili = kurulusYili;
    }

    public Takim(Long id, String isim, Integer kurulusYili) {
        this.id = id;
        this.isim = isim;
        this.kurulusYili = kurulusYili;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Integer getKurulusYili() {
        return kurulusYili;
    }

    public void setKurulusYili(Integer kurulusYili) {
        this.kurulusYili = kurulusYili;
    }

    @Override
    public String toString() {
        return "Takim{" +
                "id=" + id +
                ", isim='" + isim + '\'' +
                ", kurulusYili=" + kurulusYili +
                '}';
    }
}
