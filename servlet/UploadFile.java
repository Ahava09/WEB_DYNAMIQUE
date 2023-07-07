package etu1985.framework.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import jakarta.servlet.http.Part;
import java.io.InputStream;

public class UploadFile {

    private String fileName;
    private String path;
    private byte[] fileBytes;

    public UploadFile(Part part) throws IOException {
        this.setFileName(part.getSubmittedFileName());
        this.setPath("");
        this.setFileBytes(getBytesFromPart(part));
    }

    public UploadFile(){}

    public byte[] getBytesFromPart(Part part) throws IOException {
        try (InputStream inputStream = part.getInputStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileBytes(byte[] bytes) {
        this.fileBytes = bytes;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getPath() {
        return this.path;
    }

    public byte[] getFileBytes() {
        return this.fileBytes;
    }
}
