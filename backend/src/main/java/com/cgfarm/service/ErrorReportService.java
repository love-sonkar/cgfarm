package com.cgfarm.service;

import com.cgfarm.entity.ErrorReport;
import com.cgfarm.helper.UUIDHelper;
import com.cgfarm.repository.ErrorReportRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class ErrorReportService {

    private final ErrorReportRepository errorReportRepository;

    private static final String SERVICE = "cgfarm";

    @Autowired
    UUIDHelper uuidHelper;

    Gson gson = new Gson();

    public ErrorReportService(ErrorReportRepository errorReportRepository) {
        this.errorReportRepository = errorReportRepository;
    }

    public UUID generateErrorReport(Exception e, String parentType, String className, String function){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();
        UUID uuid = uuidHelper.generateId();
        ErrorReport errorReport = new ErrorReport();
        errorReport.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()).getTime());
        errorReport.setService(SERVICE);
        errorReport.setUuid(uuid.toString());
        errorReport.setErrorStacktrace(sStackTrace);
        errorReport.setErrorClass(e.getClass().getCanonicalName());
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("parentType", parentType);
        errorDetails.put("className", className);
        errorDetails.put("function", function);
        errorReport.setErrorDetails(gson.toJson(errorDetails));
        createErrorReport(errorReport);
        log.error("Error: " + uuid, e);
        return uuid;
    }

    public UUID generateErrorReport(String e, String service, String parentType, String className, String function){
        UUID uuid = uuidHelper.generateId();
        ErrorReport errorReport = new ErrorReport();
        errorReport.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()).getTime());
        errorReport.setService(service);
        errorReport.setUuid(uuid.toString());
        errorReport.setErrorStacktrace(e);
        errorReport.setErrorClass(className);
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("parentType", parentType);
        errorDetails.put("className", className);
        errorDetails.put("function", function);
        errorReport.setErrorDetails(gson.toJson(errorDetails));
        createErrorReport(errorReport);
        log.error("Error: {} {}", uuid , e);
        return uuid;
    }

    public ErrorReport createErrorReport (ErrorReport errorReport){
        log.info("Create Error Report");
        return save(errorReport);
    }

    private ErrorReport save(ErrorReport errorReport){
        try {
            return errorReportRepository.save(errorReport);
        } catch (Exception e){
            log.error("Error in saving Error Report", e);
            return null;
        }
    }
}
