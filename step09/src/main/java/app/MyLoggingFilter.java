package app;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@Component
public class MyLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest rqs, ServletResponse rs, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) rqs;
        log.info("URI: {}", rq.getRequestURI());
        chain.doFilter(rqs, rs);
    }
}
