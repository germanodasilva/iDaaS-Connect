package com.redhat.idaas.connect.bluebutton.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Service
public class PropertiesConfig {

    @Value("${config.bluebutton.CLIENT_ID}")
    private String CLIENT_ID;
 
    @Value("${config.bluebutton.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Value("${config.bluebutton.CALLBACK_PATH}")
    private String CALLBACK_PATH;

    @Value("${config.bluebutton.CALLBACK_HOST}")
    private String CALLBACK_HOST;

    @Value("${config.bluebutton.CALLBACK_PORT}")
    private String CALLBACK_PORT;

    @Value("${config.bluebutton.SERVER_PORT}")
    private String SERVER_PORT;

    @Value("${config.api.CMS_URL}")
    private String CMS_URL;

    @Value("${config.api.CMS_VERSION}")
    private String CMS_VERSION;

    @Value("${config.streams.KAFKA_URL}")
    private String KAFKA_URL;

    @Value("${config.streams.KAFKA_TOPIC}")
    private String KAFKA_TOPIC;

}
