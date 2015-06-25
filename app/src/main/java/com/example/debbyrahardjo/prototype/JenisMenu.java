package com.example.debbyrahardjo.prototype;

/**
 * Created by Khaerul on 6/23/2015.
 */
public class JenisMenu {
    private int id;
    private String nama;
    private String create_at;
    private String update_at;
    private String published;

    public JenisMenu(){

    }

    public int getId(){
        return  id;
    }

    public String getNama(){
        return  nama;
    }

    public String getCreate_at(){
        return  create_at;
    }

    public String getUpdate_at(){
        return  update_at;
    }

    public String getPublished(){
        return  published;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setCreate_at(String create_at){
        this.create_at = create_at;
    }
    public void setUpdate_at(String update_at){
        this.update_at = update_at;
    }
    public void setPublished(String published){
        this.published = published;
    }


}
