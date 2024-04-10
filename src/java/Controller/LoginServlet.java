/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataAccessLayer.*;
import Model.CredentialsDTO;

import javax.servlet.http.HttpSession;

/**
 * This servlet handles user login functionality. It processes POST requests, validates user credentials, and 
 * redirects users based on their role.
 * 
 * Created April 3, 2024 3:51pm
 * @author Vaishali 
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
  
  public LoginServlet(){
     
      DBConnection.getInstance().getConnection();
  }

    /**
     * Handles the HTTP POST method. It processes login requests by validating the credentials provided by the user.
     * If the credentials are valid, it redirects the user to the appropriate page based on their role.
     * If the credentials are invalid, it redirects back to the login page with an error message.
     *
     * @param request  The HttpServletRequest object that contains the request the client made of the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs during the processing of the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // This should be hashed and compared.
        
        // Basic validation
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("loginError", "Email and password cannot be empty.");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            return;
        }

        // Additional email format validation
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("loginError", "Invalid email format.");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            return;
        }
            
        UserDAO userDAO = new UserDAO();
        CredentialsDTO user = userDAO.authenticateUser(email, password);
System.out.println("Setting userId in session: " + user.getUserId());
        if (user != null) {
        System.out.println("Login successful for user: " + email); // Simple logging
        
  System.out.println("Setting userId in session: " + user.getUserId());
//        session.setAttribute("user_id", user.getUserId()); // Use the user ID from the authenticated user object     
//        session.setAttribute("userType", user.getUserType()); // Store user type in session, if your User object has this field   
//        session.setAttribute("users", user); // Store the entire user object in the session, if you want to use it later
//        
        // Redirect based on user type
        HttpSession session;
            if ("retailer".equalsIgnoreCase(user.getUserType())) {
                session = request.getSession();
                session.setAttribute("user_id", user.getUserId());
                response.sendRedirect("/OOPFinalProject_FWRP/InventoryManagementServlet"); // Redirect to the retailer's dashboard
            } else if ("consumer".equalsIgnoreCase(user.getUserType())) {
                session = request.getSession();
                session.setAttribute("user_id", user.getUserId());  // Assuming 'getUserId()' correctly retrieves the user's ID
                response.sendRedirect("/OOPFinalProject_FWRP/ConsumerItemsServlet");
            }else if ("charitable_org.".equalsIgnoreCase(user.getUserType())) {
                session = request.getSession();
                session.setAttribute("user_id", user.getUserId());  // Assuming 'getUserId()' correctly retrieves the user's ID
                response.sendRedirect("/OOPFinalProject_FWRP/CharitableOrgItemsServlet");
            } else {
                // Handle other user types or default action
                response.sendRedirect("Views/index.jsp"); // Redirect to a default page or error page
            }
        
        
    } else {
        System.out.println("Login failed for user: " + email); // Simple logging
        request.setAttribute("loginError", "Invalid email or password.");
        request.getRequestDispatcher("Views/login.jsp").forward(request, response);
        }        
    } 
    
}
