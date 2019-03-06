/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTaskBeans;

import javax.ejb.Local;

/**
 *
 * @author lesya
 */
@Local
public interface DBManagerLocal {
    String selectAll();
    String selectByName(String name);
    boolean checkUser(String user);
    void addUser(String user);
    int maxUserId();
}
