package com.pierre.oceanblu.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

public class IllegalImageSizeException extends FileUploadException {

    public IllegalImageSizeException(long actual, long permitted) {

        super("Uploaded image size " + actual/1024 + "KB exceeds maximum " + permitted/1024 + "KB");
    }
}
