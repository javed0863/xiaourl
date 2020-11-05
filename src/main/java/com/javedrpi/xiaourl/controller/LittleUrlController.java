package com.javedrpi.xiaourl.controller;

import com.javedrpi.xiaourl.exception.InvalidAuthTokenException;
import com.javedrpi.xiaourl.exception.UserNotSubscribedException;
import com.javedrpi.xiaourl.model.ShortUrlDto;
import com.javedrpi.xiaourl.model.Urls;
import com.javedrpi.xiaourl.model.UrlsDto;
import com.javedrpi.xiaourl.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static com.javedrpi.xiaourl.utils.MyLogger.getMessage;

@RestController
public class LittleUrlController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlService urlService;

    @Value("${xiao.url.base}")
    private String BASE_XIAOURL;

    @GetMapping("/xiao")
    public ResponseEntity<UrlsDto> xiaoUrl(@RequestParam("url") final String originalUrl,
                                           HttpServletRequest request){
        if(originalUrl.length() > 100){
            throw new UserNotSubscribedException("Please subscribe to access unlimited features",
                    request.getSession().getId());
        }

        return new ResponseEntity<>(
                urlService.getUrlsDtoResponse(originalUrl, request),
                HttpStatus.OK
        );
    }

    @PostMapping("/shorty")
    public ResponseEntity<UrlsDto> shorty(@RequestHeader("client_id") final String clientId,
                                          @RequestHeader("api_token") final String apiToken,
                                          @RequestBody final ShortUrlDto shortUrlDto,
                                           HttpServletRequest request){
        if(!urlService.isValidClient(clientId, apiToken))
            throw new InvalidAuthTokenException("Invalid client_id or api_token",
                    request.getSession().getId());

        return new ResponseEntity<>(
                urlService.getUrlsDtoResponse(shortUrlDto.getOriginalUrl(), request),
                HttpStatus.OK
        );
    }

    @GetMapping("/lu/{urlcode}")
    public RedirectView redirectToOriginal(@PathVariable("urlcode") final String urlcode,
                                           HttpServletRequest request){
        String requestId = request.getSession().getId().substring(0,6);
        logger.info(getMessage("Short url code: "+urlcode, requestId));
        Urls savedRecord = urlService.findOriginalUrl(BASE_XIAOURL.concat(urlcode));
        logger.info(getMessage("Redirecting to : "+savedRecord.getOriginalUrl(), requestId));
        return new RedirectView(savedRecord.getOriginalUrl());
    }
}
