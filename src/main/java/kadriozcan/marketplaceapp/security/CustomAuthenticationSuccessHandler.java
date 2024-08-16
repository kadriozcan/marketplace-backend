package kadriozcan.marketplaceapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kadriozcan.marketplaceapp.model.Role;
import kadriozcan.marketplaceapp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserAuthDetails userAuthDetails = (UserAuthDetails) authentication.getPrincipal();
        String userId = userAuthDetails.getUser().getId().toString();
        List<Role> userRoles = userAuthDetails.getUser().getRoles();
        List<String> roleNames = userRoles.stream().map(Role::getName).toList();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("userId", userId);
        responseData.put("roles", roleNames);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseData);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

    }
}
