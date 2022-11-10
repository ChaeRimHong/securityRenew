package com.example.securityRenew.persistence.mapper;


import com.example.securityRenew.persistence.model.Board;

import java.util.List;

public interface ServiceMapper {
    List<Board> search(String keyword);
    /*
    public void in(String pname, String pimg, String pd, String pft, int price);

    public ArrayList<Market> out();

    public void del(String pname);

    public Market mod_select(String pname);

    public void mod_save(String pname, String pimg, String pd, String pft, int price);

    public Market detail(String pname);
    */

}
