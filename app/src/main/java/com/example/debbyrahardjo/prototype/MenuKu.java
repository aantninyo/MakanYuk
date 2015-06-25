package com.example.debbyrahardjo.prototype;

/**
 * Created by Khaerul on 6/23/2015.
 */
public class MenuKu {

    private int id;
    private int id_restoran;
    private int id_jenis;
    private String nama;
    private String dekripsi;
    private String harga;
    private String images;
    private String create_at;
    private String update_at;
    private int published;



    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_restoran() {
        return id_restoran;
    }

    public void setId_restoran(int id_restoran) {
        this.id_restoran = id_restoran;
    }

    public int getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(int id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDekripsi() {
        return dekripsi;
    }

    public void setDekripsi(String dekripsi) {
        this.dekripsi = dekripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }


}
