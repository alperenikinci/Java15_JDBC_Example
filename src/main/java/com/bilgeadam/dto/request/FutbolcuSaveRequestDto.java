package com.bilgeadam.dto.request;

public class FutbolcuSaveRequestDto{

    private String isim;
    private String mevki;
    private String takimIsmi;
    private Long deger;
    private Integer formaNo;

    public FutbolcuSaveRequestDto(String isim, String mevki, String takimIsmi, Long deger, Integer formaNo) {
        this.isim = isim;
        this.mevki = mevki;
        this.takimIsmi = takimIsmi;
        this.deger = deger;
        this.formaNo = formaNo;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getMevki() {
        return mevki;
    }

    public void setMevki(String mevki) {
        this.mevki = mevki;
    }

    public String getTakimIsmi() {
        return takimIsmi;
    }

    public void setTakimIsmi(String takimIsmi) {
        this.takimIsmi = takimIsmi;
    }


    public Long getDeger() {
        return deger;
    }

    public void setDeger(Long deger) {
        this.deger = deger;
    }

    public Integer getFormaNo() {
        return formaNo;
    }

    public void setFormaNo(Integer formaNo) {
        this.formaNo = formaNo;
    }
}
