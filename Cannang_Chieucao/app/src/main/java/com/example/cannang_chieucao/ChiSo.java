package com.example.cannang_chieucao;

public class ChiSo {
    public int id;
    public String hoTen;
    public float chieucao;
    public float cannang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChiSo(int id, String hoTen, float chieucao, float cannang) {
        this.getClass();
        this.hoTen = hoTen;
        this.chieucao = chieucao;
        this.cannang = cannang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public float getChieucao() {
        return chieucao;
    }

    public void setChieucao(float chieucao) {
        this.chieucao = chieucao;
    }

    public float getCannang() {
        return cannang;
    }

    public void setCannang(float cannang) {
        this.cannang = cannang;
    }
}
