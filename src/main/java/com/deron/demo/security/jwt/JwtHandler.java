package com.deron.demo.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class JwtHandler {
    public static String SECRET_KEY = "SECRET_KEY";
    public static String EXTRA_SIGNATURE = "284a7215bd337000fb86221a2b83cabb";
    public static class onFail implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        }
    }
    public static class onSuccess implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        }
    }

    public static class JwtMatchers implements RequestMatcher {
        private List<String> guards;
        private List<String>matchersGuards;
        private boolean isAll;
        private Pattern double_side = Pattern.compile("\\*\\*/(.*)/\\*\\*");
        private Pattern left_side = Pattern.compile("(.*)/\\*\\*");
        private Pattern right_side = Pattern.compile("\\*\\*/(.*)");
        public JwtMatchers(String...urls) {
            this.guards = Arrays.asList(urls);
            isAll = ( this.guards.indexOf("/**") != -1 );
            if( !isAll ){
                matchersGuards = new ArrayList<>();
                for(String url: guards){
                    String mgurl = "";
                    if( double_side.matcher(url).matches() ){
                        String mg = String.join("", url.split("/\\*\\*") );
                        mg = String.join("", mg.split("\\*\\*/") );
                        mgurl = mg;
                        if( mgurl.charAt(0) !='/' ) mgurl = '/' + mgurl;
                        if( mgurl.charAt( mgurl.length() - 1) != '/' ) mgurl += '/';
                    }
                    else if( right_side.matcher(url).matches() ){
                        mgurl =  String.join("", url.split("\\*\\*/") ) ;
                        if( mgurl.charAt(0) !='/' ) mgurl = '/' + mgurl;
                        if( mgurl.charAt( mgurl.length() - 1) != '/' ) mgurl += '/';
                    }else if( left_side.matcher(url).matches() ){
                        mgurl = String.join("", url.split("/\\*\\*") );
                        if( mgurl.charAt(0) !='/' ) mgurl = '/' + mgurl;
                        if( mgurl.charAt( mgurl.length() - 1) != '/' ) mgurl += '/';
                    }
                    if (!mgurl.isEmpty())
                        if( matchersGuards.indexOf(mgurl) == -1 )
                            matchersGuards.add(mgurl);
                }
            }
        }

        private boolean matchGlobal(String url){
            if( url.charAt( url.length() -1 ) != '/' ) url += '/';
            for(String guard: matchersGuards ){
                if ( url.indexOf( guard ) != -1 )return true;
            }
            return false;
        }
        private boolean canNotPass(HttpServletRequest request){
            if( request.getHeader("signature") == null )return true;
            if( request.getHeader("signature").equals(EXTRA_SIGNATURE) ) return false;
            return true;
        }
        @Override
        public boolean matches(HttpServletRequest request) {
            if( canNotPass(request) ) return true;
            if( guards == null )return false;
            else if ( isAll ) return true;
            else if ( matchGlobal(request.getRequestURI() ) ) return true;
            return ( guards.indexOf( request.getRequestURI() ) != -1 );
        }
    }
}

