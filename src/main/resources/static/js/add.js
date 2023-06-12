function add() {
        let itemName = existId("itemName");
        let price = existId("price");
        let quantity = existId("quantity");
        let img = existId("img");

        if(itemName.value == null || itemName.value == ""){
            alert("상품명을 입력하세요.");
            return false;
        }
        if(price.value == null || price.value == ""){
            alert("가격을 입력하세요.");
            return false;
        }
        if(quantity.value == null || quantity.value == ""){
            alert("수량을 입력하세요.");
            return false;
        }
        if(img.value == null || img.value == ""){
            alert("상품 사진을 첨부해주세요.");
            return false;
        }

        let data = {};
        data.itemName = itemName.value;
        data.price = price.value;
        data.quantity = quantity.value;
        httpUtil.defaultRequest('/basic/items/add','post', data, (data) => {
            if(data.data.success){
                let fileData = new FormData();
                fileData.append("img", $("#img")[0].files[0]);
                fileData.append("prd_idx", data.data.idx);
                httpUtil.uploadRequest('/basic/items/upload','post', fileData, (data) => {
                    location.href = "/";
                })
            }else{
                alert(data.data.message);
            }
        })

}
function getItem() {
        let url = window.location.pathname;
        let lastIdx = url.lastIndexOf('/');
        let prd_idx = url.substring(lastIdx + 1);
        let html = "";
        httpUtil.defaultRequest('/basic/items/get/'+ prd_idx,'get', null, (data) => {
            html += `
                    <div>
                        <label for="itemName">상품명</label>
                        <input type="text" id="itemName" value="${data.data.itemName}" name="itemName" class="form-control" required placeholder="이름을 입력하세요">
                    </div>
                    <div>
                        <label for="price">가격</label>
                        <input type="text" id="price" value="${data.data.price}" name="price" class="form-control" required placeholder="가격을 입력하세요">
                    </div>
                    <div>
                        <label for="quantity">수량</label>
                        <input type="text" id="quantity" value="${data.data.quantity}" name="quantity" class="form-control" required placeholder="수량을 입력하세요">
                    </div>
                    <div>
                        <label for="quantity">이미지</label>
                        <input type="file" id="img" name="quantity" class="form-control" required placeholder="선택하지 않으면 기존에 쓰던 사진이 사용됩니다.">
                    </div>

            `
                let htmlBody = document.getElementById("html");
                htmlBody.innerHTML = html;
        })

}


function edit() {
        let itemName = existId("itemName");
        let price = existId("price");
        let quantity = existId("quantity");

        if(itemName.value == null || itemName.value == ""){
            alert("상품명을 입력하세요.");
            return false;
        }
        if(price.value == null || price.value == ""){
            alert("가격을 입력하세요.");
            return false;
        }
        if(quantity.value == null || quantity.value == ""){
            alert("수량을 입력하세요.");
            return false;
        }
        let url = window.location.pathname;
        let lastIdx = url.lastIndexOf('/');
        let prd_idx = url.substring(lastIdx + 1);
        let data = {};
        data.prd_idx = prd_idx;
        data.itemName = itemName.value;
        data.price = price.value;
        data.quantity = quantity.value;
        console.log(data);
        httpUtil.defaultRequest('/basic/items/edit','post', data, (data) => {
            let fileInput = document.getElementById('img');
            let file = fileInput.files[0];
            if(file){
                let fileData = new FormData();
                fileData.append("img", $("#img")[0].files[0]);
                fileData.append("prd_idx", data.data.idx);
                httpUtil.uploadRequest('/basic/items/edit/upload','post', fileData, (data) => {
                    location.href = "/basic/items/"+prd_idx;
                })
            }
        })
}

function deleteItem() {
        let really = confirm("정말 삭제하시겠습니까?");
        if(!really){
            return false;
        }
        let url = window.location.pathname;
        let lastIdx = url.lastIndexOf('/');
        let prd_idx = url.substring(lastIdx + 1);
        let data = {};
        data.prd_idx = prd_idx;
        httpUtil.defaultRequest('/basic/items/delete','post', data, (data) => {
            alert(data.data.message);
            location.href = "/basic/items"
        })


}

