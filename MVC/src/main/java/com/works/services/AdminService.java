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

    public boolean adminLogin(Admin admin) {
        boolean status = false;
        try {
            String sql = "select * from admin where email = ? and password = ?";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, admin.getEmail());
            st.setString(2, admin.getPassword());
            ResultSet rs = st.executeQuery();
            status = rs.next();
            if (status) {
                admin.setUid( rs.getLong("uid") );
                req.getSession().setAttribute("admin", admin);
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
