package shoppingmall.web.item.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import shoppingmall.global.component.FileStore;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api")
public class ViewController {
    
    private final FileStore fileStore;

    public ViewController(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @GetMapping("/img/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullFilePath(filename));
    }
}
