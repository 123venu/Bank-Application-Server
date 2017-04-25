package com.bank.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "EmployeeService")
public class EmployeeService {

    /**
     * Web service operation to get login
     */
    @WebMethod(operationName = "getLogin")
    public Boolean getLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws UsernamePasswordException {
        boolean isValid = false;
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        ResultSet rs = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "select id, emp_name, position from employeedetails where username = ? and password = ?";
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();
            if (rs.next()) {
                isValid = true;
                st1 = con.prepareStatement("update employeedetails set is_logon = 1  where id = ?");
                st1.setInt(1, rs.getInt("id"));
                st1.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (st1 != null) {
                    st1.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        if (!isValid) {
            throw new UsernamePasswordException();
        } else {
            return true;
        }

    }

    /**
     * Web service operation to get logout
     */
    @WebMethod(operationName = "getLogOut")
    public Boolean getLogOut(@WebParam(name = "username") String username) {
        boolean isValid = false;
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        ResultSet rs = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "select id, emp_name, position from employeedetails where username = ? and is_logon = 1";
            st = con.prepareStatement(sql);
            st.setString(1, username);
            rs = st.executeQuery();
            if (rs.next()) {
                isValid = true;
                //debug
                System.out.println("Server : " + isValid + " " + con.toString());

                st1 = con.prepareStatement("update employeedetails set is_logon = 0  where id = ?");
                st1.setInt(1, rs.getInt("id"));
                st1.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (st1 != null) {
                    st1.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        return isValid;
    }

    /**
     * Web service operation to get the employee list
     */
    @WebMethod(operationName = "getEmployeeList")
    public ArrayList<Employee> getEmployeeList() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "select * from employeedetails";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("id"));
                emp.setEmployeeName(rs.getString("emp_name"));
                emp.setPosition(rs.getString("position"));
                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                list.add(emp);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        return list;
    }

    /**
     * Web service operation to create employee
     */
    @WebMethod(operationName = "createEmployee")
    public Boolean createEmployee(@WebParam(name = "employee") Employee employee) {
        Connection con = null;
        PreparedStatement st = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "insert into employeedetails (emp_name, position, username, password) values (?, ?, ?, ?)";
            st = con.prepareStatement(sql);
            st.setString(1, employee.getEmployeeName());
            st.setString(2, employee.getPosition());
            st.setString(3, employee.getUsername());
            st.setString(4, employee.getPassword());
            st.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        return isSuccess;
    }

    /**
     * Web service operation to update existing employee
     */
    @WebMethod(operationName = "editEmployee")
    public Boolean editEmployee(@WebParam(name = "employee") Employee employee, @WebParam(name = "delId") String delId) {
        Connection con = null;
        PreparedStatement st = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "update employeedetails set emp_name = ?, position = ?, username =?, password= ? where username = ?";
            st = con.prepareStatement(sql);
            st.setString(1, employee.getEmployeeName());
            st.setString(2, employee.getPosition());
            st.setString(3, employee.getUsername());
            st.setString(4, employee.getPassword());
            st.setString(5, delId);
            st.executeUpdate();
            isSuccess = true;
            System.out.println("dellID: " + delId + " " + isSuccess);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        return isSuccess;
    }

    /**
     * Web service operation to delete existing employee
     */
    @WebMethod(operationName = "deleteEmployee")
    public Boolean deleteEmployee(@WebParam(name = "employee") Employee employee) {
        Connection con = null;
        PreparedStatement st = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "delete from employeedetails where username = ?";
            st = con.prepareStatement(sql);
            st.setString(1, employee.getUsername());
            st.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
        return isSuccess;
    }

}
