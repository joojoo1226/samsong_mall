package shoppingmall.domain.item;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import shoppingmall.domain.defaultDto.DefaultResultDto;
import shoppingmall.domain.member.User;
import shoppingmall.global.config.exception.CustomException;
import shoppingmall.global.config.exception.ErrorCode;
import shoppingmall.global.config.model.UpLoadFileInfo;
import shoppingmall.global.utils.SessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public class ItemRepository {

    private final ItemMapper itemMapper;
    private static final Map<Long, Item> store = new HashMap<>();

    public ItemRepository(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public DefaultResultDto save(Item item) {
        User user = SessionUtil.getUserAttribute();
        if (user == null) {
            return DefaultResultDto.builder()
                    .message("로그인이 필요한 기능입니다.")
                    .success(false)
                    .build();
        }

        itemMapper.save(item, user);

        return DefaultResultDto.builder()
                .idx(item.getPrd_idx())
                .message("")
                .success(true)
                .build();
    }

    public DefaultResultDto uploadFile(UpLoadFileInfo upLoadFileInfo) {
        itemMapper.upload(upLoadFileInfo);

        return DefaultResultDto.builder()
                .message("등록 성공")
                .success(true)
                .build();
    }


    public Item findById(Long id) {
        return itemMapper.getItemDetail(id);
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

    public List<Item> getItems(String search) {
        return itemMapper.getItems(search);
    }

    public String isOwner(Long prd_idx) {
        User user = SessionUtil.getUserAttribute();
        if (user == null) {
            return "redirect:/member/loginForm";
        }

        int count = itemMapper.isOwner(prd_idx, user.getMem_idx());
        if (count == 0) {
            throw new CustomException("본인 게시물이 아닙니다.", ErrorCode.METHOD_NOT_ALLOWED);
        }

        return "basic/editForm";
    }

    public DefaultResultDto edit(Item item) {
        User user = SessionUtil.getUserAttribute();

        item.setMem_idx(user.getMem_idx());
        itemMapper.edit(item);

        return DefaultResultDto.builder()
                .idx(item.getPrd_idx())
                .message("수정 성공")
                .success(true)
                .build();
    }

    public DefaultResultDto editUploadFile(UpLoadFileInfo upLoadFileInfo) {
        itemMapper.editUpload(upLoadFileInfo);

        return DefaultResultDto.builder()
                .message("사진 수정 성공")
                .success(true)
                .build();
    }

    public DefaultResultDto delete(Item item) {
        User user = SessionUtil.getUserAttribute();

        item.setMem_idx(user.getMem_idx());
        itemMapper.deleteItem(item);
        itemMapper.deleteImg(item);

        return DefaultResultDto.builder()
                .message("삭제 완료")
                .success(true)
                .build();
    }
}