package shoppingmall.domain.item;

import org.apache.ibatis.annotations.*;
import shoppingmall.domain.member.User;
import shoppingmall.global.config.model.UpLoadFileInfo;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Insert("insert into product(prd_name, prd_stock, prd_price, mem_idx) values (#{item.itemName}, #{item.quantity}, #{item.price}, #{user.mem_idx})")
    @Options(useGeneratedKeys = true, keyProperty = "item.prd_idx") // item.prd_idx로 수정
    public void save(@Param("item") Item item, @Param("user") User user);

    @Insert("insert into MULTIMEDIA(prd_idx, file_id, original_file_name, file_extension, file_size, reg_date) values (#{prd_idx}, #{fileId}, #{originalName}, #{extension}, #{size}, NOW())")
    public void upload(UpLoadFileInfo upLoadFileInfo);

    @Select("SELECT p.prd_idx, p.prd_name as itemName, p.prd_stock as quantity, p.prd_price as price, m.mem_id, mm.file_id FROM product p INNER JOIN member m ON p.mem_idx = m.mem_idx LEFT JOIN multimedia mm ON p.prd_idx = mm.prd_idx WHERE p.prd_name LIKE CONCAT('%', #{search}, '%') OR m.mem_id LIKE CONCAT('%', #{search}, '%');")
    List<Item> getItems(String search);

    @Select("SELECT product.prd_idx as prd_idx, product.prd_name as itemName, product.prd_stock as quantity, product.prd_price as price, member.mem_name as mem_name, multimedia.file_id as file_id FROM product JOIN member ON product.mem_idx = member.mem_idx JOIN multimedia ON product.prd_idx = multimedia.prd_idx WHERE product.prd_idx = #{id}")
    Item getItemDetail(Long id);

    @Select("SELECT COUNT(*) FROM product WHERE prd_idx = #{prd_idx} AND mem_idx = #{mem_idx}")
    int isOwner(Long prd_idx, long mem_idx);

    @Update("UPDATE product SET prd_name = #{itemName},  prd_stock = #{quantity},  prd_price = #{price} WHERE prd_idx = #{prd_idx} AND mem_idx = #{mem_idx}")
    void edit(Item item);

    @Update("UPDATE MULTIMEDIA SET file_id = #{fileId}, original_file_name = #{originalName}, file_extension = #{extension}, file_size = #{size}, reg_date = sysdate() WHERE prd_idx = #{prd_idx};")
    void editUpload(UpLoadFileInfo upLoadFileInfo);

    @Delete("DELETE FROM product WHERE mem_idx = #{mem_idx} AND prd_idx = ${prd_idx}")
    void deleteItem(Item item);

    @Delete("DELETE FROM MULTIMEDIA WHERE prd_idx = #{prd_idx}")
    void deleteImg(Item item);
}
