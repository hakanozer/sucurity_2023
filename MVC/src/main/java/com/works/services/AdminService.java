package com.works.services;

import com.works.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
@RequiredArgsConstructor
public class AdminService {

    final DB db;
    final HttpServletRequest req;
    final TinkEncDec tinkEncDec;

    public boolean adminLogin(Admin admin) {
        boolean status = false;
        try {
            String sql = "select * from admin where email = ?";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, admin.getEmail());
            ResultSet rs = st.executeQuery();
            status = rs.next();
            if (status) {
                String cipherText = rs.getString("password");
                String plainPass = tinkEncDec.decrypt(cipherText);
                if ( plainPass.equals(admin.getPassword()) ) {
                    admin.setUid( rs.getLong("uid") );
                    req.getSession().setAttribute("admin", admin);
                }else {
                    status = false;
                }
            }
        }catch (Exception ex) {
            System.err.println("Login Error: " + ex);
        }
        return status;
    }

    public void logout() {
        req.getSession().removeAttribute("admin");
    }

}
