package com.bank.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "CustomerService")
public class CustomerService {

    /**
     * Web service operation to get customer list
     */
    @WebMethod(operationName = "getCustomerList")
    public ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "select c.id, c.cus_name, c.dob,c.address, c.mobile, c.email, a.acc_type,"
                    + " a.acc_number, a.sort_code, a.balance, a.card_number"
                    + " from customerdetails c"
                    + " inner join accountdetails a on a.cus_id = c.id";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Customer cus = new Customer();
                cus.setCustomerId(rs.getInt("id"));
                cus.setCustomerName(rs.getString("cus_name"));
                cus.setDob(rs.getString("dob"));
                cus.setAddress(rs.getString("address"));
                cus.setMobile(rs.getString("mobile"));
                cus.setEmail(rs.getString("email"));
                Account acc = new Account();
                acc.setAccountType(rs.getString("acc_type"));
                acc.setAccountNo(rs.getString("acc_number"));
                acc.setSortCode(rs.getString("sort_code"));
                acc.setAccountBalance(rs.getDouble("balance"));
                acc.setCardNumber(rs.getString("card_number"));
                //acc.setCustomer(cus);

                ArrayList<Account> account = new ArrayList<>();
                account.add(acc);
                cus.setAccounts(account);
                list.add(cus);
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
     * Web service operation to create customer
     */
    @WebMethod(operationName = "createCustomer")
    public Boolean createCustomer(@WebParam(name = "customer") Customer customer, @WebParam(name = "account") Account account) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();
            String sql = "insert into customerdetails (cus_name, dob, address, mobile, email) values(?, ?, ?, ?, ?)";
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, customer.getCustomerName());
            st.setString(2, customer.getDob());
            st.setString(3, customer.getAddress());
            st.setString(4, customer.getMobile());
            st.setString(5, customer.getEmail());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                int customerId = rs.getInt(1);
                String sql1 = "insert into accountdetails (acc_type, acc_number, sort_code, balance, card_number, cus_id) "
                        + "values (?, ?, ?, ?, ?, ?)";
                st = con.prepareStatement(sql1);
                Account acc = account;
                st.setString(1, acc.getAccountType());
                st.setString(2, acc.getAccountNo());
                st.setString(3, acc.getSortCode());
                st.setDouble(4, acc.getAccountBalance());
                st.setString(5, acc.getCardNumber());
                st.setInt(6, customerId);
                st.executeUpdate();
            }
            isSuccess = true;
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
        return isSuccess;
    }

    /**
     * Web service operation to edit customer
     */
    @WebMethod(operationName = "editCustomer")
    public Boolean editCustomer(@WebParam(name = "customer") Customer customer, @WebParam(name = "account") Account account) throws InvalidDataException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();

            String sql1 = "update accountdetails set acc_type = ?, acc_number = ?, sort_code = ?, balance = ?, card_number = ? where id = ?";
            st = con.prepareStatement(sql1);
            Account acc = account;
            st.setString(1, acc.getAccountType());
            st.setString(2, acc.getAccountNo());
            st.setString(3, acc.getSortCode());
            st.setDouble(4, acc.getAccountBalance());
            st.setString(5, acc.getCardNumber());
            st.setInt(6, customer.getCustomerId());
            System.out.println(st.toString());
            int x = st.executeUpdate();

            String sql = "update customerdetails set cus_name = ?, dob = ?, address = ?, mobile = ?, email= ?  where id = ?";
            st = con.prepareStatement(sql);
            st.setString(1, customer.getCustomerName());
            st.setString(2, customer.getDob());
            st.setString(3, customer.getAddress());
            st.setString(4, customer.getMobile());
            st.setString(5, customer.getEmail());
            st.setInt(6, customer.getCustomerId());
            int x1 = st.executeUpdate();

            if (x >= 0 && x1 >= 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
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
        if (!isSuccess) {
            throw new InvalidDataException();
        } else {
            return isSuccess;
        }
    }

    /**
     * Web service operation to delete customer
     */
    @WebMethod(operationName = "deleteCustomer")
    public Boolean deleteCustomer(@WebParam(name = "email") String email, @WebParam(name = "accNum") String accNum) {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        boolean isSuccess = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            con = db.getDBConnection();

            String sql1 = "delete from accountdetails where acc_number = ?";
            st = con.prepareStatement(sql1);
            st.setString(1, accNum);
            st.executeUpdate();

            String sql = "delete from customerdetails where email = ?";
            st = con.prepareStatement(sql);
            st.setString(1, email);
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
