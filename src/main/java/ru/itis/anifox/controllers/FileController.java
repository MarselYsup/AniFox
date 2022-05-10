package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.services.FileService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("file") MultipartFile multipartFile) {
        return "redirect:/" + fileService.uploadFile(multipartFile).getStorageFileName();
    }
    @GetMapping("/{storageName}")
    public void downloadFiles(@PathVariable String storageName, HttpServletResponse response) {
        fileService.downloadFile(storageName,response);
    }

}
