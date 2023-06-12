package shoppingmall.web.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shoppingmall.domain.defaultDto.DefaultResultDto;
import shoppingmall.domain.item.Item;
import shoppingmall.domain.item.ItemRepository;
import shoppingmall.global.component.FileStore;
import shoppingmall.global.config.model.ApiResponse;
import shoppingmall.global.config.model.UpLoadFileInfo;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping
    public String items(Model model,@RequestParam(required = false, defaultValue = "") String search) {
        List<Item> items = itemRepository.getItems(search);

        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<ApiResponse<Item>> itemGet(@PathVariable Long itemId) {
        return ResponseEntity.ok(new ApiResponse<Item>(itemRepository.findById(itemId)));
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DefaultResultDto>> add(@RequestBody Item item) {
        return ResponseEntity.ok(new ApiResponse<>(itemRepository.save(item)));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<DefaultResultDto>> uploadFile(@RequestParam("img") MultipartFile multipartFile,
                                                                    @RequestParam("prd_idx")int prd_idx) throws IOException {
        UpLoadFileInfo upLoadFileInfo = fileStore.uploadFile(multipartFile);
        upLoadFileInfo.setPrd_idx(prd_idx);

        return ResponseEntity.ok(new ApiResponse<>(itemRepository.uploadFile(upLoadFileInfo)));
    }

    @GetMapping("/manage/{prd_idx}")
    public String edit(@PathVariable Long prd_idx) {
        return itemRepository.isOwner(prd_idx);
    }

    @PostMapping("/edit")
    public ResponseEntity<ApiResponse<DefaultResultDto>> edit(@RequestBody Item item) {
        return ResponseEntity.ok(new ApiResponse<DefaultResultDto>(itemRepository.edit(item)));
    }
    @PostMapping("/edit/upload")
    public ResponseEntity<ApiResponse<DefaultResultDto>> editUpload(@RequestParam("img") MultipartFile multipartFile,
                                                                    @RequestParam("prd_idx")int prd_idx) throws IOException {
        UpLoadFileInfo upLoadFileInfo = fileStore.uploadFile(multipartFile);
        upLoadFileInfo.setPrd_idx(prd_idx);

        return ResponseEntity.ok(new ApiResponse<>(itemRepository.editUploadFile(upLoadFileInfo)));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<DefaultResultDto>> delete(@RequestBody Item item) {
        return ResponseEntity.ok(new ApiResponse<DefaultResultDto>(itemRepository.delete(item)));
    }

}
