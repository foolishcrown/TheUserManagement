/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.dtos;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sangnv.daos.PromotionDAO;

/**
 *
 * @author Shang
 */
public class ProListDTO implements Serializable {

    private List<PromotionDTO> proList;

    public ProListDTO() {
        this.proList = new ArrayList<>();
    }

    public List<PromotionDTO> getProList() {
        return proList;
    }
    
    

    public void removeList(PromotionDTO dto) {
        if (this.proList.contains(dto)) {
            this.proList.remove(dto);
        }
    }

    public void updateList(PromotionDTO dto) {
        if (this.proList.contains(dto)) {
            int index = this.proList.indexOf(dto);
            this.proList.get(index).setRank(dto.getRank());
        }
    }
    
    public boolean addToList(PromotionDTO dto){
        boolean check = false;
        if (!this.proList.contains(dto)) {
            this.proList.add(dto);
            check = true;
        }
        return check;
    }

}
