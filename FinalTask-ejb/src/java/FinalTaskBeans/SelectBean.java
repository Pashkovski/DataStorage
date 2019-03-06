/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTaskBeans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lesya
 */
@Stateless
public class SelectBean implements SelectBeanLocal {
    
    @EJB
    private DBManagerLocal dbManager;

    @Override
    public String selectAll() {
        return "All parameters: " + dbManager.selectAll();
    }

    @Override
    public String selectOne(String name) {
        return "Parameter " + name + dbManager.selectByName(name) ;
    }

    @Override
    public String selectByParam() {
        return "Selected in range";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
