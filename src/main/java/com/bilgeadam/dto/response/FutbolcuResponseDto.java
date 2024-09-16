package com.bilgeadam.dto.response;

public class FutbolcuResponseDto {

    private String isim;
    private String mevki;
    private int formaNo;
    private String takimAdi;

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

    public int getFormaNo() {
        return formaNo;
    }

    public void setFormaNo(int formaNo) {
        this.formaNo = formaNo;
    }

    public String getTakimAdi() {
        return takimAdi;
    }

    public void setTakimAdi(String takimAdi) {
        this.takimAdi = takimAdi;
    }

    @Override
    public String toString() {
        return "FutbolcuResponseDto{" +
                "isim='" + isim + '\'' +
                ", mevki='" + mevki + '\'' +
                ", formaNo=" + formaNo +
                ", takimAdi='" + takimAdi + '\'' +
                '}';
    }
}
